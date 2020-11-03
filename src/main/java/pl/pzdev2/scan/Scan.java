package pl.pzdev2.scan;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown=true)
@DiscriminatorColumn(name ="scan_type")
@ToString
@Entity
@Getter
public abstract class Scan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime createdDate;

	public Scan() {
	}

	public Scan(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}