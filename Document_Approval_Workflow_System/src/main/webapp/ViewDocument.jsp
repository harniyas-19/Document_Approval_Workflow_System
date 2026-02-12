<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Document</title>
</head>
<body>
<form method="post" action="MainServlet">
Title:<input type="text" name="title" required><br><br>
Submission Date:<input type="date" name="submitDate" required><br><br>
<input type="hidden" name="operation" value="viewRecord">
<input type="submit" value="Search">
</form>
</body>
</html>