package pl.pzdev2.scanner;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    private List<ScannerData> barcodeList;
    private List<Virtua> virtuaList;
    @InjectMocks
    private ScannerService scannerService;
    @Mock
    private VirtuaRepository virtuaRepository;

    @BeforeEach
    void initializerBarcodeList() {
        barcodeList = prepareScannerData();
        virtuaList = prepareVirtuaData();
    }

    @AfterEach
    void cleanUp() {
        barcodeList.clear();
        virtuaList.clear();
    }

    private List<ScannerData> prepareScannerData() {
        var scan1 = new ScannerData("barcode1", "1982-12-13 12:23:32");
        var scan2 = new ScannerData("barcode2", "1987-12-13 12:23:32");
        var scan3 = new ScannerData("barcode3", "1992-12-13 12:23:32");

        return new ArrayList<>(List.of(scan1, scan2, scan3));
    }

    private List<Virtua> prepareVirtuaData() {
        var virtua1 = Virtua.builder().idVirtua(1L).signature("signature1").barcode("barcode1").build();
        var virtua2 = Virtua.builder().idVirtua(2L).signature("signature2").barcode("barcode2").build();
        var virtua3 = Virtua.builder().idVirtua(3L).signature("signature3").barcode("barcode3").build();

        return new ArrayList<>(List.of(virtua1, virtua2, virtua3));
    }


    @Test
    void twoScannerDataListsShouldBeEmpty() {
        //given
        List<ScannerData> scannerList = new LinkedList<>();
        List<ScannerData> fileList = new LinkedList<>();

        ScannerData a = new ScannerData("bar", "1982-12-13 12:23:32");
        ScannerData b = new ScannerData("bar", "1982-12-13 12:23:32");
        ScannerData c = new ScannerData("barC", "1982-12-13 12:23:32");
        ScannerData d = new ScannerData("barD", "1982-12-13 12:23:32");
        ScannerData e = new ScannerData("bar", "2020-12-13 12:23:32");
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