<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="resources/style.css"/>
        <link rel="stylesheet" href="resources/bootstrap.min.css"/>
    </head>
    <body>
    <h1>This if first page!</h1>
        <form action="/login" method="post">
            <div class="form-group row">
                <label for="exampleInputEmail1" class="col-sm-2 col-form-label">Login</label>
                <div class="col-sm-10">
                    <input name="login" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Login">
                </div>
            </div>
            <div class="form-group row">
                <label for="exampleInputPassword1" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
            </div>
            <div class="text-right mb-3">
                <button type="submit" class="btn btn-primary">Sign In</button>
            </div>
        </form>
    </body>

</html>