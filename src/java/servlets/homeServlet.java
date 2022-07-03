
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class homeServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object username =  session.getAttribute("username");
        
        if (username == null) {
            response.sendRedirect("login");
            return;
        }
        else{
       getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
       return;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }


}
