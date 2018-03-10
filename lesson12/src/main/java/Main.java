import Connection.ConnectionHelper;
import Jetty.Servlets.CachePanelServlet;
import MyORM.DataSet;
import MyORM.UserDataSet;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;

import java.util.Collections;


/**
 * Created by Administrator on 2/1/2018.
 */
public class Main {

    public static void main(String[] args)
    {
        Executor executor=new Executor(ConnectionHelper.getConnection());


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new CachePanelServlet(executor.getCache())), "/");

        Server server = new Server(8090);

        LoginService loginService = new HashLoginService("MyRealm",
                "src/main/resources/realm.properties");
        server.addBean(loginService);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        server.setHandler(security);


        Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[] { "admin" });


        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/*");
        mapping.setConstraint(constraint);


        security.setConstraintMappings(Collections.singletonList(mapping));
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(loginService);

        security.setHandler(new HandlerList(context));


        try {
            server.start();
        }catch (Exception e){e.printStackTrace();}

        for(int i=900;i<Integer.MAX_VALUE;i++)
        {
            executor.save(new UserDataSet(i,"John",44));

            System.out.println(i+"--------->");
            System.out.println("Hit: "+executor.getHitCount());
            System.out.println("Miss: "+executor.getMissCount());
            DataSet ds=executor.load(i,UserDataSet.class);

        }
        executor.closeConnection();

    }
}
