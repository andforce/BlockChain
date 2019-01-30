import com.andforce.block.BlockChainHelper;
import com.andforce.block.http.BlockChainApi;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class Main {

    private static BlockChainHelper sBlockChainHelper = BlockChainHelper.getInstance();

    public static void main(String[] args) throws Exception {

        sBlockChainHelper.newBlock(1, "1");


        Server server = new Server(8080);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);

        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);

        String api = BlockChainApi.class.getPackage().getName();
        servletHolder.setInitParameter("jersey.config.server.provider.packages", api);

        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.jackson.JacksonFeature"); // 自动将对象映射成json返回

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.exit(1);
        }

        finally {
            server.destroy();
        }
    }

}
