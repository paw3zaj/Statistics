package pl.pzdev2.scanner;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ScannerData {

	private String barcode;
	private String createdDate;
}
