package mntanproject.core.server.request;

import java.util.HashMap;

public class HttpRequest {

	private final String method;
	private final String uri;
	private final String httpVersion;
	private final HashMap<String, String> httpHeader;
	private final String httpBody;

	public HttpRequest(String method, String uri, String httpVersion,HashMap<String, String> httpHeader,String httpBody) {
		this.method = method;
		this.uri = uri;
		this.httpVersion = httpVersion;
		this.httpHeader = httpHeader;
		this.httpBody = httpBody;
	}
	
	@Override
	public String toString() {
		return getStartLine();
	}

	public String getStartLine() {
		return method +" " +uri +" " +httpVersion;
	}

	public String getMethod() {
		return method;
	}

	public String getUri() {
		return uri;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public HashMap<String, String> getHttpHeader() {
		return httpHeader;
	}

	public String getHttpBody() {
		return httpBody;
	}

	
	
}

