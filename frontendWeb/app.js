const menu = document.querySelector('#mobile-menu')
const menuLinks = document.querySelector('.navbar__menu')

menu.addEventListener('click', function () {
    menu.classList.toggle('is-active')
    menuLinks.classList.toggle('active')
});

const realFileButton = document.getElementById("real-file");
const customButton = document.getElementById("custom-button");
const customText = document.getElementById("custom-text");
//const realUploadButton = document.getElementById("real-upload");
const uploadButton = document.getElementById("upload-button");

customButton.addEventListener("click", function () {
    realFileButton.click();
});

uploadButton.addEventListener("click", async function backendApi() {
    //realUploadButton.click();
    let response = await fetch("/backendApi");

    if (response.status == 502) {
        // Status 502 is a connection timeout error,
        // may happen when the connection was pending for too long,
        // and the remote server or a proxy closed it
        // let's reconnect
        await backendApi();
    } else if (response.status != 200) {
        // An error - let's show it
        showMessage(response.statusText);
        // Reconnect in one second
        await new Promise(resolve => setTimeout(resolve, 1000));
        await backendApi();
    } else {
        // Get and show the message
        let message = await response.text();
        showMessage(message);
        // Call backendApi() again to get the next message
        await backendApi();
    }
});

realFileButton.addEventListener("change", function () {
    const reader = new FileReader();

    if (realFileButton.value) {
        customText.innerHTML = realFileButton.value.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
    } else {
        customText.innerHTML = "No file selected...";
    }

    reader.addEventListener("load", () => {
        sessionStorage.setItem("recent-image", reader.result);
        const recentImageDataUrl = sessionStorage.getItem("recent-image");

        if (recentImageDataUrl) {
            document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
        }
    });

    reader.readAsDataURL(this.files[0]);
});