const $ = id => document.getElementById(id);

function log(msg) {
  $("output").textContent +=  msg + "\n";
  $("output").scrollTop = $("output").scrollHeight;
}


async function post(path) {
  try {
    const res = await fetch(path, {
      method: "POST",
      headers: { "Content-Type": "text/plain" },
      body: "public class Main {public static void main(String[] args) {" + $("editor").value  + "}}"
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
      return; // don't also log text
    }

    // Fallback: show text (for /transpile, /run, or errors)
    const text = await res.text();
    log(text || `${res.status} ${res.statusText}`);
  } catch (e) {
    log("Error: " + (e.message || e));
  }
}


$("btnTranspile").onclick = () => post("/api/v1/transpile");
$("btnBuild").onclick = () => post("/api/v1/build");
$("btnRun").onclick = () => post("/api/v1/run");

$("btnCopy").onclick = () =>
  navigator.clipboard.writeText($("console").textContent);

$("btnTheme").onclick = () =>
  document.body.classList.toggle("dark");
