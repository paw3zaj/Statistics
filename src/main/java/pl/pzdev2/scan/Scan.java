package pl.pzdev2.scan;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.pzdev2.virtua.Virtua;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Scan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Virtua virtua;
	private boolean correctScan = true;
	private Integer year;
	private Integer month;

	public Scan() {
	}

	public Scan(Virtua virtua, Integer year, Integer month) {
		this.virtua = virtua;
		if(virtua == null){
			this.correctScan = false;
		}
		this.year = year;
		this.month = month;
	}
}