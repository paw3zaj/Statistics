package pl.pzdev2.scan;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
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

}
