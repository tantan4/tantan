<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        	    添加客户表
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="customerCreateForm" >
                                        <div class="form-group">
                                            <label>客户名</label>
                                            <input class="form-control" placeholder="请输入客户名" name="name" id="name">
                                        </div>
                                        <div class="form-group">
                                            <label>性别</label>
                                            <select name="gender" class="form-control" id="gender">
					    						<option value="男">男</option>
					    						<option value="男">男</option>
					    						<option value="女">女</option>
					    					</select>
                                        </div>
                                       	<div class="form-group">
                                            <label>生日</label>
                                           <!--  <input class="form-control" placeholder="请输入年龄" name="age" id="age"> -->
                                         <input type="datetime-local" name="birthday" class="form-control" id="age">
                                        </div>
                                        <button  type="button" onclick="from();" class="btn btn-default">提交</button>
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
	 function from(){
		 var name=$('#name');
		 var age=$('#age');
		 var msg="";
		 if($.trim(name.val()) == ""){
			 msg="姓名不能为空";
			/*  age.focus(); */
		 }else if($.trim(age.val()) == ""){
			 msg="生日不能为空";
			/*  name.focus(); */
		 }
		 if (msg != ""){
			 alert(msg);
				
			}else {
				var url = '${pageContext.request.contextPath}/customer/create';
        		$.post (url, $('#customerCreateForm').serializeArray(), function (result) {
        			if (result.success) {
        				//location.reload ();
        			 $('#page-wrapper').load ('${pageContext.request.contextPath}/customer'); 
        			} else {
        				alert (result.message);
        			}
        		});
			}
		
	 }
	 </script>
            </div>
            <!-- /.row -->
       

   
