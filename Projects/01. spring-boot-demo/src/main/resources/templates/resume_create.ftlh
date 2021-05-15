<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создать резюме. HH</title>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/js/mdb.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    <style>
        body {
            background: url(https://avatanplus.com/files/resources/original/577c80ded65a0155be57669e.jpg)
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

        .month_pick {
            color: grey;
        }

        .month_pick:focus {
            color: blue;
        }
    </style>
    <script>
        function openInNewTab(url) {
            let win = window.open(url, '_blank');
            win.focus();
        }
    </script>
</head>
<body onload="onLoad()">
<nav class="navbar sticky-top navbar-light bg-light navbar-expand-md">
    <a href="/profile" class="navbar-brand">HeadHunters</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown prefix">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${username}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a style="text-decoration: underline" href="/profile"
                       class="dropdown-item"> ${username}</a>
                    <div class="dropdown-divider"></div>
                    <a style="color:red" href="/logout" class="dropdown-item">Выйти</a>
                </div>

            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid" style="margin-top: 1.2em">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Создание резюме
                </div>
                <div class="card-body">
                    <form onsubmit="submitForm()" action="/resume/create" id="resume_form" method="POST">
                        <div class="md-form">
                            <label class for="id_name">Название вакансии</label>
                            <input class="form-control" type="text" name="name" id="id_name" required/></div>
                        <div class="col form-group">

                            <input class="form-check-input" type="checkbox" name="readyToBusinessTrip"
                                   id="id_readyToBusinessTrip" value="true"/>
                            <label class="form-check-label" for="id_readyToBusinessTrip">Готовность к
                                командировкам</label>
                        </div>
                        <div class="col form-group">
                            <input class="form-check-input" type="checkbox" name="moving" value="true" id="id_moving"/>
                            <label class="form-check-label" for="id_moving">Готовность к переезду</label>
                        </div>
                        <div class="md-form">
                            <i></i>
                            <label class for="id_sphere">Сфера деятельности</label>
                            <input class="form-control" name="sphere" type="text" id="id_sphere"/></div>
                        <div class="md-form">
                            <i></i>
                            <label class for="id_schedule">График работы</label>
                            <input class="form-control" name="schedule" type="text" id="id_schedule"/></div>
                        <div class="md-form">
                            <i></i>
                            <label class for="id_type">Тип работы</label>
                            <input class="form-control" type="text" name="type" id="id_type"/></div>
                        <label class for="id_workplaces">
                            Опыт работы
                        </label>
                        <div id="id_workplaces" class="form-horizontal">

                        </div>
                            <i></i>
                            <label class for="id_description"> Описание</label>
                            <textarea class="form-control" name="description" id="id_description">

                            </textarea></div>
                        <div class="md-form">
                            <i></i>
                            <label class for="id_contact_info">Контактная информация</label>
                            <input class="form-control" type="text" name="contact_info" id="id_contact_info"/>
                        </div>
                        <div class="md-form">
                            <i></i>
                            <label class for="id_salary">Зароботная плата</label>
                            <input class="form-control" name="salary" id="id_salary" type="number"/></div>

                        <input type="button" class="btn btn-outline-primary" id="id_submit_resume"
                               value="Создать"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer text-center">
    <i class="fa fa-copyright"></i> Copyright:<a onclick="openInNewTab('https://vk.com/sudden_appearance')">@sudden_appearance</a>
</footer>
</body>
</html>