package simpleWebServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Handler;

public class SimpleHttpServer {
    public static int default_Port = 9000;
    public static int port;
    private HttpServer httpServer;

    private void start(int port){
        this.port = port;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port),0);
            System.out.println("server Started at port :" +port);
            httpServer.createContext("/", new Handlers.RootHandler());
            httpServer.createContext("/echoheader",new Handlers.EchoHeaderHandler());
            httpServer.createContext("echoGet",new Handlers.EchoGetHandler());
            httpServer.createContext("/echopost",new Handlers.EchoPostHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer();
        simpleHttpServer.start(default_Port);
    }
}
