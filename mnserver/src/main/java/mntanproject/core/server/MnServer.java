package mntanproject.core.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mntanproject.core.server.request.HttpRequest;
import mntanproject.core.server.request.HttpRequestFactory;
import mntanproject.core.server.request.RegisteredRoute;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;
import mntanproject.core.server.route.Route;




public class MnServer {

	public boolean serverRunning = false;
	private ServerSocket serverSocket = null;
	private final ExecutorService connectionProcessorThreadPool = Executors.newCachedThreadPool();
	private connectionListener connectionListenerThread;
	int port;
	public RegisteredRoute registeredRoute = new RegisteredRoute();
	public Route route;
	
	public MnServer(int port) {
		super();
		this.port = port;
	}

	public void startServer(){
		if(serverRunning) {
			System.out.println("Server already running");
		} else {
			
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Error opening port");
				e.printStackTrace();
			}
			serverRunning = true;
			connectionListenerThread = new connectionListener();
			System.out.println("Server is starting");
			connectionListenerThread.start();
			System.out.println("Server started listening at port: " + port);
		}
		

	}

	public void shutdownServer() {
		if (serverRunning) {
			serverRunning = false;
			connectionListenerThread.interrupt();
		    System.out.println("Server has been shut down");
		}
	}

	private class connectionListener extends Thread {

		@Override
		public void run() {

			while (serverRunning) {
				Socket acceptedSocket = null;
				try {
					acceptedSocket = serverSocket.accept();
					System.out.println("Request made");
					connectionProcessorThreadPool
							.execute(new ConnectionProcessor(acceptedSocket, "Thread Pooled Server"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

    private void handleRequest(Socket client) {
    	
		try {
			HttpRequest request = new HttpRequestFactory().create(client.getInputStream());
			//String routeTo  = new Route(request).getRouteFromRequest();
			
			HttpResponse response = null;
			route = new Route(request);
			response = route.getResponseFromClass(registeredRoute);
			if(response == null) {
				String errorMessage = "<B>Invalid</B> API request"; 								
				response = new HttpResponse(StatusCode.ERROR, ContentType.TEXT,errorMessage);
			}
			sendResponse(response, client);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    
    }
    
    private void sendResponse(HttpResponse response,Socket client) {
		try {
			PrintStream ps = new PrintStream(client.getOutputStream());
			ps.println(response);
			ps.close();
		} catch (Exception e) {
			System.out.println("Could not send response to the client! " +e);
			e.printStackTrace();
		}
	}

	private class ConnectionProcessor implements Runnable {

		private Socket acceptedSocket = null;
		private String serverMsg = null;

		public ConnectionProcessor(Socket acceptedSocket, String serverMsg) {
			this.acceptedSocket = acceptedSocket;
			this.serverMsg = serverMsg;
		}
		
		
		public void run() {
			try {
				System.out.println("handling request from: " + acceptedSocket.getInetAddress().getHostAddress() );
				

			handleRequest(acceptedSocket);
			
//				InputStream clientMsg = acceptedSocket .getInputStream();
//				OutputStream output = acceptedSocket.getOutputStream();
//				BufferedReader input = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
//
//				long time = System.currentTimeMillis();
//				output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverMsg + " - " + "Accepted socket: "
//						+ acceptedSocket.getInetAddress() + " -- " + time).getBytes());
////
//				output.close();
////				input.close();
////				System.out.println("Request processed at: " + time);
////				output.write(request.getUri().getBytes());
				//sendResponse(new HttpResponseFactory().ok(),acceptedSocket);
				//System.out.println("request" +request);
				acceptedSocket.close();
				System.out.println("----------------------------");
				
			} catch (Exception e) {
				System.out.println("Error while serving: " + acceptedSocket.getInetAddress());
				e.printStackTrace();
			}

		}

	}

	public RegisteredRoute getRegisteredRoute() {
		return registeredRoute;
	}

	public void setRegisteredRoute(RegisteredRoute registeredRoute) {
		this.registeredRoute = registeredRoute;
	}
	
	
}
