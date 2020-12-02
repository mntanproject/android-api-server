package mntanproject.pos.api;

import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

public class SupplierResponse  {

	HttpResponse response;

	public SupplierResponse() {
		super();
		response = new HttpResponse(StatusCode.OK, ContentType.TEXT, "Response from supplier class");
		
	};
	
	public String test() {
		return null;
	}
	public HttpResponse response(String params) {
		response.setContent(params);
		return response;
	}
	
	
}
