import com.andforce.block.BlockChain;
import com.andforce.block.utils.HashUtils;
import com.andforce.block.utils.JsonUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main {

    static BlockChain blockChain;

    public static void main(String[] args) throws Exception {

        blockChain = new BlockChain();
        blockChain.newBlock(1, HashUtils.getSHA256StrJava("1"));

        Server server = new Server(8080);
        server.setHandler(new HelloHandler());

        server.start();
    }

    public static class HelloHandler extends AbstractHandler {
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println(JsonUtils.toStringSortByKey(blockChain.getChain()));
        }
    }

}
