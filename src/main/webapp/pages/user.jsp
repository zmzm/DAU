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

    <title>Dutch auction</title>

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
            var gameStateId = getCookie("gameId");
            var userId = getCookie("userId");
            var setId = null;
            (function () {
                setInterval(function () {
                    $.ajax({
                        type: 'POST',
                        url: '/game/state/' + gameStateId,
                        success: function (data) {
                            console.log(data);
                            if(data == ''){
                                document.getElementById("state").innerHTML = "Game not started!!!!!";
                            }
                            else {
                                setId = data.setId;
                                document.getElementById("state").innerHTML = 'Match: ' + data.matchId + ' | Set: ' + data.setId + ' | Game: ' + data.gameId + ' | Product: ' + data.productName + ' | Price: ' + data.price;
                            }
                        },
                        error: function () {
                        }
                    })
                }, 1500);
            })();
            $('#buy').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: '/game/buy/' + gameStateId + '/' + userId,
                    success: function () {
                    },
                    error: function () {
                        alert("Error");
                    }
                })
            });
            $('#joker').on('click', function () {
                var joker = $("#jokerValue").val();
                $.ajax({
                    type: 'POST',
                    url: '/game/joker/' + setId + '/' + joker + '/' + userId,
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

<script type="text/javascript">
    document.write("User ID: ");
    document.write(getCookie("userId"));
    document.write(" Game ID: ");
    document.write(getCookie("gameId"));
</script>
<button id="buy" class="btn btn-default">Buy</button>
<input type="text" id="jokerValue"/>
<button id="joker" class="btn btn-default">Joker</button>
<div id="state"></div>
</body>
</html>
