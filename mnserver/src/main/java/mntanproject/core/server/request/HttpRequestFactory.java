package mntanproject.core.server.request;

import java.io.InputStream;
import java.util.Scanner;

public class HttpRequestFactory {

	public HttpRequest create(InputStream is)throws Exception {
	
		Scanner scanner = null;
		scanner = new Scanner(is);
		String startLine = null;
		HttpMethod method = null;
		String uri = null;
		String version = null;
	
		// only handle valid http request otherwise null is returned
		if (scanner.hasNextLine()) {
			startLine= scanner.nextLine();
			if (startLine != null) {
				if((!startLine.isEmpty()) && startLine.split(" ").length == 3) {
					method = extractMethodFrom(startLine);
					uri = extractUriFrom(startLine);
					version = extractHttpVersionFrom(startLine);
				}
			}
		}
		return new HttpRequest(method, uri, version);
	}
	
	private HttpMethod extractMethodFrom(String startLine) {
		String methodName = startLine.split(" ")[0];
		return HttpMethod.valueOf(methodName);
	}
	
	private String extractUriFrom(String startLine) {
		return startLine.split(" ")[1];
	}
	
	private String extractHttpVersionFrom(String startLine) {
		return startLine.split(" ")[2];
	}
	
}
