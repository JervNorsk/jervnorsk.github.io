// Gets DOM elements
const textArea = document.querySelector("textarea");
const playButtom = document.querySelector("button");
const pitchBar = document.querySelector("input");
const duckFigure = document.querySelector("figure");

// On Play Button is clicked
playButtom.addEventListener("click", () => {
    const text = textArea.value.trim();

    if (!text) return;

    talk(text);
})

// Function for make the Duck talk
function talk(text) {
    // Gets pitch
    const pitch = pitchBar.value;

    // Compute text into SpeechSynthesisUtterance
    const utterance = new SpeechSynthesisUtterance(text);

    // Configure SpeechSynthesisUtterance
    utterance.volume = 1;
    utterance.rate = 1;
    utterance.pitch = pitch
    utterance.lang = "it"

    // Configure SpeechSynthesis on start
    utterance.addEventListener("start", () => {
        // Animate the Duck
        duckFigure.classList.add("talking");

        // Lock Controls
        textArea.disabled = true;
        playButtom.disabled = true;
        pitchBar.disabled = true;
    })

    // Configure SpeechSynthesis on end
    utterance.addEventListener("end", () => {
        // Animate the Duck
        duckFigure.classList.remove("talking");

        // Unlock Controls
        textArea.disabled = false;
        playButtom.disabled = false;
        pitchBar.disabled = false;
    })

    // Speak with SpeechSynthesis
    speechSynthesis.speak(utterance);
}