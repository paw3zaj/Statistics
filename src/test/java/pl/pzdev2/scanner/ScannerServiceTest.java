package pl.pzdev2.scanner;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    @InjectMocks
    private ScannerService scannerService;

    @Test
    void twoScannerDataListsShouldBeEmpty() {
        //given
        List<ScannerData> scannerList = new LinkedList<>();
        List<ScannerData> fileList = new LinkedList<>();

        ScannerData a = new ScannerData("bar", "1982-12-13 12:23:32");
        ScannerData b = new ScannerData("bar", "1982-12-13 12:23:32");
        ScannerData d = new ScannerData("barD", "1982-12-13 12:23:32");
        ScannerData f = new ScannerData("bar", "2020-12-13 12:23:32");

        scannerList.add(a);
        scannerList.add(d);
        scannerList.add(f);

        fileList.add(d);
        fileList.add(b);
        fileList.add(f);

        //when
        scannerList.removeAll(fileList);

        //then
        assertThat(scannerList, empty());
        assertThat(scannerList, hasSize(0));
    }

    @Test
    void elapseTimeShouldByLessOrEqualThenOneSecond(){
        //given
        ScannerData error = new ScannerData("bar1", "1982-12-13 12:23:30");
        ScannerData correct = new ScannerData("bar2", "1982-12-13 12:23:29");

        //when
        boolean elapse = scannerService.shortTimeLapse(error, correct);

        //then
        assertThat(elapse, is(true));
        assertTrue(elapse);
    }

    @Test
    void elapseTimeShouldBeGrateThenOneSecond(){
        //given
        ScannerData error = new ScannerData("bar1", "1982-12-13 12:23:31");
        ScannerData correct = new ScannerData("bar2", "1982-12-13 12:23:35");

        //when
        boolean elapse = scannerService.shortTimeLapse(error, correct);

        //then
        assertThat(elapse, is(false));
        assertFalse(elapse);
    }

    @Test
    void compareBarcodesShouldReturnTrue() {
        //given
        ScannerData err = new ScannerData("000837461573", "1982-12-13 12:23:31");
        ScannerData cor = new ScannerData("020837361573", "1982-12-13 12:23:31");

        //when
        boolean same = scannerService.compareBarcode(err, cor);

        //then
        assertThat(same, is(true));
        assertTrue(same);
    }

    @Test
    void compareBarcodesShouldReturnFalse() {
        //given
        ScannerData err = new ScannerData("000837461573", "1982-12-13 12:23:31");
        ScannerData cor = new ScannerData("100237561574", "1982-12-13 12:23:31");

        //when
        boolean same = scannerService.compareBarcode(err, cor);

        //then
        assertThat(same, is(not(true)));
        assertFalse(same);
    }

    @Test
    void compareScannerDataShouldReturnTrue() {
        //given
        ScannerData err = new ScannerData("000837461573", "1982-12-13 12:23:34");
        ScannerData cor = new ScannerData("020837361573", "1982-12-13 12:23:35");

        //when
        boolean same = scannerService.checkTheScans(err, cor);

        //then
        assertThat(same, is(true));
        assertTrue(same);
    }

    @Test
    void compareScannerDataShouldReturnFalse() {
        //given
        ScannerData err = new ScannerData("000837461573", "1982-12-13 12:23:31");
        ScannerData cor = new ScannerData("111837460573", "1982-12-13 12:23:30");

        //when
        boolean same = scannerService.checkTheScans(err, cor);

        //then
        assertThat(same, is(not(true)));
        assertFalse(same);
    }

}