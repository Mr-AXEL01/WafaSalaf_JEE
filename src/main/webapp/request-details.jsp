<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Request Details</title>
</head>
<body>
<h2>Request Details</h2>

<!-- Display Request Information -->
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
        <th>Work</th>
        <td>${request.work}</td>
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
        <th>Email</th>
        <td>${request.email}</td>
    </tr>
    <tr>
        <th>Phone</th>
        <td>${request.phone}</td>
    </tr>
    <!-- Add other request information as needed -->
</table>

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

</body>
</html>
