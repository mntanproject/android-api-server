package mntanproject.core.server.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpResponse {

	private StatusCode statusCode = null;
	private ContentType contentType = null;
	public String content = null;
	public HashMap<String, String> headers = new HashMap<String, String>();
	
	public HttpResponse(StatusCode statusCode,ContentType contentType,String content) {
	
		this.statusCode = statusCode;
		this.contentType = contentType;
		this.content = content;
		this.headers.put("Access-Control-Allow-Origin", "*");
		if(content != null && content.length()!= 0) {
			this.headers.put("Content-Length", Integer.toString(content.length()));
		}
		this.headers.put("Content-type", contentType.toString());
		
		
	
	}
	
	public String getProtocolStatusLine() {
		return "HTTP/1.1 " + statusCode + "\r\n";
	}
	
	public String generateHeaders() {
		String headersString = "";
		
		Set<Map.Entry<String, String>> set = headers.entrySet();
		for (Map.Entry<String, String> header : set) {
		      headersString = headersString + (header.getKey() + ":" + header.getValue() + "\r\n");
		}
		
		return headersString;
	}
	public String getEndHeader() {
		return ("\r\n"); 
	}

	public String getEndContent() {
		return ("\r\n"); 
	}

	
	
	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String httpString = null;
		
		httpString = getProtocolStatusLine() + generateHeaders() + getEndHeader() + content + getEndContent();
//		httpString = "HTTP/1.1 " + statusCode + " " + "Good" + "\r\n" +
//				"Content-Length" + ": " + Integer.toString(content.length()) + "\r\n" + 
//				"Access-Control-Allow-Origin: *\r\n" +
//				"\r\n" + 
//				content;
//		
		System.out.println("httpString: " + httpString);
		return httpString;
	}
	
	
	
}
