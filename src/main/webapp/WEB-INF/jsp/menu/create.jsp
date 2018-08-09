<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">创建菜单</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form" id="menuCreateForm">
							<div class="form-group">
								<label>编号</label> <input class="form-control" name="no">
							</div>
							<div class="form-group">
								<label>名称</label> <input class="form-control"
									placeholder="请输入名称" name="name">
							</div>
							<div class="form-group">
								<label>图标</label> <input class="form-control"
									placeholder="请输入图标" name="icon">
							</div>
							<div class="form-group">
								<label>URL</label> <input class="form-control"
									placeholder="请输入URL" name="url">
							</div>
							<button type="button" class="btn btn-default"
								onclick="submitCreateForm();">提交</button>
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
		function submitCreateForm() {
			var url = '${pageContext.request.contextPath}/menu/create';
			$.post(url,$('#menuCreateForm').serializeArray(),
							function(result) {
								if (result.success) {
									/* location.reload (); */
									/* $('#page-wrapper').load ('${pageContext.request.contextPath}/menu'); */
									window.location.href = '${pageContext.request.contextPath}/menu/menu';
								} else {
									alert(result.message);
								}
							});
		}
	</script>
</div>
<!-- /.row -->
