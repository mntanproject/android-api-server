package mntanproject.pos.supplier;

import java.lang.reflect.ParameterizedType;

import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

public class GenericApi<T> {

	
	
	private Class<T> type;

	public GenericApi() {
		this.type =  (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public HttpResponse test(String params) {
		System.out.println("TEST CALLED generic" + params);
		return new HttpResponse(StatusCode.OK, ContentType.TEXT, "Response from supplier class");
	}
	
	
	
	
	
	
}
