
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/styles.css">
    <title>Status</title>
</head>
<body>
    <h1>Status</h1>
    <section>
        <form action="${pageContext.request.contextPath}/admin/status" method="post">
            <label for="statusName">Status Name:</label>
            <input type="text" id="statusName" name="statusName">
            <button type="submit" class="btn-submit">Submit</button>
        </form>
    </section>
</body>
</html>
