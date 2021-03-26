package pl.pzdev2.virtua;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Virtua {
	
	private String readingRoom;
	@Id
	private Long idVirtua;
	private String signature;
	private String barcode;
	private String author;
	@Column(length=500)
	private String title;
    private LocalDate createdDate;
	private Status status;
	@Transient
	private String statusValue;

	public void setStatusValue() {
		this.statusValue = this.status.getValue();
	}

	public String getStatusValue() {
		return statusValue;
	}

	@Override
	public String toString() {
		return "Virtua [readingRoom=" + readingRoom + ", idVirtua=" + idVirtua + ", signature=" + signature
				+ ", barcode=" + barcode + ", author=" + author + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((idVirtua == null) ? 0 : idVirtua.hashCode());
		result = prime * result + ((readingRoom == null) ? 0 : readingRoom.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Virtua other = (Virtua) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (idVirtua == null) {
			if (other.idVirtua != null)
				return false;
		} else if (!idVirtua.equals(other.idVirtua))
			return false;
		if (readingRoom == null) {
			if (other.readingRoom != null)
				return false;
		} else if (!readingRoom.equals(other.readingRoom))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			return other.title == null;
		} else return title.equals(other.title);
	}
}
