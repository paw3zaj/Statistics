package pl.pzdev2.scan;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.ToString;
import pl.pzdev2.virtua.Virtua;

@Entity
@ToString
@DiscriminatorValue(value = "correct")
public class CorrectScan extends Scan {
	
	@OneToOne
	private Virtua virtua;

	public CorrectScan() {
	}

	public CorrectScan(LocalDateTime createdDate, Virtua virtua) {
		super(createdDate);
		this.virtua = virtua;
	}

	public Virtua getVirtua() {
		return virtua;
	}

	public void setVirtua(Virtua virtua) {
		this.virtua = virtua;
	}
	
}

