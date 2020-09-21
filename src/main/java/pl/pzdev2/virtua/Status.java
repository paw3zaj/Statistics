package pl.pzdev2.virtua;

public enum Status {

	OUT("poza czytelnią"),
	IN("w czytelni");
	
	private final String value;

	private Status(String value) {
		this.value = value;
	}
	
	public String getStatus() {
		return this.value;
				
	}
	
}
