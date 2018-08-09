package com.dream.ssh.exception;

import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;

/**
 * @author cailei
 *
 */
@Component
public class AopExceptionHandler implements MethodInterceptor {
	private final static Logger LOG = LogManager.getLogger(AopExceptionHandler.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		/*LOG.info("method:" + invocation.getMethod().getName());
		LOG.info("args:" + Arrays.toString(invocation.getArguments()));*/
		Object obj = null;
		try {
			obj = invocation.proceed();
			Class<?> r = invocation.getMethod().getReturnType();
			if (String.class.equals(r)) {
				if (invocation.getMethod().getName().equals("update")
						|| invocation.getMethod().getName().equals("create")
						|| invocation.getMethod().getName().equals("createChild")
						|| invocation.getMethod().getName().equals("menu")) {
					Object[] args = invocation.getArguments();
					Map<String, Object> map = (Map<String, Object>) args[args.length - 1];
					if (map.get("loginUser") == null) {
						return "redirect:/login";
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			Class<?> r = invocation.getMethod().getReturnType();
			// 如果方法的返回类型是String，表示异常时需要返回登录页面或者错误页面
			if (String.class.equals(r)) {
				// 1.需要返回登录页面
				if (invocation.getMethod().getName().equals("main")
						|| invocation.getMethod().getName().equals("login")) {
					
					return "redirect:/login?error=" + e.getMessage();
				}

				// 2.需要返回错误页面
				Object[] args = invocation.getArguments();
				Map<String, Object> map = (Map<String, Object>) args[args.length - 1];
				map.put("error", e.getMessage());
				return "error";
			}
			// 3.DataTable获取列表数据是的json请求，异常处理
			if (DataTable.class.equals(r)) {
				Object[] args = invocation.getArguments();
				Integer draw = (Integer) args[0];
				DataTable dt = new DataTable();
				dt.setDraw(++draw);
				return dt;
			}
			// 4.ajax请求返回Result
			if(e instanceof ConstraintViolationException){
				return new MyResult().setSuccess(false).setMessage("此用户已经存在，更换登录名或手机号");
			}
			return new MyResult().setSuccess(false).setMessage(e.getMessage());
		}
		return obj;
	}
}
