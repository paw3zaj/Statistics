package pl.pzdev2.scanner;

import org.springframework.stereotype.Component;
import pl.pzdev2.utility.DateTimeUtility;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class BarcodeComparator {

    public boolean checkTheScans(ScannerData error, ScannerData correct) {
        if(shortTimeLapse(error, correct)){
            return compareBarcode(error, correct);
        }
        return false;
    }
    public boolean shortTimeLapse(ScannerData error, ScannerData correct) {

        LocalDateTime errorTime = DateTimeUtility.convertStringToLocalDateTime(error.getCreatedDate());
        LocalDateTime correctTime = DateTimeUtility.convertStringToLocalDateTime(correct.getCreatedDate());

        Duration timeElapsed = Duration.between(errorTime, correctTime);
        long second = timeElapsed.toSeconds();

        return Math.abs(second) <= 1L;
    }

    public boolean compareBarcode(ScannerData error, ScannerData correct) {

        char[] errBarcode = error.getBarcode().toCharArray();
        char[] corBarcode = correct.getBarcode().toCharArray();

        int counter = 0;

        for (int i = 0; i < corBarcode.length; i++) {
            if (corBarcode[i] != errBarcode[i]) {
                counter++;
            }
            if (counter > 3) {
                return false;
            }
        }
        return true;
    }
}
