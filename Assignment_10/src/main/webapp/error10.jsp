<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
    <h1>Error</h1>
    <p>Sorry, an error occurred: <%= exception != null ? exception.getMessage() : "Unknown error" %></p>
    <p><a href="index10.html">Go back to home</a></p>
</body>
</html>
