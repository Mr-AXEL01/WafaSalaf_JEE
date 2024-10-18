<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Admin-manage requests</title>
</head>
<body class = "admin-requests">
<h1>ALL Requests</h1>
<form method="get" action="${pageContext.request.contextPath}/admin/requests">
    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="">All</option>
        <option value="PENDING">Pending</option>
        <option value="APPROVED">Approved</option>
        <option value="REJECTED">Rejected</option>
    </select>

    <label for="hiring_date">Hiring Date:</label>
    <input type="date" name="hiring_date" id="hiring_date" />

    <input type="submit" value="Filter" />
</form>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Project</th>
        <th>Work</th>
        <th>Amount Loan</th>
        <th>Duration</th>
        <th>Status</th>
        <th>Hiring Date</th>
        <th>Detail</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requests}">
        <tr>
            <td>${request.id}</td>
            <td>${request.project}</td>
            <td>${request.work}</td>
            <td>${request.amountLoan}</td>
            <td>${request.duration}</td>
            <td>
                <c:set var="latestStatus" value="${null}"/>
                <c:set var="latestChangedDate" value="${null}"/>
                <c:forEach var="status" items="${request.requestStatuses}">
                    <c:choose>
                        <c:when test="${latestChangedDate == null || status.changedDate > latestChangedDate}">
                            <c:set var="latestChangedDate" value="${status.changedDate}"/>
                            <c:set var="latestStatus" value="${status.status.name}"/>
                        </c:when>
                    </c:choose>
                </c:forEach>
                    ${latestStatus}
            </td>
            <td>${request.hiringDate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/request-details?id=${request.id}">Details</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
