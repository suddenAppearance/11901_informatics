<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация. HH</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.1/examples/sticky-footer-navbar/sticky-footer-navbar.css"
          rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"
            integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container" style="margin-top: 100px">
    <div class="row">

        <div class="offset-md-3 col-md-6">
            <form class="form-horizontal" action="/signIn" method="post">
                <span class="heading">ВХОД</span>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" maxlength="32" required id="id_email"
                           placeholder="Email">
                    <div><i class="fa fa-at"></i></div>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="password" maxlength="32" required id="id_password"
                           placeholder="Пароль ">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-default" id="id_button">Зарегистрироваться</button>
                </div>
                <p>Нет аккаунта? <a href="/signUp">Регистрация</a></p>
            </form>
        </div>

    </div>
</div>
</body>
</html>
