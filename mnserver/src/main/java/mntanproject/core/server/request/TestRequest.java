package mntanproject.core.server.request;

import mntanproject.core.server.route.Route;

public class TestRequest {

	public static void main(String[] args) {
		Route route = new Route(new HttpRequest(HttpMethod.GET,
				"/login/test.html?name=martin",
				"HTTP/1.1"));
		//System.out.println("Route:" + route.getRouteFromRequest());

	}

}
