<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
</head>
<body>
	<table id="tab" border="1px" width="65%" align="center">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th></th>
		</tr>
		
		<c:forEach items="${userList}" var="u">
			<tr>
				<th>${u.id}</th>
				<th>${u.name}</th>
				<th>${u.age}</th>
				<th>${u.sex}</th>
			</tr>
		</c:forEach>
	</table>


	<script type="application/javascript" src="/js/jquery-3.4.1.min.js"></script>
	<script type="application/javascript">
		$(function () {
			getObjects()
		})

		function getObjects() {
			// modeGet()
			modeAjax()
		}

		function modeGet() {
			$.get("findAjax", function (result) {
				showPerTrs(result)
			})
		}
		
		function modeAjax() {
			$.ajax({
				type: "get",
				url: "findAjax",
				async: true,
				success: function (result) {
					showPerTrs(result)
				},
				error: function () {
					alert("请求异常...")
				}
			})
		}

		function showPerTrs(result) {
			console.log(result)
			let trs = ""
			for(let user of result){
				<%--trs += `<tr align="center">--%>
				<%--			<td>${uer.id}</td>--%>
				<%--			<td>${user.name}</td>--%>
				<%--			<td>${user.age}</td>--%>
				<%--			<td>${user.sex}</td>--%>
				<%--		</tr>`--%>
				trs += "<tr align='center'><td>"+user.id+"</td><td>"+user.name+"</td><td>"+user.age+"</td><td>"+user.sex+"</td></tr>"
			}
			$("#tab").append(trs)

		}
	</script>
</body>
</html>