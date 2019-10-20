<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ext" uri="./../WEB-INF/../user.tld"%>
<html>
    <head>
        <title>Admin Home</title>
        <link rel="shortcut icon" type="image/gif" href="/resources/favicon.ico"/>
        <link href="resources/bootstrap.min.css" rel="stylesheet"/>
        <link href="resources/users.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="header">
            <div class="inner_wrapper">
                <span class="user">
                    <%= request.getAttribute("user_name") %>
                </span>
                <span>(</span><a href="/logout">Logout</a><span>)</span>
            </div>
        </div>
        
        <ext:user users="${users_list}"/>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>