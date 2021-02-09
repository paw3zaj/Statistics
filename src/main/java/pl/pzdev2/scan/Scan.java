package pl.pzdev2.scan;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pl.pzdev2.virtua.Virtua;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Scan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime createdDate;
	@OneToOne
	private Virtua virtua;
	private String scanType;
	private Integer year;
	private Integer month;

	public Scan() {
	}

	public Scan(LocalDateTime createdDate, Virtua virtua, String scanType) {
		this.createdDate = createdDate;
		this.virtua = virtua;
		this.scanType = scanType;
	}
}