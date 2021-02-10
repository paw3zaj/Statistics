package pl.pzdev2.scanner;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.List;

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
}