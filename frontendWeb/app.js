const realFileButton = document.getElementById("real-file");
const customButton = document.getElementById("custom-button");
const customText = document.getElementById("custom-text");
const uploadButton = document.getElementById("upload-button");

customButton.addEventListener("click", function () {
    realFileButton.click();
});

uploadButton.addEventListener("click", async function backendApi() {
    //localhost:8080/uploads
    const recentImageDataUrl = sessionStorage.getItem("recent-image");
    //console.log("I am here", recentImageDataUrl);
    // console.log("I am here!", JSON.stringify({
    //     image: recentImageDataUrl
    // }));
    let response = await fetch("http://127.0.0.1:8080/uploads", {
        method: "POST",
        body: {
            image: recentImageDataUrl
        }
    }, { mode: 'cors' });

    if (response.status == 502) {
        // Status 502 is a connection timeout error,
        // may happen when the connection was pending for too long,
        // and the remote server or a proxy closed it
        // let's reconnect
        await backendApi();
    } else if (response.status != 200) {
        // An error - let's show it
        //showMessage(response.statusText);
        console.log(response.statusText);
        // Reconnect in one second

        //await new Promise(resolve => setTimeout(resolve, 1000));
        //await backendApi();
    } else {
        // Get and show the message
        let message = await response.text();
        //showMessage(message);
        console.log(message);
        // Call backendApi() again to get the next message

        //await backendApi();

        if (message) {
            // .then(response => {
            //     //handle response            
            //     console.log(response);
            // })
            // .then(data => {
            //     //handle data
            //     console.log(data);
            // })
            //document.getElementById('apiResponse').src=URL.createObjectURL(message);
            document.getElementById('apiResponseAnimal').innerHTML=message.animal;
            document.getElementById('apiResponseStatus').innerHTML=message.status;
        }
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
        const recentImageDataUrl = sessionStorage.getItem("recent-image"); // const was here

        if (recentImageDataUrl) {
            document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
        }
    });

    reader.readAsDataURL(this.files[0]);
});