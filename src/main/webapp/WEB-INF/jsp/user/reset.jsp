<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">修改密码</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<form role="form"
										action="${pageContext.request.contextPath}/reset"
										method="post" id="resetForm">
										<div class="form-group">
											<label></label> <input id="psw1" type="password"
												class="form-control" placeholder="请输入密码" name="password1">
										</div>
										<div class="form-group">
											<label></label> <input id="psw2" type="password"
												class="form-control" placeholder="请确认密码" name="password">
										</div>

									</form>
									<button id="submit" onclick="javascript:submit()"
										class="btn btn-default">提交</button>
									<button id="reset" onclick="javascript:reset()"
										class="btn btn-default">重置</button>
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
		$(document).ready(function() {

			$("#psw2").blur(function() {
				var psw1 = $('#psw1');
				var psw2 = $('#psw2');
				if ($.trim(psw1.val()) == "") {
					alert('请输入密码！');
					return;
				}
				if (($.trim(psw1.val())) != ($.trim(psw2.val()))) {
					alert('请重新输入密码！');
					location.reload();
					return;
				}
				

			});
		});
		function submit() {
			var psw1 = $('#psw1');
			var psw2 = $('#psw2');
			if ($.trim(psw1.val()) == "" || $.trim(psw2.val()) == "" ) {
				alert('请输入密码！');
				return;
			}
			$('#resetForm').submit();
		}
		function reset() {
			$('#resetForm').reset();
		}
	</script>
			</div>
			<!-- /.row -->
	
