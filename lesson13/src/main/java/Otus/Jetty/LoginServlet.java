package Otus.Jetty;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 13.03.2018.
 */
public class LoginServlet extends HttpServlet {

    private final String adminLogin="admin";
    private final String adminPassword="1";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login=request.getParameter("login");
        String password=request.getParameter("password");

        Boolean auth=(Boolean) request.getSession().getAttribute("auth");
        if(auth !=null && auth==true) {
            request.getSession().setAttribute("auth", true);
            response.sendRedirect(request.getContextPath() + "/cache");
        }
        else
        {
            response.getWriter().println(TemplateProcessor.instance().getPage("Login.html",new HashMap()));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestLogin = request.getParameter("login");
        String requestPassword = request.getParameter("password");

        if (isAuth(requestLogin, requestPassword)) {
            request.getSession().setAttribute("auth", true);

            response.sendRedirect(request.getContextPath() + "/cache");
        }else{
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private boolean isAuth(String login,String password)
    {
        if(login != null && password!=null) {
            if (login.equals(adminLogin) && password.equals(adminPassword)) {
                return true;
            }
        }
        return false;
    }

}
