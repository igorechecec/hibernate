<html>
    <head>
        <title>User Home</title>
        <link rel="shortcut icon" type="image/gif" href="/resources/favicon.ico"/>
        <link href="resources/style.css" rel="stylesheet"/>
        <link href="resources/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="wrapper">
            <h1>Hello <%= request.getAttribute("user_name")%>!</h1>
            <div class="logout_block">
                Click <a href="/logout">here</a> to log out.
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>