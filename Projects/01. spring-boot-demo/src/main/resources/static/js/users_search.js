function search(pre) {
    let url = `http://localhost:8080/users/search?email=${pre}`
    let e = document.getElementById("found")
    e.innerText = ""
    $.ajax({
        url: url,

        success: function (response) {
            let e = document.getElementById("found")
            for (let i = 0; i < response.length; i++) {
                console.log(response[i])
                e.innerText+=response[i].toString()
            }
        },
    });
}