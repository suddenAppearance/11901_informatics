let username_taken = false;
function pass_identity() {
    const pass = document.getElementById("id_password");
    const pass_rep = document.getElementById("id_password_repeat");
    const btn = document.getElementById("id_button")
    const username = document.getElementById("id_username")
    const username_valid = username_validate_format(username.value) && !username_taken
    const passwords_match = pass.value !== pass_rep.value
    btn.disabled =  passwords_match || !username_valid
    pass.style.backgroundColor = passwords_match ? "#ffcccc" : ""
    pass_rep.style.backgroundColor = passwords_match ? "#ffcccc" : ""
    username.style.backgroundColor = !username_valid ? "#ffcccc" : ""
    username.title = `
                        Имя пользователя может состоять из 8–30 знаков и содержать латинские буквы, цифры, нижнее подчеркивание и точку
                        Имя пользователя может начинаться и заканчиваться любым разрешенным символом, кроме точки.`
}

function validate_username_usage(username) {
    if (!username_validate_format(username)) {
        return;
    }
    let a = document.getElementById("username_validation")
    a.innerHTML = "<i class=\"fa fa-spinner fa-spin\">"
    let data = {
        "username": username
    }

    $.ajax(
        {
            url: "/usernameValidationProcessor",
            type: "GET",
            data: data,
            success: function (response) {
                if (response === "Valid") {
                    username_is_not_used()
                } else (
                    username_is_already_used()
                )
            }
        }
    )
}

function username_is_not_used() {
    let a = document.getElementById("username_validation")
    let btn = document.getElementById("id_button")
    a.className = ""
    username_taken = false
    a.innerHTML = "<i class=\"fa fa-check-circle\" style='color: green; position: ' title='Имя пользователя свободно'></i>"
    btn.disabled = false
}

function username_is_already_used() {
    let a = document.getElementById("username_validation")
    a.className = ""
    username_taken = true
    a.innerHTML = "<i class=\"fa fa-exclamation-circle\" style='color: red' title='Имя пользователя занято'></i>"
    let btn = document.getElementById("id_button")
    btn.disabled = true;
}

function username_validate_format(username) {
    return username.match(/^([A-Za-z_0-9.])+$/) && !username.match(/.*\.\..*|^\..+|.+\.$/) && 8 <= username.length && username.length <= 30
}