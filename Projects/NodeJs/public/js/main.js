function loadUsers() {
    $.get("http://localhost:8080/users/", function (response) {
        let data = response
        let elem = document.getElementById("users_table")
        for (let i = 0; i < data.length; i++) {
            elem.innerHTML += `
<tr><td>${data[i].id}</td><td>${data[i].fullName}</td><td>${data[i].role}</td></tr>
`
        }
    })
}

function createUser() {
    let data = {
        "fullName": $('#fullName').val(),
        "role": $('#role').val(),
        "token": $('#token').val()
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8080/users/",
        data: JSON.stringify(data),
        success: function (response) {
            location.reload()
        },
        error: function (response) {
            alert(response.responseJSON.message)
        },
        contentType: "application/json"
    })
}

function loadClassrooms() {
    $.get("http://localhost:8080/classrooms/", function (response) {
        let data = response
        let elem = document.getElementById("classrooms_table")
        for (let i = 0; i < data.length; i++) {
            elem.innerHTML += `<tr><td>${data[i].id}</td><td>${data[i].number}</td></tr>`
        }
    })
}

function createClassroom() {
    let data = {
        "number": $("#number").val()
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8080/classrooms/",
        data: JSON.stringify(data),
        success: function (response) {
            location.reload()
        },
        error: function (response) {
            alert(response.responseJSON.message)
        },
        contentType: "application/json"
    })
}

function loadJournal() {

    $.get("http://localhost:8080/journals/", function (response) {
        let data = response
        response.sort((a, b) => a.taken_at > b.taken_at ? 1 : a.taken_at < b.taken_at ? -1 : 0).reverse()
        let elem = document.getElementById("journals_table")
        for (let i = 0; i < data.length; i++) {
            elem.innerHTML += `
<tr><td>${data[i].id}</td><td>${data[i].userFullName}</td>
<td>${data[i].classroomNumber}</td><td>${data[i].taken_at.substr(0, 16).replace("T", " ")}</td>
<td>${data[i].returned_at == null ? "" : data[i].returned_at.substr(0, 16).replace("T", " ")}</td>
<td><input type="password" id="return_password${data[i].id}" placeholder="Пароль"><button onclick="returnKey(${data[i].id})">Вернуть</button></td></tr>
`
        }
    })

}

function createJournal() {
    let data = {
        "classroomId": $("#classroom_id").val(),
        "token": $("#token").val()
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8080/journals/",
        data: JSON.stringify(data),
        success: function (response) {
            location.reload()
        },
        error: function (response) {
            alert(response.responseJSON.message)
        },
        contentType: "application/json"
    })
}

function returnKey(id){
    let data = {
        "token": $("#return_password" + id).val()
    }
    $.ajax({
        type: "post",
        url: `http://localhost:8080/journals/${id}/returnKey`,
        data: JSON.stringify(data),
        success: function (response) {
            location.reload()
        },
        error: function (response) {
            alert(response.responseJSON.message)
        },
        contentType: "application/json"
    })
}