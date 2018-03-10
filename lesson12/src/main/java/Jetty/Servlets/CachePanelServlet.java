package Jetty.Servlets;

        import Cache.CacheEngine;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;

public class CachePanelServlet extends HttpServlet {

    private static final String TIMER_PAGE_TEMPLATE = "CachePanel.html";

    private final CacheEngine cache;


    public CachePanelServlet(CacheEngine cacheEngine)
    {
        cache=cacheEngine;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("hit",cache.getHitCount());
        pageVariables.put("miss",cache.getMissCount());

        response.getWriter().println(TemplateProcessor.instance().getPage(TIMER_PAGE_TEMPLATE, pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}