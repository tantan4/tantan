<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="deptModalLabel">创建子部门</h4>
		    </div>
		    
   			<div class="modal-body">
   				<form role="form" id="deptCreateForm" class="form-horizontal">
   					<div class="form-group">
                        <label class="col-sm-2 control-label">上级部门编号</label>
                        <div class="col-sm-10">
                        		<input type="hidden" name="parent.id" value="${dept.id }">
                        	 <input class="form-control" value="${dept.no }" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">编号</label>
                        <div class="col-sm-10">
                        	 <input class="form-control" name="no"  placeholder="请输入编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入名称" name="name">
                        </div>
                    </div>
                   	<div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                         <input class="form-control" placeholder="请输入描述" name="description">
                        </div>
                    </div>
                    
                </form>
   			</div>
   			
   			<div class="modal-footer">
   				<button type="button" onclick="submitDeptCreateForm ();" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
   			</div>
   		</div>
   		<script type="text/javascript">
   		 $(document).ready(function () {	
	   			$('#deptCreateForm').bootstrapValidator({
	   			
		            // 默认错误消息
	   				message: '输入值不合法',
	   				// 设置验证成功或者失败的图标
		            feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            // 设置不同字段的验证规则和错误信息
		            fields: {
		            	  no: {
		            	 message: '编号不合法',
		                    validators: {
		                    	   notEmpty: {
		                            message: '用编号不能为空'
		                        },
		                        regexp: {
		                            regexp: /^[0-9]+$/,
		                            message: '编号只能由数字组成 '
		                        }
		                    }
		                },
		                name: {
		                    message: '名称不合法',
		                    validators: {
		                        notEmpty: {
		                            message: '名称不能为空'
		                        }
		                        
		                    }
		                }
		                
		            }
		        });
	   			
	   			// $('#deptCreateForm').data('bootstrapValidator').enableFieldValidators('roleIds', false);
		    });
	    
		   	function submitDeptCreateForm () {
				// 获取关联的bootstrapValidator对象	
		   		var bv = $('#deptCreateForm').data('bootstrapValidator');
		        bv.validate();
		        if (!bv.isValid()) {
		        	return;
		        }
		        $.post ('${pageContext.request.contextPath}/dept/createChild', $('#deptCreateForm').serializeArray(), function (result) {
		 			if (result.success) {
		 				$('#page-wrapper').load ('${pageContext.request.contextPath}/dept');
		 				$('#deptDialog').modal ('hide');
		 			} else {
		 				alert (result.message);
		 			}
		 		});
		 	}
	</script>
 </div>
            <!-- /.row -->
           <%--  <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          	  创建子部门
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="deptCreateChildForm">
                                        <div class="form-group">
                                            <label>上级部门编号</label>
                                            <input class="form-control" value="${dept.no }" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label>编号</label>
                                            <input class="form-control" name="no"  placeholder="请输入编号">
                                            <input type="hidden" name="parent.id" value="${dept.id }">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>名称</label>
                                            <input class="form-control" placeholder="请输入名称" name="name">
                                        </div>
                                       	<div class="form-group">
                                            <label>描述</label>
                                            <input class="form-control" placeholder="请输入描述" name="description">
                                        </div>
                                        <button type="button" class="btn btn-default" onclick="submitCreateChildForm();">提交</button>
                                        <button type="reset" class="btn btn-default">重置</button>
                                    </form>
                                </div>
                               
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
                <script type="text/javascript">
                	function submitCreateChildForm () {
                		var url = '${pageContext.request.contextPath}/dept/createChild';
                		$.post (url, $('#deptCreateChildForm').serializeArray(), function (result) {
                			if (result.success) {
                				/* location.reload (); */
                			$('#page-wrapper').load ('${pageContext.request.contextPath}/dept'); 
                				/* window.location.href = '${pageContext.request.contextPath}/dept'; */
                			} else {
                				alert (result.message);
                			}
                		});
                	} 
                </script>
            </div> --%>
            <!-- /.row -->
			