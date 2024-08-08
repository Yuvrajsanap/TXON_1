<!DOCTYPE html>
<html>
<head>
    <title>Database Operations</title>
</head>
<body>
    <h1>Database Operations</h1>
    
    <h2>Create User</h2>
    <form method="post" action="Question13?action=create">
        Name: <input type="text" name="name"><br>
        Email: <input type="text" name="email"><br>
        <input type="submit" value="Create User">
    </form>

    <h2>Update User</h2>
    <form method="post" action="Question13?action=update">
        User ID: <input type="text" name="id"><br>
        New Name: <input type="text" name="name"><br>
        New Email: <input type="text" name="email"><br>
        <input type="submit" value="Update User">
    </form>

    <h2>Delete User</h2>
    <form method="post" action="Question13?action=delete">
        User ID: <input type="text" name="id"><br>
        <input type="submit" value="Delete User">
    </form>

    <h2>Read Users</h2>
    <form method="get" action="Question13">
        <input type="hidden" name="action" value="read">
        <input type="submit" value="Read Users">
    </form>
</body>
</html>
