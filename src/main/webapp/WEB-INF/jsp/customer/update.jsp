<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">更新客户资料</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Basic Form Elements
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="updateCustomerForm">
                                        <input type="hidden"  name="id" value="${customer.id }">
                                        <div class="form-group">
                                            <label>客户名</label>
                                            <input class="form-control" placeholder="请输入客户名" name="name" value="${customer.name }">
                                        </div>
                                       	<div class="form-group">
                                            <label>年龄</label>
                                            <input class="form-control" placeholder="请输入年龄" name="age" value="${customer.age }">
                                        </div>
                                        <button type="button" onclick="submitUpdateCustomerForm ();" class="btn btn-default">提交</button>
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
                <script type="text/javascript">
                	function submitUpdateCustomerForm () {
                		var url = '${pageContext.request.contextPath}/customer/update';
                		$.post (url, $('#updateCustomerForm').serializeArray(), function (result) {
                			if (result.success) {
                				location.reload ();
                				/* $('#page-wrapper').load ('${pageContext.request.contextPath}/menu'); */
                			} else {
                				alert (result.message);
                			}
                		});
                	} 
                </script>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->