<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>用户管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="userTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>登录名</th>
                                        <th>姓名</th>
                                        <th>角色</th>
                                        <th width="120">操作</th>
                                    </tr>
                                </thead>
                            </table>
                            <!-- /.table-responsive -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <script>
            $(document).ready(function() {
            	var tableId = 'userTable';
            	// 初始化列表
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/user/list",
                        "type": "GET"
                    },
                    "order": [[ 1, "desc" ]] ,
                    "columns": [
                    	{ "data": "id" , "orderable" : false},
                    	{ "data": "loginName" },
                        { "data": "name" , "orderable" : false},
                        { "data": "roleNames" , "orderable" : false},
                        { "data": null , "orderable" : false, "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a> <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及UserDto
                    var data = table.row( $(this).parents('tr') ).data();
                	if ($(this).attr('name') == 'update') {
                		updateUser(data.id);
                	}
					if ($(this).attr('name') == 'delete') {
						deleteUser (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createUser();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteUsers();'>批量删除</a>");
            });
            
            function deleteUser (id) {
            	$.post ('${pageContext.request.contextPath}/user/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			/* location.reload (); */
		    			var tableId = 'userTable';
		    			 var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} else {
		    			alert ('删除用户失败！');
		    		}
		    	});
            }
            
            function updateUser (id) {
            	// 创建userDailog的div，如果之前存在，先删除
            	$('#userDialog').remove();
            	$('body').append ('<div class="modal fade" id="userDialog" tabindex="-1" role="dialog" aria-labelledby="userModalLabel"></div>');
            	// 
            	$('#userDialog').load ('${pageContext.request.contextPath}/user/update?id=' + id, function () {
            		// 调用modal控件的show方法
            		$('#userDialog').modal ('show');
            	});
            	/* $('#page-wrapper').load ('${pageContext.request.contextPath}/user/update?id=' + id); */
            }
            
            function createUser () {
            	/*$('#page-wrapper').load ('${pageContext.request.contextPath}/user/create');
            	 $('#page-wrapper').load ('${pageContext.request.contextPath}/customer/create'); */
            	// 创建userDailog的div，如果之前存在，先删除
            	$('#userDialog').remove();
            	$('body').append ('<div class="modal fade" id="userDialog" tabindex="-1" role="dialog" aria-labelledby="userModalLabel"></div>');
            	// 显示对话框
            	$('#userDialog').load ('${pageContext.request.contextPath}/user/create', function () {
            		$('#userDialog').modal ('show');
            	});
            }
            
            function deleteUsers () {
		    	var tableId = 'userTable';
		    	var table = $('#' + tableId).DataTable();
		    	var rows = table.rows('.info').data();
		    	// console.log (rows);
		    	if (rows.length == 0) {
		    		alert ('请选择至少一条记录！');
		    		return;
		    	}
		    	if (!confirm ('是否删除选中的用户？')) {
		    		return;
		    	}
		    	var ids = '';
		    	for (var i = 0; i < rows.length; i ++) {
		    		if (ids != '') {
		    			ids += ',';
		    		}
		    		ids += rows[i].id;
		    	}
		    	console.log (ids);
		    	deleteUser(ids);
		    }
		    </script>
                <!-- /.col-lg-12 -->
            </div>
            