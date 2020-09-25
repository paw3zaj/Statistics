package pl.pzdev2.scan;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.ToString;

@Entity
@ToString
@DiscriminatorValue(value = "bad")
public class BadScan extends Scan {

	private String barcode;

	public BadScan() {
		super();
	}

	public BadScan(LocalDateTime createdDate, String barcode) {
		super(createdDate);
		this.barcode = barcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
