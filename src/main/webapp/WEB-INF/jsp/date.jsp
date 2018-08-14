<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    	<input type="text" class="demo-input" placeholder="请选择日期" id="test1">


			<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script> <!-- 改成你的路径 -->
			<script>
			lay('#version').html('-v'+ laydate.v);
			
			//执行一个laydate实例
			laydate.render({
			  elem: '#test1' //指定元素
			});
			</script>
 	</body>
</html>