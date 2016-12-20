<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<a href="<%=path%>/index.jsp">返回首页</a>
	<a href="<%=path%>/FilmServlet?command=to_add">add</a>
	<table>
		<tbody>
			<tr>
				<th>film_id</th>
				<th>title</th>
				<th>description</th>
				<th>language</th>
				<th>operation</th>
			</tr>
			<c:forEach items="${filmlist}" var="fl">
				<tr>
					<td>${fl.film_id}</td>
					<td>${fl.title}</td>
					<td>${fl.description}</td>
					<td>${fl.name}</td>
					<td><a
						href="<%=path%>/FilmServlet?command=getOneFilm&film_id=${fl.film_id}">修改</a>&nbsp;&nbsp;
						<a
						href="<%=path%>/FilmServlet?command=delete&film_id=${fl.film_id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>