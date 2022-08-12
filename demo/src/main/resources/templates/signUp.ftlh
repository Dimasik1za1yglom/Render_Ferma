<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Регистрация</title>
</head>
<body>
<center>
    <h3>Регистрация</h3>
    <h2 style="color:red"><#if error??>${error}<#else></#if></h2>
    <form method="post" action="/signup">
        <label>
            Login: <input type="email" name="login" placeholder="Введите ваш E-mail">
        </label>
        <br>
        <label>
            Password: <input type="password" name="password" placeholder="Пароль">
        </label>
        <br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</center>
<br>
<form method="get" action="/login">
    <input type="submit" value="Страница входа.">
</form>
</body>
</html>