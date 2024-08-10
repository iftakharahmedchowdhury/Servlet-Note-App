<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Notes</h2>

    <div class="list-group">
        <c:forEach var="note" items="${notes}">
            <div class="list-group-item">
                <p>${note.content}</p>
                <c:if test="${sessionScope.role == 'teacher'}">
                        <a href="note/edit?id=${note.id}" class="btn btn-primary mr-2">Edit</a>
                        <a href="note/delete?id=${note.id}" class="btn btn-danger ">Delete</a>
                </c:if>
            </div>
        </c:forEach>
    </div>

    <h3 class="mt-5 mb-3">Add a New Note</h3>
    <form action="notes" method="post">
        <div class="form-group">
            <textarea class="form-control" name="content" rows="4" required></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Add Note</button>
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
    </form>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
