package cn.smbms.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.smbms.pojo.User;
import cn.smbms.utils.Constants;
/**
 * 拦截器
 * @params
 * @author syb
 * @return
 */
public class SysInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response
			,Object handler)throws Exception{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		if (user==null) {
			response.sendRedirect(request.getContextPath()+"/syserror.jsp");
			return false;
		}
		return true;
	}
}
