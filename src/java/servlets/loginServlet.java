
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountServices;


public class loginServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        
        if (request.getParameter("logout") != null) {
         
           request.setAttribute("message", "Logout Successful");
           session.invalidate();
        }
        else
        {
            if (session.getAttribute("username") != null) {
                response.sendRedirect("home");
                return;
            }
        }
         getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
     
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         if (username == null || username.equals("") ||password == null || password.equals("")) {
            request.setAttribute("message", "Please enter username and password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
         else{
             AccountServices service = new AccountServices();
             
             User user = service.login(username, password);
             if (user != null) {
                 request.getSession().setAttribute("username", username);
                 response.sendRedirect("home");
                 return;
                 
             }
             else
             {
                 request.setAttribute("username", username);
                 request.setAttribute("message", "Invalid username or password.");
             }
                     }
         
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
       return;
    }

 
}
