<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="container">
    <section id="content">
        <form action="j_security_check" method="post">
            <h1>Authorization</h1>
            <h5 class="err" style="margin-bottom:5px">
                Invalid username or password. Try again.
            </h5>
            <div>
                <input type="text" placeholder="Username" id="username" name="j_username" autofocus required>
            </div>
            <div>
                <input type="password" placeholder="Password" id="password" name="j_password" required>
            </div>
            <div>
                <input type="submit" value="Log in">
                <!--<a href="#">Forgot your password?</a>-->
            </div>
        </form>
    </section>
</div>
</body>
</html>