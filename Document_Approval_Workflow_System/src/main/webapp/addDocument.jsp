<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
</head>
<body>
<form method="post" action="MainServlet">
Title:<input type="text" name="title" required>
Type:<select name="type">
    <option value="Memo">Memo</option>
    <option value="Report">Report</option>
    <option value="Request">Request</option>
</select>
Submitter:<input type="text" name="submitter" required>
Submission Date:<input type="date" name="submitDate" required>
Remarks:<input type="text" name="remarks">
<input type="submit" value="Submit">
<input type="hidden" name="operation" value="newRecord">
</form>
</body>
</html>