<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>XML Data Handling</title>
    <script>
        function sendData() {
            // Get the XML data from the form
            var xmlData = '<person><name>' + document.getElementById("name").value + '</name><age>' + document.getElementById("age").value + '</age></person>';

            // Send the XML data using fetch
            fetch('xml15', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/xml'
                },
                body: xmlData
            })
            .then(response => response.text())
            .then(data => {
                // Display the response
                document.getElementById("response").innerText = data;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</head>
<body>
    <h1>XML Data Handling Example</h1>
    <form onsubmit="event.preventDefault(); sendData();">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required><br><br>
        <input type="submit" value="Send Data">
    </form>
    <h2>Response</h2>
    <pre id="response"></pre>
</body>
</html>
