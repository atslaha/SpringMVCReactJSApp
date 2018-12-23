<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SpringApp</title>
    <spring:url value="/resources/mytheme/css/style.css" var="styleCSS" />
    <link href="${styleCSS}" rel="stylesheet" />

</head>
<body>
<%--<div>--%>
<%--<form  method="POST" action="uploadFile" enctype="multipart/form-data">--%>
<%--File to upload: <input type="file" name="file" accept="text/plain"><br />--%>
<%--Name: <input type="text" name="name"><br />--%>
<%--<input type="submit" value="Upload"> Press here to upload the file!--%>
<%--</form>--%>
<%--</div>	--%>
<%----%>
<%--<h3>--%>
<%--${message} ${name} --%>
<%--</h3>--%>
<%----%>
<%--<a href="parse"> Parse File</a> <a href="list">  Show Lines Statistics</a>--%>
<%--<h3>${messageEx}</h3>--%>
<%----%>
<%--<h2>List of Lines</h2>  --%>
<%--<table>--%>
<%--<tr>--%>
<%--<td width="700px">Lines<td>Longest word</td><td>Shortest word</td><td>Line Length</td><td>Average Word Length</td><td width="40px"></td>--%>
<%--</tr>--%>
<%--<c:forEach items="${listLines}" var="listLine">--%>
<%--<tr>--%>
<%--<td>${listLine.line}</td>--%>
<%--<td>${listLine.longest_word}</td>--%>
<%--<td>${listLine.shortest_word}</td>--%>
<%--<td>${listLine.line_length}</td>--%>
<%--<td>${listLine.average_w_length}</td>--%>
<%--<td><a href="<c:url value='/delete-${listLine.line_id}-line' />">delete</a></td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>

<div id="app" width="1000px"></div>
<script type="text/javascript" src="/resources/js/index_bundle.js"></script></body>


</body>
</html>