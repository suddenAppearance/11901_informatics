<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>${vacancy.getName()}. HH</title>
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

        .error-block {
            opacity: 0;
            margin-top: 10%;
            width: 100%;
            background-color: indianred;
            border-radius: 5px;
            border: solid crimson;
            transition: all 1s ease;
            transition-property: opacity, margin-top;
        }

        .error-div {
            position: absolute;
            right: 0;
            top: 20%;
            max-height: 40%;
            overflow: hidden;
        }
    </style>
    <script>

        function like() {
            $.ajax({
                type: "post",
                url: "/vacancy/like",
                data: {
                    "id": ${vacancy.getId()}
                },
                success: function (response) {
                    let a = document.getElementById("like")
                    a.style.color = "red";
                    document.getElementById("btn_like").onclick = unlike
                }
            })
        }

        function unlike() {
            $.ajax({
                type: "post",
                url: "/vacancy/unlike",
                data: {
                    "id": ${vacancy.getId()}
                },
                success: function (response) {
                    let a = document.getElementById("like")
                    a.style.color = "grey";
                    document.getElementById("btn_like").onclick = like
                },
                contentType: "application/x-www-form-urlencoded"

            })
        }

        function openInNewTab(url) {
            let win = window.open(url, '_blank');
            win.focus();
        }


        function show(id) {
            let a = document.getElementById(id)
            setTimeout(function (a) {
                a.style.opacity = "1"
                a.style.marginTop = "5%"
            }, 100, a)
            return id
        }
    </script>
</head>
<body onload="document.getElementById('navbarDropdown').click(); document.getElementById('navbarDropdown').blur()">
<nav class="navbar sticky-top navbar-light bg-light navbar-expand-md">
    <a href="/profile" class="navbar-brand">HeadHunters</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <#if username??>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
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

                </li>
            </ul>
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
<div class="container-fluid" style="margin-top: 1.2em">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header font-weight-bold" style="font-size: xx-large">
                    <div class="row">
                        <div class="col-xs-12 col-lg-8 col-md-8 col-sm-7">
                            ${vacancy.getName()}
                        </div>
                        <div class="col-xs-12 col-lg-4 col-md-4 col-sm-5 text-xl-right text-lg-right text-md-right text-sm-right">
                            <button id="btn_like" class="btn btn-outline-primary"
                                    <#if username??>

                                <#if isLiked>
                                    onclick="unlike()"
                                <#else>
                                    onclick="like()"
                                </#if>
                                    <#else>
                                    </#if>>
                                <i class="fa fa-heart" id="like"
                                        <#if isLiked>
                                            style="color: red" <#else>
                                            style="color: grey"
                                        </#if>
                                >
                                </i>
                                Добавить в избранное
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row col-12">
                        <label style="color:grey">График работы: </label>
                        <#if vacancy.getSchedule()??>
                            ${vacancy.getSchedule()}
                        </#if>
                    </div>
                    <div class="row col-12">
                        <label style="color:grey">Тип работы: </label>
                        <#if vacancy.getType()??>
                            ${vacancy.getType()}
                        </#if>
                    </div>
                    <div class="row col-12">
                        <label style="color:grey">График выплат: </label>
                        <#if vacancy.getPaymentSchedule()??>
                            ${vacancy.getPaymentSchedule()}
                        </#if>
                    </div>
                    <div class="row col-12">
                        <label style="color:grey">Где предстоит работать: </label>
                        <#if vacancy.getPlace()??>
                            ${vacancy.getPlace()}
                        </#if>
                    </div>
                    <div class="row col-12">
                        <label style="color:grey">Опыт работы: </label>
                        <#if vacancy.getExperience()?? && vacancy.getExperience() != 0>
                            ${vacancy.getExperience()}   лет(года)
                        <#else>
                            не требуется
                        </#if>
                    </div>


                    <div class="row col-12">
                        <label style="color:grey">Адрес: </label>
                        <#if vacancy.getAddress()??>
                            <a style="color: #11a3fc; text-decoration: underline; cursor: pointer"
                               onclick="openInNewTab('https://yandex.ru/maps/1/moscow-and-moscow-oblast/search/${vacancy.getAddress()}/')">${vacancy.getAddress()}
                                <i class="fa fa-map-marker"></i></a>
                        </#if>
                    </div>
                    <div class="row col-12">
                        <#if vacancy.getDescription()??>
                            <div class="row col-12">
                                <p class="font-weight-bold">Описание</p>
                            </div>
                            <div class="col-12 border mb-3" style="border-radius: 5px">
                                <pre class="mb-0">${vacancy.getDescription()}</pre>
                            </div>
                        </#if>
                        <#if vacancy.getRequirements()??>
                            <div class="row col-12">
                                <p class="font-weight-bold">Требования</p>
                            </div>
                            <div class="col-12 border mb-3" style="border-radius: 5px">
                                <pre class="mb-0">${vacancy.getRequirements()}</pre>
                            </div>
                        </#if>
                    </div>
                    <div class="row col-12">
                        <label style="color:grey">Контактная информация: </label>
                        <#if vacancy.getContactInfo()??>
                            ${vacancy.getContactInfo()}
                        </#if>
                    </div>
                    <div class="row col-12" style="display: block">
                        <#if vacancy.getSalary()?? && vacancy.getSalary() != 0>
                            <div class="card-text font-weight-bold float-right"
                                 style="font-size: xx-large">${vacancy.getSalary()} р/мес.
                            </div>
                        <#else>
                            <div class="card-text font-weight-bold float-right"
                                 style="font-size: xx-large">Зарплата не указана
                            </div>
                        </#if>
                    </div>
                    <#if owner>
                        <div class="row col">
                            <form action="/vacancy/edit" method="get" style="margin-right: 20px">
                                <input type="hidden" name="id" value="${vacancy.getId()}"/>
                                <button class="btn btn-primary" type="submit">
                                    Редактировать
                                </button>
                            </form>
                            <form action="/vacancy/delete" method="post">
                                <input type="hidden" name="id" value="${vacancy.getId()}"/>
                                <button class="btn btn-danger" type="submit">
                                    Удалить
                                </button>
                            </form>
                        </div>
                    </#if>
                </div>

                <footer class="card-footer text-center" style="color: grey">
                    ${vacancy.getCreated()}
                </footer>
            </div>
        </div>
    </div>
</div>
<div id="errors" class="error-div">

</div>
<footer class="footer text-center">
    <i class="fa fa-copyright"></i> Copyright:<a onclick="openInNewTab('https://vk.com/sudden_appearance')">@sudden_appearance</a>
</footer>
</body>
</html>
