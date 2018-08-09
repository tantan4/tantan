<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>职位管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="positionTable">
                                <thead>
                                    <tr>
                                        <th>职位</th>
                                        <th>级别</th>
                                        <th>部门</th>
                                        <th>描述</th>
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
            	var tableId = 'positionTable';
            	// 初始化列表
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/position/list",
                        "type": "GET"
                    },
                    "order": [[ 0, "desc" ]] ,
                    "columns": [
                    	{ "data": "name" },
                        { "data": "level" , "orderable" : false},
                        { "data": "deptName" , "orderable" : false},
                        { "data": "description" , "orderable" : false},
                        { "data": null , "orderable" : false, "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a> <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及PositionDto
                    var data = table.row( $(this).parents('tr') ).data();
                	if ($(this).attr('name') == 'update') {
                		updatePosition(data.id);
                	}
					if ($(this).attr('name') == 'delete') {
						deletePosition (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createPosition();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deletePositions();'>批量删除</a>");
            });
            
            function deletePosition (id) {
            	$.post ('${pageContext.request.contextPath}/position/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			/* location.reload (); */
		    			var tableId = 'positionTable';
		    			 var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} else {
		    			alert ('删除职位失败！');
		    		}
		    	});
            }
            
            function updatePosition (id) {
            	$('#positionDialog').remove();
            	$('body').append ('<div class="modal fade" id="positionDialog" tabindex="-1" role="dialog" aria-labelledby="positionModalLabel"></div>');
            	// 显示对话框
            	$('#positionDialog').load ('${pageContext.request.contextPath}/position/update?id=' + id, function () {
            		$('#positionDialog').modal ('show');
            	});
            	//$('#page-wrapper').load ('${pageContext.request.contextPath}/position/update?id=' + id);
            }
            
            function createPosition () {
            	$('#positionDialog').remove();
            	$('body').append ('<div class="modal fade" id="positionDialog" tabindex="-1" role="dialog" aria-labelledby="positionModalLabel"></div>');
            	// 显示对话框
            	$('#positionDialog').load ('${pageContext.request.contextPath}/position/create', function () {
            		$('#positionDialog').modal ('show');
            	});
            	//$('#page-wrapper').load ('${pageContext.request.contextPath}/position/create');
            	/* $('#page-wrapper').load ('${pageContext.request.contextPath}/customer/create'); */
            }
            
            function deletePositions () {
		    	var tableId = 'positionTable';
		    	var table = $('#' + tableId).DataTable();
		    	var rows = table.rows('.info').data();
		    	// console.log (rows);
		    	if (rows.length == 0) {
		    		alert ('请选择至少一条记录！');
		    		return;
		    	}
		    	if (!confirm ('是否删除选中的职位？')) {
		    		return;
		    	}
		    	var ids = '';
		    	for (var i = 0; i < rows.length; i ++) {
		    		if (ids != '') {
		    			ids += ',';
		    		}
		    		ids += rows[i].id;
		    	}
		    	deletePosition(ids);
		    }
		    </script>
                <!-- /.col-lg-12 -->
            </div>
            