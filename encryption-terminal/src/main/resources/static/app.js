/* ---------- Cipher Steps ---------- */

function addStep() {
    const stepsDiv = document.getElementById("steps");

    const div = document.createElement("div");

    div.innerHTML = `
        <select class="type">
            <option value="reverse">reverse</option>
            <option value="caesar">caesar</option>
            <option value="swap">swap</option>
            <option value="rotation">rotation</option>
            <option value="xor">xor</option>
            <option value="vowel">vowel</option>
        </select>
        <input class="key" type="number" placeholder="key (optional)">
        <button onclick="this.parentElement.remove()">❌</button>
    `;

    stepsDiv.appendChild(div);
}

function buildRequest() {
    const message = document.getElementById("message").value;

    const steps = Array.from(document.querySelectorAll("#steps > div"))
        .map(div => {
            const type = div.querySelector(".type").value;
            const key = div.querySelector(".key").value;

            return key
                ? { type, key: Number(key) }
                : { type };
        });

    return { message, steps };
}

/* ---------- Basic Encrypt / Decrypt ---------- */

async function encrypt() {
    send("/api/encrypt");
}

async function decrypt() {
    send("/api/decrypt");
}

async function send(endpoint) {
    const payload = buildRequest();

    const res = await fetch(endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    const data = await res.json();
    document.getElementById("output").textContent =
        JSON.stringify(data, null, 2);
}

/* ---------- Trace (Animated) ---------- */

async function encryptTrace() {
    sendTrace("/api/encrypt/trace");
}

async function decryptTrace() {
    sendTrace("/api/decrypt/trace");
}

async function sendTrace(endpoint) {
    const payload = buildRequest();

    const res = await fetch(endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    const data = await res.json();
    renderTrace(data);
}

function renderTrace(data) {
    const outputDiv = document.getElementById("output");
    outputDiv.innerHTML = "";

    let delay = 0;

    addAnimatedBlock("INPUT", data.input, outputDiv, delay);
    delay += 600;

    data.steps.forEach((step, index) => {
        const title =
            `STEP ${index + 1} → ${step.type}` +
            (step.key !== null ? ` (key=${step.key})` : "");

        addAnimatedBlock(title, step.output, outputDiv, delay);
        delay += 600;
    });

    addAnimatedBlock("FINAL OUTPUT", data.finalOutput, outputDiv, delay);
}

function addAnimatedBlock(title, text, container, delay) {
    const block = document.createElement("div");
    block.className = "trace-step";

    block.innerHTML = `
        <div class="title">${title}</div>
        <div class="text">${text}</div>
    `;

    container.appendChild(block);

    setTimeout(() => {
        block.classList.add("show");
    }, delay);
}

/* ---------- Cipher Info Panel ---------- */

async function toggleInfo() {
    const panel = document.getElementById("cipher-info");

    if (panel.style.display === "none") {
        panel.style.display = "block";
        await loadCipherInfo();
    } else {
        panel.style.display = "none";
    }
}

async function loadCipherInfo() {
    const res = await fetch("/api/ciphers/info");
    const data = await res.json();

    let html = `
        <h3>🔐 What is a Cipher?</h3>
        <p>${data.whatIsCipher}</p>

        <h3>🔒 Encryption</h3>
        <p>${data.whatIsEncryption}</p>

        <h3>🔓 Decryption</h3>
        <p>${data.whatIsDecryption}</p>

        <h3>🧠 Supported Ciphers</h3>
    `;

    data.supportedCiphers.forEach(cipher => {
        html += `
            <div class="cipher-card">
                <h4>${cipher.name} (${cipher.type})</h4>
                <p>${cipher.description}</p>
                <p><b>Requires Key:</b> ${cipher.requiresKey ? "Yes" : "No"}</p>
                <code>${cipher.example}</code>
            </div>
        `;
    });

    document.getElementById("cipher-info").innerHTML = html;
}
