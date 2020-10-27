package pl.pzdev2.scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    private List<ScannerData> barcodeList;
    @InjectMocks
    private ScannerService scannerService;
    @Mock
    private VirtuaRepository virtuaRepository;

    @BeforeEach
    void initializerOrder() {
        barcodeList = prepareScannerData();
    }

    @AfterEach
    void cleanUp() {
        barcodeList.clear();
    }

    @Test
    void badScansListShouldHaveTreeElements() {
        //given
        scannerService.addToBadScans(new ScannerData("barcode1", "createdDate1"));
        scannerService.addToBadScans(new ScannerData("barcode2", "createdDate2"));
        scannerService.addToBadScans(new ScannerData("barcode3", "createdDate3"));

        //when
        List<ScannerData> list = scannerService.getBadScans();

        //then
        assertThat(list, hasSize(3));
    }

    @Test
    void correctScansListShouldBeEmpty() {
        //given
        given(virtuaRepository.findByBarcode("barcode4")).willReturn(Virtua.builder()
                .idVirtua(3L).signature("signature4").barcode("barcode4").build());
        virtuaRepository.findByBarcode("barcode4");

        //when
        List<ScannerData> correctScans = scannerService.extractCorrectScans(barcodeList);
        List<ScannerData> badScans = scannerService.getBadScans();

        //then
        assertThat(correctScans, hasSize(0));
        assertThat(badScans, hasSize(3));

    }

    @Test
    void correctScansListShouldHaveOneElement() {
        //given
        given(virtuaRepository.findByBarcode("barcode2")).willReturn(Virtua.builder()
                .idVirtua(3L).signature("signature2").barcode("barcode2").build());


        virtuaRepository.findByBarcode("barcode2");

        //when
        List<ScannerData> correctScans = scannerService.extractCorrectScans(barcodeList);
        List<ScannerData> badScans = scannerService.getBadScans();

        //then
        assertThat(correctScans, hasSize(1));
        assertThat(correctScans.get(0).getBarcode(), equalTo("barcode2"));
        assertThat(badScans, hasSize(2));

    }

    private List<ScannerData> prepareScannerData() {
        ScannerData scan1 = new ScannerData("barcode1", "createdDate1");
        ScannerData scan2 = new ScannerData("barcode2", "createdDate2");
        ScannerData scan3 = new ScannerData("barcode3", "createdDate3");

        return new ArrayList<>(Arrays.asList(scan1, scan2, scan3));
    }

}