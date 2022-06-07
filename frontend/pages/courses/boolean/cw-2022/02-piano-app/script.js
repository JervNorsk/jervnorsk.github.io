const keyElements = document.querySelectorAll(".key");

function playSound(id) {
    console.log(`Play: ${id}`);

    const audioElement = new Audio();

    audioElement.src = `./assets/sounds/${id}.mp3`;

    audioElement.play();
}

keyElements.forEach((it) => {
    it.addEventListener("touchend", function (e) {
        playSound(it.id);
    })
})