<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dream.ssh.dto.DeptDto" %>
<%@ page import="java.util.List" %>
<%
	List<DeptDto> depts = (List<DeptDto>)request.getAttribute("depts");
%>		
<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="userModalLabel">创建数职位表</h4>
		    </div>
		    
   			<div class="modal-body">
   				<form role="form" id="positionCreateForm" class="form-horizontal">
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
                   	<div class="form-group">
                        <label class="col-sm-2 control-label">级别</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入级别" name="level">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10">
                          <select name="deptId" class="form-control col-sm-12" id="deptId">
                        <%for (DeptDto d : depts) { %>
                       		 <option value="<%=d.getId()%>"> <%=d.getName()%></option>
                        <% } %>
                        </select>
                        </div>
                    </div>
                </form>
   			</div>
   			
   			<div class="modal-footer">
   				<button type="button" onclick="submitPositionCreateForm ();" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
   			</div>
   		</div>
   		<script type="text/javascript">
	   	    $(document).ready(function () {	
	   			$('#positionCreateForm').bootstrapValidator({
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
		                name: {
		                    validators: {
		                        notEmpty: {
		                            message: '名称不能为空'
		                        },
		                        stringLength: {
		                            min: 1,
		                            max: 30,
		                            message: '请输入1到30个字符'
		                        }
		                    }
		                },
		                level: {
		                    validators: {
		                        notEmpty: {
		                            message: '级别不能为空'
		                        }
		                    }
		                }
		                
		            }
		        });
	   			
	   			// $('#positionCreateForm').data('bootstrapValidator').enableFieldValidators('roleIds', false);
		    });
   	    
		   	function submitPositionCreateForm () {
				// 获取关联的bootstrapValidator对象	
		   		var bv = $('#positionCreateForm').data('bootstrapValidator');
		        bv.validate();
		        if (!bv.isValid()) {
		        	return;
		        }
		        $.post ('${pageContext.request.contextPath}/position/create', $('#positionCreateForm').serializeArray(), function (result) {
		 			if (result.success) {
		 				$('#page-wrapper').load ('${pageContext.request.contextPath}/position');
		 				$('#positionDialog').modal ('hide');
		 			} else {
		 				alert (result.message);
		 			}
		 		});
		 	}
   		</script>
   	</div>
<!-- /.row -->
<!-- <div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">创建职位表</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form role="form" id="formid">
							<div class="form-group">
								<label>职位名称</label> <input class="form-control"
									placeholder="请输入职位名" name="name" id="nameId">
							</div>
							<div class="form-group">
								<label>职位描述</label> <input class="form-control"
									placeholder="请输入描述" name="description" id="descriptionId">
							</div>
							<div class="form-group">
								<label>级别</label> <input class="form-control"
									placeholder="请输入级别" name="level" 
									id="levelId">
							</div>
							<label>部门</label>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="deptIds" id="roleFormDeptIds">
                                            <input type="text" class="form-control" placeholder="请选择部门" id="roleFormDeptNames">
                                            <span class="input-group-btn">
                                                <button style="height:34px;" class="btn btn-default" type="button" onclick="selectPositionDept();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                             <label>员工名</label>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="staffIds" id="roleFormStaffIds">
                                            <input type="text" class="form-control" placeholder="请选择员工" id="roleFormStaffNames">
                                            <span class="input-group-btn">
                                                <button style="height:34px;" class="btn btn-default" type="button" onclick="selectPositionStaff();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                            <button type="button" onclick="javascript:submit()"
							class="btn btn-default">提交</button>
						<button type="reset" class="btn btn-default">重置</button>
						</form>
						
					</div>

				</div>
				/.row (nested)
			</div>
			/.panel-body
		</div>
		/.panel
	</div>
	<script type="text/javascript">
		function submit() {
			var levelId = $('#levelId');
			var loginname = $('#descriptionId');
			var name = $('#nameId');
			var msg = "";
			if ($.trim(loginname.val()) == "") {
				msg = "描述不能为空";
				loginname.focus();
			} else if ($.trim(levelId.val()) == "") {
				msg = "级别不能为空";
				levelId.focus();

			} else if ($.trim(name.val()) == "") {
				msg = "姓名不能为空";
				name.focus();
			}
			if (msg != "") {
				alert(msg);

			} else {
				var url = '${pageContext.request.contextPath}/position/create';
				$.post(url, $('#formid').serializeArray(), function(result) {
					if (result.success) {
						/* location.reload(); */
						 $('#page-wrapper').load ('${pageContext.request.contextPath}/position'); 
					} else {
						alert(result.message);
					}
				});
			}
		}
		function selectPositionDept () {
    		$('#page-wrapper').hide().attr('id', "page-wrapper2");
    		$('#wrapper').append ('<div id="page-wrapper">部门列表选择</div>');
    		$('#page-wrapper').load ('${pageContext.request.contextPath}/position/dept');
    	}
		function selectPositionStaff () {
    		$('#page-wrapper').hide().attr('id', "page-wrapper2");
    		$('#wrapper').append ('<div id="page-wrapper">员工列表选择</div>');
    		$('#page-wrapper').load ('${pageContext.request.contextPath}/position/staff');
    	}
	</script>
	/.col-lg-12
</div> -->
<!-- /.row -->


