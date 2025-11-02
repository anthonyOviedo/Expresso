const $ = id => document.getElementById(id);

function append(targetId, msg) {
  const el = $(targetId);
  el.value += msg + "\n";
  el.scrollTop = el.scrollHeight;
}

function log(msg) {
  append("console", msg);
}

function showOutput(text) {
  $("output").value = text + (text.endsWith("\n") ? "" : "\n");
  $("output").scrollTop = $("output").scrollHeight;
}

function setStatus(message) {
  $("status").textContent = message;
}

function buildPayload() {
  return {
    source: $("editor").value
  };
}

async function post(path) {
  try {
    // CLEAR OUTPUT BEFORE EACH OPERATION
    $("output").value = "";
    
    setStatus("Calling " + path + " ...");
    const res = await fetch(path, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(buildPayload())
    });

    // If server signals a download, save it
    const dispo = res.headers.get("content-disposition") || "";
    if (res.ok && /attachment/i.test(dispo)) {
      // filename or filename*=UTF-8''...
      const m = dispo.match(/filename\*?=(?:UTF-8'')?("?)([^";]+)\1/i);
      const filename = m ? decodeURIComponent(m[2]) : "download";

      const blob = await res.blob();
      const url = URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = filename;
      document.body.appendChild(a);
      a.click();
      a.remove();
      URL.revokeObjectURL(url);

      log(`Downloaded ${filename} (${blob.size} bytes)`);
      setStatus("Received download: " + filename);
      return; // don't also log text
    }

    // Fallback: show text (for /transpile, /run, /build or errors)
    const text = await res.text();
    if (res.ok && text) {
      // Handle JSON responses for all endpoints
      if (text.startsWith("{")) {
        try {
          const json = JSON.parse(text);
          
          // For transpile: show Java source code
          if (path.includes("/transpile") && json.javaSource) {
            showOutput(json.javaSource);
          }
          // For run: show console output
          else if (path.includes("/run") && json.output) {
            showOutput(json.output.trim());
          }
          // For build: show build output or success message
          else if (path.includes("/build")) {
            if (json.fileName && json.classBytesBase64) {
              const blob = new Blob(
                [Uint8Array.from(atob(json.classBytesBase64), c => c.charCodeAt(0))],
                { type: "application/octet-stream" }
              );
              const link = document.createElement("a");
              link.href = URL.createObjectURL(blob);
              link.download = json.fileName;
              link.click();
              showOutput(`Downloaded ${json.fileName}`);
            } else if (json.output) {
              showOutput(json.output.trim());
            } else if (json.message) {
              showOutput(json.message);
            } else {
              showOutput("Build completed successfully");
            }
          }
          // Fallback for any other JSON response
          else if (json.output) {
            showOutput(json.output.trim());
          } else if (json.message) {
            showOutput(json.message);
          }
          
          // Also log to console if there's additional output
          if (json.output && !path.includes("/run")) {
            append("console", json.output.trim());
          }
        } catch (ignored) {
          // If JSON parsing fails, show raw text
          showOutput(text);
        }
      } else {
        // For non-JSON responses, show the raw text
        showOutput(text);
      }
    } else {
      // For error responses, show in console
      append("console", text || `${res.status} ${res.statusText}`);
    }
    setStatus(res.ok ? "OK" : `${res.status} ${res.statusText}`);
  } catch (e) {
    const message = "Error: " + (e.message || e);
    append("console", message);
    setStatus("Request failed");
  }
}

$("btnTranspile").onclick = () => post("/api/v1/transpile");
$("btnBuild").onclick = () => post("/api/v1/build");
$("btnRun").onclick = () => post("/api/v1/run");

$("btnCopyOutput").onclick = () =>
  navigator.clipboard.writeText($("output").value);

$("btnClearOutput").onclick = () => {
  $("output").value = "";
  setStatus("Cleared output");
};

$("btnCopyConsole").onclick = () =>
  navigator.clipboard.writeText($("console").value);

$("btnClearConsole").onclick = () => {
  $("console").value = "";
  setStatus("Cleared console");
};

$("btnTheme").onclick = () =>
  document.body.classList.toggle("dark");

setStatus("Ready");