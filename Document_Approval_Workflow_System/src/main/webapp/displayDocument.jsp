<%@ page import="com.wipro.document.bean.DocumentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Document</title>
</head>
<body>

<h2>Document Details</h2>
<%
    DocumentBean bean = (DocumentBean) request.getAttribute("document");
    String message = (String) request.getAttribute("message");

    if (bean != null) {
%>
<table border="1" align="center" cellpadding="8">
    <tr><th>Document ID</th><td><%= bean.getDocumentId() %></td></tr>
    <tr><th>Title</th><td><%= bean.getTitle() %></td></tr>
    <tr><th>Type</th><td><%= bean.getType() %></td></tr>
    <tr><th>Submitter</th><td><%= bean.getSubmitter() %></td></tr>
    <tr><th>Submit Date</th><td><%= bean.getSubmitDate() %></td></tr>
    <tr><th>Status</th><td><%= bean.getStatus() %></td></tr>
    <tr><th>Remarks</th><td><%= bean.getRemarks() %></td></tr>
</table>
<%
    } else {
%>
<%= message %>


<%
    }
%>
<br><br>
<a href="Menu.html">Back to Menu</a>
</div>

</body>
</html>
