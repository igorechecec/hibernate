<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="shortcut icon" type="image/gif" href="/resources/favicon.ico"/>
    <link rel="stylesheet" href="resources/style.css"/>
    <link rel="stylesheet" href="resources/bootstrap.min.css"/>
</head>
    <body>
        <form action="/login" method="post">
            <div class="form-group row">
                <label for="input-email" class="col-sm-2 col-form-label">Login</label>
                <div class="col-sm-10">
                    <input name="login" type="text" class="form-control" id="input-email" aria-describedby="emailHelp" placeholder="Enter login">
                </div>
            </div>
            <div class="form-group row">
                <label for="input-password" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <input name="password" type="password" class="form-control" id="input-password" placeholder="Enter password">
                </div>
            </div>
            <div class="text-right mb-3">
                <button type="submit" class="btn btn-primary">Sign In</button>
            </div>
        </form>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>