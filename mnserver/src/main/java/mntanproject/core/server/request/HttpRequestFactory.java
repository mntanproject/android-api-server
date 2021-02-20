package mntanproject.core.server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class HttpRequestFactory {

	HashMap<String, String> httpHeader = new HashMap<String, String>();
	String httpMethod = null;
	String httpUri = null;
	String httpVersion = null;
	String httpBody = null;

	public HttpRequest create(InputStream is)throws Exception {
	
	
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(isr);
		
		String line = null;
        line = in.readLine();
        System.out.println("line create: " + line);
        if(line != null) {
        	//System.out.println("HTTP-HEADER: " + line);
            httpMethod = extractMethod(line);
            httpUri = extractUri(line);
            httpVersion = extractHttpVersion(line);
            generateHeaderBody(in);
            //System.out.println("Post Data: " + httpBody);
             	
        } else {
        	System.out.println("input stream empty: " + line);
        }
        
    
        
		
		
		// only handle valid http request otherwise null is returned
//		while (scanner.hasNextLine())
//		{
//			if(i==0) {
//				startLine= scanner.nextLine();
//				if (startLine != null) {
//					if((!startLine.isEmpty()) && startLine.split(" ").length == 3) {
//						method = extractMethodFrom(startLine);
//						uri = extractUriFrom(startLine);
//						version = extractHttpVersionFrom(startLine);
//						i++;
//					
//					}
//				}
//			} else {
//				String line = scanner.nextLine();
//				System.out.println(i + " - " + line);
//				String[] splitLine = line.split(":");
//				if(splitLine.length == 2) {
//					requestMap.put(splitLine[0], splitLine[1]);
//				}else {
//					if(line.length()!=0 && line.trim().length()>0) {
//						requestMap.put("body", line);
//					}
//				}
//				
//			}
//		}
		
		return new HttpRequest(httpMethod, httpUri, httpVersion,httpHeader,httpBody);
	}
	
	private String extractMethod(String line) {
		String methodName = line.split(" ")[0];
		return methodName;
	}
	
	private String extractUri(String line) {
		return line.split(" ")[1];
	}
	
	private String extractHttpVersion(String line) {
		return line.split(" ")[2];
	}
	
	private void generateHeaderBody(BufferedReader in) throws NumberFormatException, IOException {
        int postDataIndex = -1;
        String line = "";
        while ((line = in.readLine()) != null && (line.length() != 0)) {
        	//System.out.println("line header body: " + line);
        	String[] splits = line.split(":");
        	if(splits != null && splits.length == 2) {
        		httpHeader.put(splits[0], splits[1]);
        	}
            if (line.indexOf("Content-Length:") > -1) {
                postDataIndex = new Integer(
                        line.substring(
                                line.indexOf("Content-Length:") + 16,
                                line.length())).intValue();
            }
        }
        // read the post data
        if (postDataIndex > 0) {
            char[] charArray = new char[postDataIndex];
            in.read(charArray, 0, postDataIndex);
            httpBody = new String(charArray);
        }
 
        
    	
	}
	
	
}
