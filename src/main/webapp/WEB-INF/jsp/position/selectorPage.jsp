<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>选择菜单</h4>
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
                                        <th>职位</th>
                                        <th>级别</th>
                                        <th>部门</th>
                                        <th>描述</th>
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
            // 用于存放用户点击菜单时的临时id和名称，当用户点击确定时，该值才覆盖rolePositionIds和rolePositionNames的值
            var selectedPosition = {
            	ids : $('#staffFormPositionIds').val(),
            	names : $('#staffFormPositionNames').val()
            };
            console.log (selectedPosition);
            $(document).ready(function() {
            	var tableId = 'menuTable';
            	// 初始化列表，并触发后台的json数据获取
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/position/list",
                        "type": "GET"
                    },
                    "order": [[ 0, "asc" ]] ,
                    "columns": [
                    	{ "data": "name" },
                        { "data": "level" , "orderable" : false},
                        { "data": "deptName" , "orderable" : false},
                        { "data": "description" , "orderable" : false}
                    ],
                    // 创建行之后的回调方法
                    "createdRow" : function( row, data, dataIndex ) {
                    	// 获取菜单的ids
                    	var ids = selectedPosition.ids.split(',');
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
                		var ids = selectedPosition.ids.split(',');
                		var names = selectedPosition.names.split(',');
                		selectedPosition.ids = '';
                		selectedPosition.names = '';
                		for (var i = 0; i < ids.length; i ++) {
                			if (ids[i] != r.id) {
                				if (selectedPosition.ids != '') {
                					selectedPosition.ids += ',';
                    				selectedPosition.names += ',';
                				}
                				selectedPosition.ids += ids[i];
                				selectedPosition.names += names[i];
                			}
                		}
                		console.log ('after delete :' + selectedPosition.names);
                	} else {
                		// 添加
                		if (selectedPosition.ids != '') {
           					selectedPosition.ids += ',';
               				selectedPosition.names += ',';
           				}
                		selectedPosition.ids += r.id;
        				selectedPosition.names += r.name;
        				console.log ('after add :' + selectedPosition.names);
                	}
                	$(this).toggleClass('info');
                } );
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='selectPositionOK();'>确定</a> <a class='btn btn-primary btn-warning btn-sm' onclick='selectPositionCancel();'>取消</a>");
            });
            
            function selectPositionOK () {
            	var table = $('#menuTable').DataTable ();
            	$('#staffFormPositionIds').val (selectedPosition.ids);
            	$('#staffFormPositionNames').val (selectedPosition.names);
            	$('#staffDialog1').remove();
            	$('#my').show();
            }
            
            function selectPositionCancel () {
            	$('#staffDialog1').remove();
            	$('#my').show();
		    }
		    </script>