<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Request Details</title>
</head>
<body class="request-details">

<div class="request-info">
    <h3>Request Information</h3>

    <table border="1">
        <tr>
            <th>ID</th>
            <td>${request.id}</td>
        </tr>
        <tr>
            <th>Project</th>
            <td>${request.project}</td>
        </tr>
        <tr>
            <th>Amount Loan</th>
            <td>${request.amountLoan}</td>
        </tr>
        <tr>
            <th>Duration</th>
            <td>${request.duration}</td>
        </tr>
        <tr>
            <th>Monthly</th>
            <td>${request.monthly}</td>
        </tr>
    </table>
</div>

<div class="user-info">
    <h3>User Information</h3>

    <%--user unformations--%>
    <table border="1">
        <tr>
            <th>Requester</th>
            <td>${request.first_name}</td>
        </tr>
        <tr>
            <th>Work</th>
            <td>${request.work}</td>
        </tr>
        <tr>
            <th>CIN</th>
            <td>${request.cin}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${request.email}</td>
        </tr>
        <tr>
            <th>Phone</th>
            <td>${request.phone}</td>
        </tr>
    </table>
</div>

<div class="status-update">
    <h3>Update Status</h3>

    <!-- Form to Update Request Status -->
    <form action="${pageContext.request.contextPath}/admin/request-details?id=${request.id}" method="POST">
        <label for="status">Status:</label>
        <select name="status" id="status">
            <c:forEach var="statusOption" items="${allStatuses}">
                <option value="${statusOption.name}">${statusOption.name}</option>
            </c:forEach>
        </select>
        <br>
        <label for="description">Description:</label>
        <textarea name="description" id="description"></textarea>
        <br>
        <input type="submit" value="Update Status">
    </form>
</div>

<div class="status-history">
    <h3>Status History</h3>

    <!-- Table to Display Status History -->
    <table border="1">
        <thead>
        <tr>
            <th>Status</th>
            <th>Changed Date</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="status" items="${requestStatuses}">
            <tr>
                <td>${status.status.name}</td>
                <td>${status.changedDate}</td>
                <td>${status.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
