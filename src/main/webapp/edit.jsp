<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Note</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Edit Note</h2>
    <form action="${pageContext.request.contextPath}/note/edit" method="post">
        <input type="hidden" name="id" value="${id}" />
        <div class="form-group">
            <label for="note">Note Content</label>
            <textarea id="note" name="note" class="form-control" rows="6" required>${note}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update Note</button>
        <a href="../notes" class="btn btn-danger">Cancel</a>
    </form>
</div>
</body>
</html>

