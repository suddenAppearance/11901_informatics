<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Резюме. HH</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
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
    <style>
        .card:hover .card-header {
            background-color: #fff895;
        }

        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            /* Set the fixed height of the footer here */
            height: 60px;
            line-height: 60px; /* Vertically center the text there */
            background-color: #f5f5f5;
            z-index: 10;
        }

        body {
            background: url(https://avatanplus.com/files/resources/original/577c80ded65a0155be57669e.jpg)
        }
    </style>
</head>
<body onload="document.getElementById('navbarDropdown').click(); document.getElementById('navbarDropdown').blur()">
<nav class="navbar sticky-top navbar-light bg-light navbar-expand-md">
    <a href="/profile" class="navbar-brand">HeadHunters</a>
    <#if username??>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/vacancies">Вакансии</a></li>
                <li class="nav-item"><a class="nav-link" href="/resumes">Резюме</a></li>
            </ul>
            <div class="nav-item dropdown ml-auto">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${username}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a style="text-decoration: underline" href="/profile"
                       class="dropdown-item">${username}</a>
                    <div class="dropdown-divider"></div>
                    <a style="color:red" href="/logout" class="dropdown-item">Выйти</a>
                </div>

            </div>
        </div>
    <#else>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/vacancies">Вакансии</a></li>
                <li class="nav-item"><a class="nav-link" href="/resumes">Резюме</a></li>
            </ul>
            <a href="/login" class="nav-link ml-auto">Войти</a>
        </div>
    </#if>


</nav>
<div class="container">
    <div class="row">
        <#list resumes as resume>

            <div class="col-lg-6 col-sm-12 col-12 col-xl-4 mb-1">
                <div class="card" onclick="window.location.assign('/resume?id=${resume.getId()}')">
                    <div class="card-header font-weight-bold">
                        ${resume.getName()}
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <#if resume.getSalary()?? && resume.getSalary() != 0>
                                ${resume.getSalary()}
                            <#else>
                                з.п. не указана
                            </#if>
                        </div>
                    </div>
                    <div class="card-footer" style="color: grey">
                        ${resume.getCreated()}
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
<footer class="footer text-center">
    <i class="fa fa-copyright"></i> Copyright:<a onclick="openInNewTab('https://vk.com/sudden_appearance')">@sudden_appearance</a>
</footer>
</body>
</html>