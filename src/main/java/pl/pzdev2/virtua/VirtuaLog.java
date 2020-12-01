package pl.pzdev2.virtua;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Getter
@Setter
public class VirtuaLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String readingRoom;
	private Long idVirtua;
	private String signature;
	private String barcode;
	private String author;
	@Column(length=500)
	private String title;
	@Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
	@JsonFormat(pattern="d MMMM yyyy")
    private LocalDate createdDate;
    private Status status;
    
}
