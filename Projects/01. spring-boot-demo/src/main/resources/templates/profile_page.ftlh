<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль. HeadHunters</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"
            integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body onload="document.getElementById('navbarDropdown').click(); document.getElementById('navbarDropdown').blur()">
<nav class="navbar sticky-top navbar-light bg-light navbar-expand-md">
    <a href="/profile" class="navbar-brand">HeadHunters</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="/profile/vacancies">Мои вакансии</a></li>
            <li class="nav-item"><a class="nav-link" href="/profile/resumes">Мои резюме</a></li>
            <li class="nav-item"><a class="nav-link" href="/resume/create">Создать резюме</a></li>
            <li class="nav-item"><a class="nav-link" href="/vacancy/create">Создать вакансию</a></li>
            <li class="nav-item"><a class="nav-link" href="/profile/vacancies/saved">Избранное вакансий</a></li>
            <li class="nav-item"><a class="nav-link" href="profile/resumes/saved">Избранное резюме</a></li>
            <li class="nav-item"><a class="nav-link" href="/vacancies">Вакансии</a></li>
            <li class="nav-item"><a class="nav-link" href="/resumes">Резюме</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${username}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a style="text-decoration: underline" href="#" class="dropdown-item">${username}</a>
                    <div class="dropdown-divider"></div>
                    <a style="color:red" href="/logout" class="dropdown-item">Выйти</a>
                </div>

            </li>
        </ul>

    </div>

</nav>
<div class="jumbotron jumbotron-fluid" style="background-color: rgba(200, 200, 200, 0.5)">
    <div class="container">
        <h1 class="display-3">Это ваш профиль</h1>
        <p>Здесь вы можете быстро переходить по нужным вам разделам</p>
        <#if !confirmed>
            <p style="color: crimson"> Ваш аккаунт не подтвержден. Подтвердите аккаунт по ссылке, отправленной на электронную почту</p>
        </#if>
    </div>
</div>
<footer class="footer text-center bg-light">
    <i class="fa fa-copyright"></i> Copyright:<a onclick="openInNewTab('https://vk.com/sudden_appearance')">@sudden_appearance</a>
</footer>
</body>
</html>