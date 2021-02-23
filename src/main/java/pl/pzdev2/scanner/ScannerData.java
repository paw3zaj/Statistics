package pl.pzdev2.scanner;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class ScannerData {

	private String barcode;
	private String createdDate;
}
