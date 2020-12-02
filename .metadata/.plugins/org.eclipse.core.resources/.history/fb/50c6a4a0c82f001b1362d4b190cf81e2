package mntanproject.core.server.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	String route;
	HttpRequest request;

	
	public Route(HttpRequest request) {
		super();
		this.request = request;
		this.uri = request.getUri();
	}


	private boolean isRequestEmptyUri() {
		if(uri != null&&uri.length()!=0) {
			return false;
		}
		return true;
	}
	
	public String getRouteFromRequest()  {
		//System.out.println("size:" + uri.split("/").length);
		String[] splits = uri.split("/");
		for (int i = 0; i < splits.length; i++) {
			//System.out.println(i + " - " + splits[i]);
		}
		if(splits.length > 1) {
			return uri.split("/")[1]; 
		}
		
		return null; 
		
	}
	
	public HttpResponse getResponseFromClass(RegisteredRoute registeredRoute) {
		HashMap<String,String> routes =  new HashMap<String,String>();
		routes = registeredRoute.getRegisteredRoutes();
		
		HttpResponse response = null;
		if(!routes.isEmpty()&&getRouteFromRequest() != null) {
			for (Map.Entry<String,String> hm: routes.entrySet()) {
				if(hm.getKey().toLowerCase().equals(getRouteFromRequest().toLowerCase()))	{
					System.out.println("API Found: "+ hm.getKey());
					response = getReflectionResponse(hm.getValue());
				}
				else {
					System.out.println("API Not Found");
				}
	        }
		}
		
		return response;
	}
	
	public HttpResponse getReflectionResponse(String clazz) {
		
		HttpResponse response = null;
		try {
			//SupplierResponse supplier;
			Class<?> classRef;
			classRef = Class.forName(clazz);
			//System.out.println("created class: " + route);
			Object instance = classRef.newInstance();
			//System.out.println("created object: " + route);
			
			Method method = classRef.getDeclaredMethod("response");
		    response = (HttpResponse) method.invoke(instance);
		    
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
