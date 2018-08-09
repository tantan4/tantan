<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="deptModalLabel">修改部门</h4>
		    </div>
		    
   			<div class="modal-body">
   				<form role="form" id="deptUpdateForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">编号</label>
                        <div class="col-sm-10">
                        <input class="form-control" name="id" value="${dept.id }" type="hidden"  >
                        	 <input class="form-control" name="no" value="${dept.no }"  placeholder="请输入编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入名称" name="name" value="${dept.name }">
                        </div>
                    </div>
                   	<div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                         <input class="form-control" placeholder="请输入描述" name="description" value="${dept.description }">
                        </div>
                    </div>
                    
                </form>
   			</div>
   			
   			<div class="modal-footer">
   				<button type="button" onclick="submitDeptUpdateForm ();" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
   			</div>
   		</div>
   		<script type="text/javascript">
   		 $(document).ready(function () {	
	   			$('#deptUpdateForm').bootstrapValidator({
	   			
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
	   			
	   			// $('#deptUpdateForm').data('bootstrapValidator').enableFieldValidators('roleIds', false);
		    });
	    
		   	function submitDeptUpdateForm () {
				// 获取关联的bootstrapValidator对象	
		   		var bv = $('#deptUpdateForm').data('bootstrapValidator');
		        bv.validate();
		        if (!bv.isValid()) {
		        	return;
		        }
		        $.post ('${pageContext.request.contextPath}/dept/update', $('#deptUpdateForm').serializeArray(), function (result) {
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
			