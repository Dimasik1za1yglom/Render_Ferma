<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Задачи</title>
</head>
<body>
<form method="post" action="/task">
    <input type="text" name="name" placeholder="name task">
    <input type="submit" value="Добавить задачу">
</form>
<h2 style="color:red"><#if error??>${error}<#else></#if></h2>
<br>
<table>
    <tr>
        <th>Id</th>
        <th>Название</th>
    </tr>
    <#list tasks as task>
        <tr>
            <td>${task.id}</td>
            <td>${task.name}</td>
        </tr>
    </#list>
</table>
<hr>
<h3>История статусов задач</h3>
<table>
    <tr>
        <th>Название</th>
        <th>Cтатус</th>
        <th>Время изменения статуса</th>
    </tr>
    <#list taskStatuses as taskStat>
        <tr>
            <td>${taskStat.task.name}</td>
            <td>${taskStat.status.name}</td>
            <td>${taskStat.dataTime}</td>
        </tr>
    </#list>
</table>
</body>
</html>