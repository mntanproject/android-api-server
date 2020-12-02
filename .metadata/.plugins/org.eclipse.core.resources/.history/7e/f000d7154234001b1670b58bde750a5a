package mntanproject.core.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

	public static void main(String[] args) throws IOException {
	
		MnServer server = new MnServer(9000);
		//Thread serverThread = new Thread(server);
		//register route
		HashMap<String,String>routes = new HashMap<String,String>();
		routes.put("supplier","mntanproject.pos.api.SupplierResponse");
		server.getRegisteredRoute().setRegisteredRoutes(routes);
		server.startServer();
		
		System.out.println("working at other things.....");
		System.out.println("starting sleep thread");
		
	try {
		    Thread sleepThread = new Thread();
		    sleepThread.start();
		    sleepThread.sleep(11000);
		    sleepThread.interrupt();
		
	} catch (InterruptedException e) {
			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}");
		    e.printStackTrace();
		}
	
		System.out.println("sleep thread ended");
	
	
		//server.shutdownServer();
		//System.out.println("Bye...");
		//System.exit(0);
		//serverThread.setRunning(false);
		//serverThread.shutDownServer();
		
	
		
	}		
}