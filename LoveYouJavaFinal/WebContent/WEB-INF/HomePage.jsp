<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Love You Java - Home</title>
</head>
<body>
<h1 align="center" >Welcome to Love You Java</h1>




<center>
<table border="1">
<c:forEach items="${fileDetailList}" var="fileObject">
    
   <tr><td> <a href="<c:url value="/resources/${fileObject.filePath}" /> " target="_blank" >${fileObject.fileTitle}</a></td></tr>
</c:forEach>
</table>
</center>

</body>
</html>