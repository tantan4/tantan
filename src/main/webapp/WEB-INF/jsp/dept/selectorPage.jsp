<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>选择部门</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="menuTable">
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
            // 用于存放用户点击菜单时的临时id和名称，当用户点击确定时，该值才覆盖roleDeptIds和roleDeptNames的值
            var selectedDept = {
            	ids : $('#roleFormDeptIds').val(),
            	names : $('#roleFormDeptNames').val()
            };
            console.log (selectedDept);
            $(document).ready(function() {
            	var tableId = 'deptTable';
            	// 初始化列表，并触发后台的json数据获取
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
                         { "data": "description" , "orderable" : false}
                    ],
                    // 创建行之后的回调方法
                    "createdRow" : function( row, data, dataIndex ) {
                    	// 获取菜单的ids
                    	var ids = selectedDept.ids.split(',');
                    	for (var i = 0; i < ids.length; i ++) {
                    		if (data.id == ids[i]) {
                    			$(row).addClass( 'info' );
                    		}
                    	}
                    }
                });
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	var r = table.row( this ).data();
                	console.log (r);
                	// 如果有info样式则删除，没有则添加
                	if ($(this).attr ('class').indexOf ('info') > -1) {
                		// 删除
                		var ids = selectedDept.ids.split(',');
                		var names = selectedDept.names.split(',');
                		selectedDept.ids = '';
                		selectedDept.names = '';
                		for (var i = 0; i < ids.length; i ++) {
                			if (ids[i] != r.id) {
                				if (selectedDept.ids != '') {
                					selectedDept.ids += ',';
                    				selectedDept.names += ',';
                				}
                				selectedDept.ids += ids[i];
                				selectedDept.names += names[i];
                			}
                		}
                		console.log ('after delete :' + selectedDept.names);
                	} else {
                		// 添加
                		if (selectedDept.ids != '') {
           					selectedDept.ids += ',';
               				selectedDept.names += ',';
           				}
                		selectedDept.ids += r.id;
        				selectedDept.names += r.name;
        				console.log ('after add :' + selectedDept.names);
                	}
                	$(this).toggleClass('info');
                } );
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='selectDeptOK();'>确定</a> <a class='btn btn-primary btn-warning btn-sm' onclick='selectDeptCancel();'>取消</a>");
            });
            
            function selectDeptOK () {
            	var table = $('#menuTable').DataTable ();
            	$('#roleFormDeptIds').val (selectedDept.ids);
            	$('#roleFormDeptNames').val (selectedDept.names);
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
            }
            
            function selectDeptCancel () {
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
		    }
		    </script>