package mntanproject.core.server.response;

public class HttpResponse {

	private StatusCode statusCode = null;
	private ContentType contentType = null;
	public String content = null;
	
	public HttpResponse(StatusCode statusCode,ContentType contentType,String content) {
	
		this.statusCode = statusCode;
		this.contentType = contentType;
		this.content = content;
	
	}
	
	public String getProtocolStatusLine() {
		return "HTTP/1.1 " + statusCode + "\r\n";
	}
	
	public String getContentTypeLine() {
		return "Content-type: " + contentType + "\r\n";
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
		httpString = getProtocolStatusLine() + getContentTypeLine() + getEndHeader() + content + getEndContent();
		//System.out.println("httpString: " + httpString);
		return httpString;
	}
	
	
	
}
