<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dream.ssh.dto.DeptDto" %>
<%@ page import="com.dream.ssh.dto.PositionDto" %>
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
   				<form role="form" id="positionUpdateForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                        <input class="form-control" name="id" value="${position.id }" type="hidden">
                        	<input class="form-control" placeholder="请输入名称" name="name" value="${position.name }">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入描述" name="description" value="${position.description }">
                        </div>
                    </div>
                   	<div class="form-group">
                        <label class="col-sm-2 control-label">级别</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入级别" name="level"value="${position.level }">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10">
                          <select name="deptId" class="form-control col-sm-12" id="deptId">
                          <option value="${position.deptId }">${position.deptName }</option>
                        <%for (DeptDto d : depts) { %>
                       		 <option value="<%=d.getId()%>"> <%=d.getName()%></option>
                        <% } %>
                        </select>
                        </div>
                    </div>
                </form>
   			</div>
   			
   			<div class="modal-footer">
   				<button type="button" onclick="submitPositionUpdateForm ();" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
   			</div>
   		</div>
   		<script type="text/javascript">
	   	    $(document).ready(function () {	
	   			$('#positionUpdateForm').bootstrapValidator({
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
	   			
	   			// $('#positionUpdateForm').data('bootstrapValidator').enableFieldValidators('roleIds', false);
		    });
   	    
		   	function submitPositionUpdateForm () {
				// 获取关联的bootstrapValidator对象	
		   		var bv = $('#positionUpdateForm').data('bootstrapValidator');
		        bv.validate();
		        if (!bv.isValid()) {
		        	return;
		        }
		        $.post ('${pageContext.request.contextPath}/position/update', $('#positionUpdateForm').serializeArray(), function (result) {
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


