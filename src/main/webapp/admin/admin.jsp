<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dutch auction (admin)</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
    <link href="/static/css/sticky-footer.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script>
        function getCookie(name) {
            var re = new RegExp(name + "=([^;]+)");
            var value = re.exec(document.cookie);
            return (value != null) ? (value[1]) : null;
        }
        $(document).ready(function () {
            var gameState = null;
            $('#create').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/create',
                    success: function (data) {
                        gameState = data;
                        document.cookie = "gameId=" + gameState;
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#start').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/start',
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#stop').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/stop',
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            (function () {
                setInterval(function () {
                    $.ajax({
                        type: 'POST',
                        url: '/game/state/' + getCookie("gameId"),
                        success: function (data) {
                            console.log(data);
                            if(data == ''){
                                document.getElementById("state").innerHTML = "Game not started!!!!!";
                            }
                            else {
                                document.getElementById("state").innerHTML = 'Match: ' + data.matchId + ' | Set: ' + data.setId + ' | Game: ' + data.gameId + ' | Product: ' + data.productName + ' | Price: ' + data.price;
                            }
                        },
                        error: function () {
                        }
                    })
                }, 1500);
            })();
            $('#users').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/users/' + getCookie("gameId"),
                    success: function (data) {
                        console.log(data);
                        $.each(data, function (index, element) {
                            $('body').append($('<div>', {
                                text: 'User ID: ' + element.id + ' | Name: ' + element.name
                            }));
                        });
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#price').on('click', function () {
                var price = $("#newPrice").val();
                $.ajax({
                    type: 'POST',
                    url: '/game/price/' + getCookie("gameId") + '/' + price,
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#beginGame').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/beginGame/' + getCookie("gameId"),
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#endGame').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/endGame/' + getCookie("gameId"),
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
        });
    </script>
</head>

<body>
<a href="#">Logged as: <sec:authentication property="principal.username"/></a>
<a class="btn btn-default" href="<c:url value="/logout" />" role="button">Logout</a>
<br>
<!--<a class="btn btn-default" href="<c:url value="/game/create" />" role="button">Create</a>
    <a class="btn btn-default" href="<c:url value="/admin/start" />" role="button">Start</a>
    <a class="btn btn-default" href="<c:url value="/admin/stop" />" role="button">Stop</a>-->
<button id="create" class="btn btn-default">Create</button>
<button id="start" class="btn btn-default">Start</button>
<button id="stop" class="btn btn-default">Stop</button>
<button id="users" class="btn btn-default">Users</button>

<input type="text" id="newPrice"/>
<button id="price" class="btn btn-default">New Price</button>
<button id="beginGame" class="btn btn-default">Begin new game</button>
<button id="endGame" class="btn btn-default">End game</button>
<div id="state"></div>
</body>
</html>
