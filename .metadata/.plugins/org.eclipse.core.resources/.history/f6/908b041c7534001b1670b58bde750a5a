package mntanproject.core.server.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mntanproject.core.server.request.HttpRequest;
import mntanproject.core.server.request.RegisteredRoute;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.pos.api.SupplierResponse;

public class Route {

	String uri = null;
	String api = null;
	String route = null;
	String method = null;
	String params = null;
	boolean apiAccess = false;

	// HashMap<String,String> params = new HashMap<String,String>();
	HttpRequest request;

	public Route(HttpRequest request) {
		super();
		this.request = request;
		this.uri = request.getUri();
		if(!isRequestEmptyUri()) {
			processRouteFromRequest();
		}
	}

	private boolean isRequestEmptyUri() {
		if (uri != null && uri.length() != 0) {
			return false;
		}
		return true;
	}

	public void processRouteFromRequest() {

		String[] splits = uri.split("/");
		int splitsLength = 0;
		splitsLength = splits.length;
//		for (int i = 0; i < splits.length; i++) {
//			System.out.println("route: " + i + " - " + splits[i]);
//		}
		if (splitsLength >= 5) {
			api = splits[1];
			if (api != null && !api.isBlank()) {
				if (api.equalsIgnoreCase("api")) {
					route = splits[2];
					method = splits[3];
					params = splits[4];
				}

			}

		}

	}

	public HttpResponse getResponseFromClass(RegisteredRoute registeredRoute) {
		HashMap<String, String> routes = new HashMap<String, String>();
		routes = registeredRoute.getRegisteredRoutes();

		HttpResponse response = null;
		if (!routes.isEmpty() && route != null) {
			for (Map.Entry<String, String> hm : routes.entrySet()) {
				if (hm.getKey().toLowerCase().equals(route.toLowerCase())) {
					System.out.println("API Found: " + hm.getKey());
					response = getReflectionResponse(hm.getValue());
				} else {
					System.out.println("API Not Found");
				}
			}
		}

		return response;
	}

	
	public HttpResponse getReflectionResponse(String clazz) {

		HttpResponse response = null;
		try {
			// SupplierResponse supplier;
			Class<?> classRef;
			classRef = Class.forName(clazz);
			// System.out.println("created class: " + route);
			Object instance = classRef.newInstance();
			// System.out.println("created object: " + route);
			boolean methodFound = false;
			for (Method clazzMethod : classRef.getDeclaredMethods()) {
				if(clazzMethod.getName().equalsIgnoreCase(method)) {
					methodFound = true;
					break;
				}
			}
			if(methodFound) {
				//Do some checking for the method
				//Method must accept one parameter String.class
				//Method must return HttpResponse.class
				//otherwise reject process
				Method invokeMethod = classRef.getDeclaredMethod(method,String.class);
				Class<?>[] invokeMethodParams = invokeMethod.getParameterTypes();
				int invokeMethodParamsCount = invokeMethodParams.length;
				if(invokeMethodParamsCount == 1 && invokeMethodParams[0].equals(String.class) && invokeMethod.getReturnType().equals(HttpResponse.class)) {
					response = (HttpResponse) invokeMethod.invoke(instance,params);
				} else {
					
					System.out.println("Invalid method Found: " + method + ", must accept only one String as parameter and return object in HttpResponse class");
				}
				
			} else {
				
				System.out.println("Method not found in API");
			}
			

		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("instantiation exeption");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("illegal access");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;

	}

}
