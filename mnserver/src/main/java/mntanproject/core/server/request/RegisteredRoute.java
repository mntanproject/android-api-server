package mntanproject.core.server.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisteredRoute {
	
	public HashMap<String,String> registeredRoutes = new HashMap<String, String>();

	public HashMap<String, String> getRegisteredRoutes() {
		return registeredRoutes;
	}

	public void setRegisteredRoutes(HashMap<String, String> registeredRoutes) {
		this.registeredRoutes = registeredRoutes;
	}

	
	
}
