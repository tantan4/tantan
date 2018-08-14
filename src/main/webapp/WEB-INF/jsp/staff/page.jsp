<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
			 
                <div class="col-lg-12">
                    <h4>人员管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="staffTable">
                                <thead>
                                    <tr>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>生日</th>
                                        <th>手机号</th>
                                        <th>职位</th>
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
            	var tableId = 'staffTable';
            	// 初始化列表
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/staff/list",
                        "type": "GET"
                    },
                    "order": [[ 0, "desc" ]] ,
                    "columns": [
                    	{ "data": "name" },
                        { "data": "gender" , "orderable" : false},
                        { "data": "birthday" , "orderable" : false},
                        { "data": "mobile" , "orderable" : false},
                        { "data": "positionNames" , "orderable" : false},
                        { "data": null , "orderable" : false, "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a> <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及StaffDto
                    var data = table.row( $(this).parents('tr') ).data();
                	if ($(this).attr('name') == 'update') {
                		updateStaff(data.id);
                	}
					if ($(this).attr('name') == 'delete') {
						deleteStaff (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createStaff();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteStaffs();'>批量删除</a>");
            });
            
            function deleteStaff (id) {
            	$.post ('${pageContext.request.contextPath}/staff/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			/* location.reload (); */
		    			var tableId = 'staffTable';
		    			 var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} else {
		    			alert ('删除用户失败！');
		    		}
		    	});
            }
            
            function updateStaff (id) {
            	$('#staffDialog').remove();
            	$('body').append ('<div class="modal fade" id="staffDialog" tabindex="-1" role="dialog" aria-labelledby="staffModalLabel"></div>');
            	// 
            	$('#staffDialog').load ('${pageContext.request.contextPath}/staff/update?id=' + id, function () {
            		// 调用modal控件的show方法
            		$('#staffDialog').modal ('show');
            	});
            	//$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/update?id=' + id);
            }
            
            function createStaff () {
            	$('#staffDialog').remove();
            	$('body').append ('<div class="modal fade" id="staffDialog" tabindex="-1" role="dialog" aria-labelledby="staffModalLabel"></div>');
            	// 显示对话框
            	$('#staffDialog').load ('${pageContext.request.contextPath}/staff/create', function () {
            		$('#staffDialog').modal ('show');
            	});
            	//$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/create');
            	/* $('#page-wrapper').load ('${pageContext.request.contextPath}/customer/create'); */
            }
            
            function deleteStaffs () {
		    	var tableId = 'staffTable';
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
		    	deleteStaff(ids);
		    }
		    </script>
                <!-- /.col-lg-12 -->
            </div>
    