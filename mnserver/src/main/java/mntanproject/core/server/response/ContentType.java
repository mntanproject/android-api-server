package mntanproject.core.server.response;

public enum ContentType {
	
	TEXT("text/html"),
	JSON("application/json");
	
	private final String type;

	private ContentType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
