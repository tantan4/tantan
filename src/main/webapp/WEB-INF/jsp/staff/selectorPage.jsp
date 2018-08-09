<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>选择员工</h4>
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
                                        <tr>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>生日</th>
                                        <th>手机号</th>
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
                <!-- /.col-lg-12 -->
            </div>
            <script>
            // 用于存放用户点击菜单时的临时id和名称，当用户点击确定时，该值才覆盖roleStaffIds和roleStaffNames的值
            var selectedStaff = {
            	ids : $('#roleFormStaffIds').val(),
            	names : $('#roleFormStaffNames').val()
            };
            console.log (selectedStaff);
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
                        { "data": "mobile" , "orderable" : false}
                    ],
                    // 创建行之后的回调方法
                    "createdRow" : function( row, data, dataIndex ) {
                    	// 获取菜单的ids
                    	var ids = selectedStaff.ids.split(',');
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
                		var ids = selectedStaff.ids.split(',');
                		var names = selectedStaff.names.split(',');
                		selectedStaff.ids = '';
                		selectedStaff.names = '';
                		for (var i = 0; i < ids.length; i ++) {
                			if (ids[i] != r.id) {
                				if (selectedStaff.ids != '') {
                					selectedStaff.ids += ',';
                    				selectedStaff.names += ',';
                				}
                				selectedStaff.ids += ids[i];
                				selectedStaff.names += names[i];
                			}
                		}
                		console.log ('after delete :' + selectedStaff.names);
                	} else {
                		// 添加
                		if (selectedStaff.ids != '') {
           					selectedStaff.ids += ',';
               				selectedStaff.names += ',';
           				}
                		selectedStaff.ids += r.id;
        				selectedStaff.names += r.name;
        				console.log ('after add :' + selectedStaff.names);
                	}
                	$(this).toggleClass('info');
                } );
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='selectStaffOK();'>确定</a> <a class='btn btn-primary btn-warning btn-sm' onclick='selectStaffCancel();'>取消</a>");
            });
            
            function selectStaffOK () {
            	var table = $('#menuTable').DataTable ();
            	$('#roleFormStaffIds').val (selectedStaff.ids);
            	$('#roleFormStaffNames').val (selectedStaff.names);
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
            }
            
            function selectStaffCancel () {
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
		    }
		    </script>