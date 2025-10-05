param(
    [switch]$Deb,
    [switch]$Msi,
    [switch]$Help
)

function Write-Usage {
    Write-Host @"
Usage: package-installers.ps1 [-Deb] [-Msi]

Creates Expresso CLI installers using jpackage. When no flags are supplied the
script produces an MSI on Windows. It can automatically bootstrap required
dependencies using winget (Temurin JDK 23, Maven, WiX).
"@
}

if($Help){ Write-Usage; return }

$IsWindows = $true
if(-not $Deb -and -not $Msi){
    $Msi = $true
}

if($Deb){
    Write-Warning "DEB packaging requires Linux. Please run scripts/package-installers.sh under WSL or a Linux host."
    $Deb = $false
}

$repoRoot = (Get-Item "$PSScriptRoot\..").FullName
Push-Location $repoRoot
try {
    function Test-Command($name){ Get-Command $name -ErrorAction SilentlyContinue }

    function Add-PathHints([string[]]$globs){
        foreach($glob in $globs){
            try {
                $paths = Resolve-Path -Path $glob -ErrorAction Stop
            } catch {
                continue
            }
            foreach($path in $paths){
                $p = $path.Path
                if(Test-Path $p){
                    if(($env:PATH -split ';') -notcontains $p){
                        $env:PATH = "$p;" + $env:PATH
                    }
                }
            }
        }
    }

    function Ensure-WingetPackage {
        param(
            [string]$CommandName,
            [string]$WingetId,
            [string]$DisplayName,
            [string[]]$PathHints
        )
        if(Test-Command $CommandName){ return }
        if(-not (Test-Command 'winget')){
            throw "winget is required to bootstrap $DisplayName. Install from Microsoft Store and retry."
        }
        $isAdmin = ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltinRole]::Administrator)
        if(-not $isAdmin){
            Write-Warning "Installing $DisplayName requires elevated privileges. Please run PowerShell as Administrator if prompted."
        }
        Write-Host "[INFO] Installing $DisplayName via winget..."
        $args = @('install','--id', $WingetId,'--exact','--silent','--accept-package-agreements','--accept-source-agreements')
        winget @args | Out-Null
        if($PathHints){ Add-PathHints $PathHints }
        if(-not (Test-Command $CommandName)){
            throw "Unable to locate $DisplayName even after installation. Please restart the shell or add it to PATH manually."
        }
    }

    Ensure-WingetPackage -CommandName 'jpackage' -WingetId 'EclipseAdoptium.TemurinJDK.23.JDK' -DisplayName 'Temurin JDK 23' -PathHints @('C:\Program Files\Eclipse Adoptium\jdk*\bin')
    Ensure-WingetPackage -CommandName 'mvn' -WingetId 'Apache.Maven' -DisplayName 'Apache Maven' -PathHints @('C:\Program Files\Apache\maven\apache-maven*\bin', 'C:\Program Files\Apache Software Foundation\Maven*\bin')
    Ensure-WingetPackage -CommandName 'candle.exe' -WingetId 'WiXToolset.WiXToolset' -DisplayName 'WiX Toolset' -PathHints @('C:\Program Files (x86)\WiX Toolset v3.*\bin')

    # Ensure WiX bin directory is on PATH for jpackage
    Add-PathHints @('C:\Program Files (x86)\WiX Toolset v3.*\bin')

    if(-not (Test-Command 'mvn')){ throw 'Maven (mvn) not found on PATH.' }
    if(-not (Test-Command 'jpackage')){ throw 'jpackage not found even after installation.' }

    $version = (& mvn -q -DforceStdout help:evaluate -Dexpression=project.version)
    $appName = 'expresso'
    $mainClass = 'com.diezam04.expresso.adapters.cli.Cli'
    $inputDir = Join-Path $repoRoot 'expresso-cli/target'
    $distDir = Join-Path $repoRoot 'out/installers'
    if(-not (Test-Path $distDir)){ New-Item -ItemType Directory -Path $distDir | Out-Null }

    & mvn -pl expresso-cli -am -DskipTests package | Out-Null

    $jarName = "expresso-cli-$version-shaded.jar"
    $jarPath = Join-Path $inputDir $jarName
    if(-not (Test-Path $jarPath)){
        throw "Shaded jar not found at $jarPath"
    }

    $licenseArgs = @()
    $license = Join-Path $repoRoot 'LICENSE'
    if(Test-Path $license){ $licenseArgs = @('--license-file', $license) }

    if($Msi){
        Write-Host "`n[INFO] Generating MSI installer..."
        & jpackage \
            --type msi \
            --name $appName \
            --app-version $version \
            --vendor 'Expresso Team' \
            --input $inputDir \
            --main-jar $jarName \
            --main-class $mainClass \
            --dest $distDir \
            --win-console \
            --win-dir-chooser \
            --win-menu \
            --win-menu-group 'Expresso' \
            @licenseArgs
    }

    Write-Host "`n[INFO] Installers available under: $distDir"
}
finally {
    Pop-Location
}
