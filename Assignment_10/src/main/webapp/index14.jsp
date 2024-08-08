<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSON Data Handling</title>
    <script>
        function sendData() {
            // Get the input values
            var name = document.getElementById("name").value;
            var age = document.getElementById("age").value;

            // Create a JSON object
            var data = {
                "name": name,
                "age": parseInt(age)
            };

            // Send the data using fetch
            fetch('question144', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                // Display the response
                document.getElementById("response").innerText = JSON.stringify(data, null, 2);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</head>
<body>
    <h1>JSON Data Handling Example</h1>
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
