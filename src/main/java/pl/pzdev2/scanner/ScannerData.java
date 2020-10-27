package pl.pzdev2.scanner;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ScannerData {

	private String barcode;
	private String createdDate;
}
