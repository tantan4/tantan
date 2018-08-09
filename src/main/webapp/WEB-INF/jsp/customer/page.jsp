<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<div class="row">
                <div class="col-lg-12">
                    <h4>客户管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="customerTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>客户名</th>
                                        <th>订单数</th>
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
            	var tableId = 'customerTable';
            	// 初始化列表
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/customer/list",
                        "type": "GET"
                    },
                    "order": [[ 1, "desc" ]] ,
                    "columns": [
                    	{ "data": "id" , "orderable" : false},
                    	{ "data": "name" },
                        { "data": "number" , "orderable" : false},
                        { "data": null , "orderable" : false, "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a> <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及CustomerDto
                    var data = table.row( $(this).parents('tr') ).data();
                	if ($(this).attr('name') == 'update') {
                		updateCustomer(data.id);
                	}
					if ($(this).attr('name') == 'delete') {
						deleteCustomer (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createCustomer();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteCustomers();'>批量删除</a>");
            });
            
            function deleteCustomer (id) {
            	$.post ('${pageContext.request.contextPath}/customer/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			/* location.reload (); */
		    			var tableId = 'customerTable';
		    			 var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} else {
		    			alert ('删除客户失败！');
		    		}
		    	});
            }
            
            function updateCustomer (id) {
            	$('#page-wrapper').load ('${pageContext.request.contextPath}/customer/update?id=' + id);
            }
            
            function createCustomer () {
            	$('#page-wrapper').load ('${pageContext.request.contextPath}/customer/create');
            }
            
            function deleteCustomers () {
		    	var tableId = 'customerTable';
		    	var table = $('#' + tableId).DataTable();
		    	var rows = table.rows('.info').data();
		    	// console.log (rows);
		    	if (rows.length == 0) {
		    		alert ('请选择至少一条记录！');
		    		return;
		    	}
		    	if (!confirm ('是否删除选中的客户？')) {
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
		    	deleteCustomer(ids);
		    }
		    </script>