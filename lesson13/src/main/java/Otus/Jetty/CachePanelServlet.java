package Otus.Jetty;

        import Otus.Cache.CacheEngine;
        import Otus.MyORM.UserDataSet;
        import Otus.Support.DBExecutor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Configurable;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;

@Configurable
public class CachePanelServlet extends HttpServlet {

    private static final String CACHE_TEMPLATE = "CachePanel.html";

    @Autowired
    private DBExecutor dbExecutor;
    @Autowired
    private ApplicationContext context;




    public void init()
    {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        dbExecutor = (DBExecutor) context.getBean("dbExecutorBean");
        dbExecutor.save(new UserDataSet(1025,"json",15));
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Boolean auth=(Boolean) request.getSession().getAttribute("auth");
        if(auth !=null && auth==true) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("hit", dbExecutor.getHitCount());
            pageVariables.put("miss", dbExecutor.getMissCount());

            response.getWriter().println(TemplateProcessor.instance().getPage(CACHE_TEMPLATE, pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
            }
        }

    }