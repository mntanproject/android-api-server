package mntanproject.core.server.response;

public class TestResponse {

	public static void main(String[] args) {
		System.out.println(ContentType.JSON);
		HttpResponse response =  new HttpResponse(StatusCode.OK, ContentType.JSON, "TEt"); 
		System.out.println(response);

	}

}
