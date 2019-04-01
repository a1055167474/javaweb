package cn.swu.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet implements Servlet {

	private ServletConfig servletConfig;


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.servletConfig = servletConfig;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext servletContext = servletConfig.getServletContext();
		String inituser = servletContext.getInitParameter("user");
		String initpassword = servletContext.getInitParameter("password");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		if(inituser.equals(username) && initpassword.equals(password)) {
			out.print(username + " you have log in nitian.online!");
		}else {
			out.print(username + " login failed!");

		}
	}

}
