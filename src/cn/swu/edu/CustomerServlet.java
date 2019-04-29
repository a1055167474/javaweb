package cn.swu.edu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.swu.edu.CustomerDaoJdbcImpl;
import cn.swu.edu.Customer;
import cn.swu.edu.CriteriaCustomer;
import cn.swu.edu.CustomerDao;
//import javax.servlet.http.HttpSession;

public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDao customerDao = new CustomerDaoJdbcImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
            this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        //1、获取servletPath
        String servletPath = req.getServletPath();
        //System.out.println(servletPath);  //得到的是  /addCustomer.do
        
        //2、去除 / 和 .do ,得到 addCustomer 这样的字符串
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 3);
        //System.out.println(methodName);  // 得到的是 addCustomer
        
        try {
            //3、利用反射，根据获取的 方法名，获取对应的方法。
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
                                                    HttpServletResponse.class);
            //4、利用反射调用对应的方法    
            method.invoke(this, req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
            //可以有一些响应
            resp.sendRedirect("error.jsp");
        }
        
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //1、获取 id
        String idStr = request.getParameter("id");
        int id = 0;
        //为了避免输入的 id 是不 合法的。 idStr 不能转为 int 类型。若不能转，则 id 为0，无法执行任何的操作。
        try {
            id = Integer.parseInt(idStr);
            //2、调用 CustomerDAO 的 delete 方法
            customerDao.delete(id);
            //3、页面的重定向
            response.sendRedirect("query.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //1、获取模糊查询的请求参数
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        //2、把请求参数封装为 CriteriaCustomer 对象
        CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
        
        //3、再调用CustomerDao 的 getForListWithCriteriaCustomer() 方法
        List<Customer> customers = customerDao.getForListWithCriteriaCustomer(cc);
         
        //调用CustomerDao 的 getAll() 方法 查询。
        //List<Customer> customers = customerDao.getAll();
        
        //4、把查询到的结果集放入 request 中
        request.setAttribute("customers", customers);
        //5、请求的转发
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        System.out.println("add");
    }

}
