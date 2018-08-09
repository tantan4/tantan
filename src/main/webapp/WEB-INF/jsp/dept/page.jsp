<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>菜单管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="deptTable">
                                <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>部门名</th>
                                        <th>上级部门</th>
                                        <th>描述</th>
                                        <th width="150">操作</th>
                                    </tr>
                                </thead>
                            </table>
                            <!-- /.table-responsive -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <script>
            $(document).ready(function() {
            	var tableId = 'deptTable';
            	// 初始化列表，并触发后台的json数据获取
            	var btns = ["<a class='btn btn-primary btn-xs' name='createChild'>创建子部门</a> ",
            		"<a class='btn btn-primary btn-xs' name='update'>修改</a> ",
            		"<a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"].join('');
            	 var table = $('#' + tableId).DataTable({
                 	"processing": true,
                     "serverSide": true,
                     "rowId" : 'id',
                     "ajax": {
                         "url": "${pageContext.request.contextPath}/dept/list",
                         "type": "GET"
                     },
                     "order": [[ 0, "asc" ]] ,
                     "columns": [
                     	{ "data": "no" },
                         { "data": "name" , "orderable" : false},
                         { "data": "parentName" , "orderable" : false},
                         { "data": "description" , "orderable" : false},
                        // 下面在操作列中添加了修改和删除按钮
                        { "data": null , "orderable" : false, "defaultContent": btns}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及UserDto
                    var data = table.row( $(this).parents('tr') ).data();
                 	// 调用创建子菜单方法
					if ($(this).attr('name') == 'createChild') {
						createChild (data.id);
                	}
                	// 调用修改方法
                	if ($(this).attr('name') == 'update') {
                		updateDept(data.id);
                	}
                	// 调用删除方法
					if ($(this).attr('name') == 'delete') {
						deleteDept (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	// 如果有info样式则删除，没有则添加
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createDept();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteDepts();'>批量删除</a>");
            });
            
            function deleteDept (id) {
            	$.post ('${pageContext.request.contextPath}/dept/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			var tableId = 'deptTable';
				    	var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} else {
		    			alert ('删除部门失败！当前部门存在子部门或者存在关联职位');
		    		}
		    	});
            }
            
            function createChild (id) {
            	//$('#page-wrapper').load ('${pageContext.request.contextPath}/dept/createChild?id=' + id);
            	$('#deptDialog').remove();
            	$('body').append ('<div class="modal fade" id="deptDialog" tabindex="-1" role="dialog" aria-labelledby="deptModalLabel"></div>');
            	// 显示对话框
            	$('#deptDialog').load ('${pageContext.request.contextPath}/dept/createChild?id=' + id, function () {
            		$('#deptDialog').modal ('show');
            	});
            }
            
            function updateDept (id) {
            	//$('#page-wrapper').load('${pageContext.request.contextPath}/dept/update?id='+id);
            	$('#deptDialog').remove();
            	$('body').append ('<div class="modal fade" id="deptDialog" tabindex="-1" role="dialog" aria-labelledby="deptModalLabel"></div>');
            	// 显示对话框
            	$('#deptDialog').load ('${pageContext.request.contextPath}/dept/update?id='+id, function () {
            		$('#deptDialog').modal ('show');
            	});
            }
            
            function createDept() {
            	//$('#page-wrapper').load('${pageContext.request.contextPath}/dept/create');
            	$('#deptDialog').remove();
            	$('body').append ('<div class="modal fade" id="deptDialog" tabindex="-1" role="dialog" aria-labelledby="deptModalLabel"></div>');
            	// 显示对话框
            	$('#deptDialog').load ('${pageContext.request.contextPath}/dept/create', function () {
            		$('#deptDialog').modal ('show');
            	});
            }
            
            function deleteDepts () {
            	// 获取DataTable
		    	var tableId = 'deptTable';
		    	var table = $('#' + tableId).DataTable();
		    	// 查找样式为info的行，包含所有UserDto属性
		    	var rows = table.rows('.info').data();
		    	// 判断用户是否有选择
		    	if (rows.length == 0) {
		    		alert ('请选择至少一条记录！');
		    		return;
		    	}
		    	// 让用户确认删除
		    	if (!confirm ('是否删除选中的部门？')) {
		    		return;
		    	}
		    	// 拼接id字符串，多个id逗号隔开
		    	var ids = '';
		    	for (var i = 0; i < rows.length; i ++) {
		    		if (ids != '') {
		    			ids += ',';
		    		}
		    		ids += rows[i].id;
		    	}
		    	// 发送ajax请求，如果成功刷新本页面，否则提示用户操作失败
		    	deleteDept(ids);
		    }
		    </script>