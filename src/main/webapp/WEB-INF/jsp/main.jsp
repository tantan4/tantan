<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dream.ssh.dto.MenuDto"%>
<%
	List<MenuDto> menus = (List<MenuDto>) request.getAttribute("menus");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理系统</title>

   

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
	
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/datatables-responsive/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/dist/css/sb-admin-2.min.css"
	rel="stylesheet">
 
<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/sb-admin2/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- bootstrap validator -->
    <link href="${pageContext.request.contextPath}/sb-admin2/vendor/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
                
    <![endif]-->
<style type="text/css">
</style>
</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="javascript:void(0);"><i
				class="fa fa-user 
  fa-fw"></i><span id="loginName"
				myname="${loginUser.name }"></span></a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right" id="loginUl">
			<!-- /.dropdown -->
			<li class="dropdown" id="loginOut"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="javascript:reset();"><i
							class="fa fa-gear fa-fw"></i> 修改密码</a></li>
					<li class="divider"></li>
					<li><a href="${pageContext.request.contextPath}/logout"><i
							class="fa fa-sign-out fa-fw"></i> 退出</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<li class="dropdown"><span id="nowTime"></span></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control input-sm"
								placeholder="Search/搜一搜"> <span class="input-group-btn">
								<button class="btn btn-default" type="button"
									style="height: 30px;">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<%
						for (MenuDto m : menus) {
					%>
					<li>
						<%
							if (null != m.getChildren() && m.getChildren().size() > 0) {
						%> <a href="#" onclick="return false;"
						class="<%=m.getActiveCls()%>"><i
							class="fa <%=m.getIcon()%> fa-fw"></i> <%=m.getName()%><span
							class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<%
								for (MenuDto c : m.getChildren()) {
							%>
							<li>
						<%
							if (null != c.getChildren() && c.getChildren().size() > 0) {
						%> <a href="#" onclick="return false;"
						class="<%=c.getActiveCls()%>"><i
							class="fa <%=c.getIcon()%> fa-fw"></i> <%=c.getName()%><span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<%
								for (MenuDto cc : c.getChildren()) {
							%>
							<li><a href="#" onclick="return false;"
								menuURL="<%=cc.getUrl()%>" class="<%=cc.getActiveCls()%>"><i
									class="fa <%=cc.getIcon()%> fa-fw"></i> <%=cc.getName()%></a></li>
							<%
								}
							%>
						</ul> <%
 	} else {
 %>
					
					<li><a href="#" onclick="return false;"
						menuURL="<%=c.getUrl()%>" class="<%=c.getActiveCls()%>"><i
							class="fa <%=c.getIcon()%> fa-fw"></i> <%=c.getName()%></a></li>
					<%
						}
					%>
					</li>
							<%
								}
							%>
						</ul> <%
 	} else {
 %>
					
					<li><a href="#" onclick="return false;"
						menuURL="<%=m.getUrl()%>" class="<%=m.getActiveCls()%>"><i
							class="fa <%=m.getIcon()%> fa-fw"></i> <%=m.getName()%></a></li>
					<%
						}
					%>
					</li>
					<%
						}
					%>

				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper"></div>
		
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/jquery/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/sb-admin2/vendor/datatables-responsive/dataTables.responsive.js"></script>
	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/sb-admin2/dist/js/sb-admin-2.min.js"></script>
<!-- bootstrap validator -->
    <script src="${pageContext.request.contextPath}/sb-admin2/vendor/bootstrap-validator/js/bootstrapValidator.min.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document)
				.ready(
						function() {
							var login = $('#loginName').attr('myname');
							if (login == "") {
								$('#loginName').text("游客");
								$('#loginOut').hide();
								$('#loginUl')
										.append(
												" <li class='dropdown'><a href='${pageContext.request.contextPath}/login'><i class='fa fa-user fa-fw'></i> 请登录</a></li>");
							} else {
								$('#loginName').text(login);
							}
							$('#side-menu').on(
									'click',
									'li a',
									function() {
										$('#side-menu').find('li a')
												.removeClass('active');
										var url = $(this).attr('menuURL');
										if (url) {
											if (url == '/index') {
												$('#page-wrapper').load(
														'${pageContext.request.contextPath}'
																+ url);
											} else {
												$('#page-wrapper').load(
														'${pageContext.request.contextPath}'
																+ url);
											}
										}
										$(this).addClass('active');
									});
							$('#side-menu').find('li a.active').click();
							/* function clock(){
								  var time=new Date();
								  var week;
								  switch (time.getDay()){            <!--time.getDay()获取后显示1~7阿拉伯数字-->
								    case 1: week="星期一"; break;
								    case 2: week="星期二"; break;
								    case 3: week="星期三"; break;
								    case 4: week="星期四"; break;
								    case 5: week="星期五"; break;
								    case 6: week="星期六"; break;
								    default: week="星期天";
								  }
									var year=time.getFullYear();
									var month=time.getMonth();
									var date=time.getDate();
								        document.getElementById("#nowTime").innerHTML = "今天是："+year+"年"+(month+1)+"月"+date+"日  "+week;
								  }
								  setInterval(clock,1000);     <!--每隔1秒显示一次--> */
						});
		function reset() {
			$('#page-wrapper').load('${pageContext.request.contextPath}/reset');
		}
	</script>

</body>
</html>