<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dream.ssh.dto.PositionDto" %>
<%@ page import="com.dream.ssh.dto.DictionaryDto" %>
<%@ page import="java.util.List" %>
<%
	List<PositionDto> positions = (List<PositionDto>)request.getAttribute("positions");
	List<DictionaryDto> dos=(List<DictionaryDto>) request.getAttribute("dictionarys");
%>	
<!-- /.row -->
<div class="modal-dialog modal-lg" role="document" id="you">
		<div class="modal-content" id="my">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="staffModalLabel">修改员工</h4>
		    </div>
		    
   			<div class="modal-body">
   				<form role="form" id="staffCreateForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓&nbsp;&nbsp;&nbsp;名</label>
                        <div class="col-sm-10">
                        	<input class="form-control" placeholder="请输入姓名" name="name" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性&nbsp;&nbsp;&nbsp;别</label>
                        <div class="col-sm-10">
                        <%for (DictionaryDto d : dos) { %>
                        <label class="radio-inline">
                            <input type="radio" value="<%=d.getValue()%>" name="gender"> <%=d.getName()%>
                        </label>
                        <% } %>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">生&nbsp;&nbsp;&nbsp;日</label>
                        <div class="col-sm-10">
                        <input class="form-control" name="birthday" type="datetime-local">
                        </div>
                    </div>
                   	<div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入手机号" name="mobile">
                        </div>
                    </div>
                    <input type="hidden" name="positionIds" id="staffFormPositionIds">
				<div class="form-group ">
				 <label class="col-sm-2 control-label">职&nbsp;&nbsp;&nbsp;位</label>
				  	 <div class=" col-sm-10 ">
				  	 <div class=" input-group">
					 <input type="text" class="form-control" placeholder="请选择职位"id="staffFormPositionNames"> 
					<span class="input-group-btn">
						<button style="height: 34px;" class="btn btn-default" type="button" onclick="selectStaffPositions();">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				</div>
				</div>
			</form>
   			</div>
   			
   			<div class="modal-footer">
   				<button type="button" onclick="submitStaffCreateForm ();" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
   			</div>
   		</div>
	<script type="text/javascript">
	function selectStaffPositions () {
		$('#my').hide();
		
		$('#you').append ('<div id="staffDialog1">职位列表选择</div>');
		//$('#staffDialog1').modal ('show');
		/* $('#staffDialog').load ('${pageContext.request.contextPath}/staff/positions', function () {
    		$('#staffDialog').modal ('show');
    	}); */
		$('#staffDialog1').load ('${pageContext.request.contextPath}/staff/positions');
	}
		 $(document).ready(function () {	
	   			$('#staffCreateForm').bootstrapValidator({
	   				
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
		            	 message: '姓名不合法',
		                    validators: {
		                    	   notEmpty: {
		                            message: '姓名不能为空'
		                        },
		                        regexp: {
		                            regexp: /^[a-zA-Z\u4e00-\u9fa5 ]+$/,
		                            message: '姓名只能由字母和汉字组成 '
		                        }
		                    }
		                },
		                mobile: {
		                    message: '手机号不合法',
		                    validators: {
		                        notEmpty: {
		                            message: '手机号不能为空'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 20,
		                            message: '请输入6到20个字符'
		                        },
		                        regexp: {
		                            regexp: /^[0-9]+$/,
		                            message: '手机号只能由数字组成 '
		                        }
		                    }
		                }
		              
		            }
		        });
	   			
	   			// $('#staffCreateForm').data('bootstrapValidator').enableFieldValidators('roleIds', false);
		    });
	    
		   	function submitStaffCreateForm () {
				// 获取关联的bootstrapValidator对象	
		   		var bv = $('#staffCreateForm').data('bootstrapValidator');
		        bv.validate();
		        if (!bv.isValid()) {
		        	return;
		        }
		        $.post ('${pageContext.request.contextPath}/staff/create', $('#staffCreateForm').serializeArray(), function (result) {
		 			if (result.success) {
		 				$('#page-wrapper').load ('${pageContext.request.contextPath}/staff');
		 				$('#staffDialog').modal ('hide');
		 			} else {
		 				alert (result.message);
		 			}
		 		});
		 	}
	</script>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->


