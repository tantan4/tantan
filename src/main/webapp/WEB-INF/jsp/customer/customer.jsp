<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.List" %>
<%@ page import="com.dream.ssh.dto.CustomerDto" %>
<%
	List<CustomerDto> customers = (List<CustomerDto>) request.getAttribute("customers"); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
</head>

<body>

    <div id="wrapper">

         <%@include file="head.jsp" %>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" style="height:34px;">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath }/index"><i class="fa fa-dashboard fa-fw" class="active"></i> 首页</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw" ></i> 用户<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                 <li>
                            <a href="${pageContext.request.contextPath }/user/update?id=${user.id}" ><i class="fa fa-edit fa-fw"></i> 修改用户</a>
                               </li>
                                <li>
                                     <a href="${pageContext.request.contextPath }/user/create" ><i class="fa fa-edit fa-fw"></i> 添加用户</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> 客户<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                 <li>
                            <a href="${pageContext.request.contextPath }/customer"><i class="fa fa-edit fa-fw" class="active"></i> 客户列表</a>
                               </li>
                                <li>
                            <a href="${pageContext.request.contextPath }/customer/create"><i class="fa fa-edit fa-fw" ></i> 添加客户</a>
                               </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> 其他<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">客户资料表</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a class="btn" href="${pageContext.request.contextPath }/customer/create">创建</a>
                            <a class="btn" href="javascript:deleteCustomers();">删除</a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        <form id="customerForm">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                   <tr>
                                   		<th width="45">选择</th>
                                        <th>客户名</th>
                                        <th>性别</th>
                                        <th>订单数</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                        <% for (CustomerDto u : customers) { %>
                                    <tr class="odd gradeX">
                                   	    <td><input type="checkbox" name="id" value="<%=u.getId()%>"></td>
                                        <td><%=u.getName()%></td>
                                          <td><%=u.getGender()%></td>
                                        <td><%=u.getNumber()%></td>
                                        <td><a href="${pageContext.request.contextPath}/customer/update?id=<%=u.getId()%>" class="btn">修改</a></td>
                                    </tr>
                                <% } %> 
                                   
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                           </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
         
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

   
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
        
    });
    function deleteCustomers() {
    	if (!confirm ('是否删除选中的客户？')) {
    		return;
    	}
    	$.post ('${pageContext.request.contextPath}/customer/delete', $('#customerForm').serializeArray(), function (result) {
    		if (result.success) {
    			location.reload ();
    		} else {
    			alert ('删除客户失败！');
    		}
    	});
    }
    </script>

</body>

</html>
