<html>
    <head>
        <title>Add new user</title>
        <link rel="shortcut icon" type="image/gif" href="/resources/favicon.ico"/>
        <link href="resources/bootstrap.min.css" rel="stylesheet"/>
        <link href="resources/add_user.css" rel="stylesheet"/>
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
        <div class="form-wrapper">
            <h1>Add user</h1>
            <form action="/add" method="post">
<!--                <input type="hidden" name="user_id" value="${user.getId()}">-->
<!--                <input type="hidden" name="login" value="${user.getLogin()}">-->
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Enter login" value="${user.getLogin()}" required/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" value="${user.getPassword()}" required/>
                </div>
                <div class="form-group">
                    <label for="password-again">Password again</label>
                    <input type="password" class="form-control" id="password-again" name="password-again" placeholder="Enter password" value="${user.getPassword()}" required/>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" value="${user.getEmail()}" required/>
                </div>
                <div class="form-group">
                    <label for="firstname">First name</label>
                    <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Enter first name" value="${user.getFirstName()}" required/>
                </div>
                <div class="form-group">
                    <label for="lastname">Last name</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Enter last name" value="${user.getLastName()}" required/>
                </div>
                <div class="form-group">
                    <label for="birthday">Birthday</label>
                    <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Enter bithday" value="${user.getBirthday()}" required/>
                </div>
                <div class="form-group">
                    <label for="login">Role</label>
                    <select class="custom-select" required name="role">
                        <option value="">Choose role</option>
                        <option value="Admin">Admin</option>
                        <option value="User">User</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add User</button>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>