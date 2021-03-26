package pl.pzdev2.virtua;

public enum Status {

	OUT("poza czytelnią"),
	IN("w czytelni");

	private final String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
