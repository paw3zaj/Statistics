package pl.pzdev2.scanner;

import org.junit.jupiter.api.*;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class ScannerServiceTest {

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
}