import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class Main {

	public static void main(String[] args) throws IOException {
		
		String[] names = {"bob","john","peter","rowin"};
		ArrayList<String> a = new ArrayList<>();
		
		for(int i = 0; i < names.length; i++){
			
			a.add(names[i]);
		}
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8090),0);
		HttpContext hc = server.createContext("/");
	
		hc.setHandler(new HttpHandler(){
			@Override
			public void handle(HttpExchange exchange) throws IOException {
				OutputStream os = exchange.getResponseBody();				
				String r = "<h1>helloworld guest</h1>";
				String[] uri = String.valueOf(exchange.getRequestURI()).split("/");
				if(exchange.getRequestURI().toString().length() > 1){
				if(a.contains(uri[1].toLowerCase())){
				r =	"<h1 style='color:green'>helloworld "+uri[1].toLowerCase()+"</h1>";

				}
			
				else {
				r =	"<h1 style='color:red'>404 user \""+uri[1].toLowerCase()+"\" not found</h1>";
				}
				}		
	exchange.sendResponseHeaders(200,r.length());		
	os.write(r.getBytes());
				
				os.close();
				
			}});

		server.start();
				}
}