<%@ page import="java.util.List" %>
<%@ page import="com.wipro.document.bean.DocumentBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Documents</title>
</head>
<body>

<h2>All Document Records</h2>
<%
    List<DocumentBean> list = (List<DocumentBean>) request.getAttribute("documentList");
    String message = (String) request.getAttribute("message");
    if (list != null && !list.isEmpty()) {
%>
<table border="1"  align="center" cellpadding="8">
<tr>
    <th>Document ID</th>
    <th>Title</th>
    <th>Type</th>
    <th>Submitter</th>
    <th>Submit Date</th>
    <th>Status</th>
    <th>Remarks</th>
</tr>
<%
    for (DocumentBean bean : list) {
%>
<tr>
    <td><%= bean.getDocumentId() %></td>
    <td><%= bean.getTitle() %></td>
    <td><%= bean.getType() %></td>
    <td><%= bean.getSubmitter() %></td>
    <td><%= bean.getSubmitDate() %></td>
    <td><%= bean.getStatus() %></td>
    <td><%= bean.getRemarks() %></td>
</tr>
<%
    }
%>
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
