<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title> Love you java- Detail display </title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="/resources/style.css" type="text/css"/>
<meta name="viewport" content="width=device-width , initial-scale=1.0">
</head>
<style>

body{
/*background-image:url{'img/rahul.png'};*/
color:#000385;
font-size:87.5%;/*It means 87.5% of base font size 14px*/
font-family:Arial,'Lucida Sans Unicode';
line-height:1.5;
text-align:left;
}

a{
 text-decoration:none;
}

a:link,a:visited{
}

a:hover,a:active{
}

.body{
margin: 0 auto;
width: 70%;
clear:both;
}

.mainHeader img{
width:30%;
height: auto;
margin: 2% 0;
}

.mainHeader nav {
background-color:#666;
height:40px;
-moz-border-radius:5px;
-webkit-border-radius:5px;

}

.mainHeader nav ul {
list-style: none;
margin: 0 auto;
}
 
.mainHeader nav ul li {
float: left;
display: inline;
}


.mainHeader nav a:link, .mainHeader nav a:visited{
color: #FFF;
display: inline-block;
padding: 10px 25px;
height: 20px;
}

.mainHeader nav a:hover,.mainHeader nav a:active,
.mainHeader nav .active a:link,.mainHeader nav .active a:visited
{
background-color: #CF5C3F;
text-shadow: none;
}


</style>

<body class="body">
<header class="mainHeader">

<nav>
<ul>
<li class="active"><a href="/loveyoujavafinal/">Home</a> </li>

<li><a href="#">About us</a> </li>


</ul>
</nav>
</header>
<body>
<h1>${title}</h1>
<br>


<ul>
<c:forEach items="${titleContentList}" var="rowval">
<li>
 <c:out value="${rowval}"/>
 
</li>
<br>
</c:forEach>
</ul>




</body>
</html>