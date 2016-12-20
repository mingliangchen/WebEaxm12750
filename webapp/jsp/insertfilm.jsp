<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<a href="<%=path%>/index.jsp">返回首页</a>
	<form action="<%=path%>/FilmServlet" method="get">
		<input name="command" value="insert" style="display: none;">
		<!-- film_id:<input name="film_id" type="text" ><br> -->
		title:<input name="title" type="text" ><br>
		description:<input name="description" type="text" ><br>
		language:<select name="language_id">
			<c:forEach items="${languages}" var="ls">
				<c:choose>
					<c:when test="${film.language_id == ls.language_id}">
						<option value="${ls.language_id}" selected="selected">${ls.name}${ls.language_id}</option>
					</c:when>
					<c:otherwise>
						<option value="${ls.language_id}">${ls.name}${ls.language_id}</option>
					</c:otherwise>
				</c:choose>

			</c:forEach>
		</select><br>

		<button type="submit">提交</button>
		<br>
		<button type="reset">重置</button>
		<br>

	</form>
</body>
</html>