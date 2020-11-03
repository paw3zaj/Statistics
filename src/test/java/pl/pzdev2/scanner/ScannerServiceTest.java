package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pzdev2.scan.BadScan;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    private List<ScannerData> barcodeList;
    @InjectMocks
    private ScannerService scannerService;
    @Mock
    private VirtuaRepository virtuaRepository;

    @BeforeEach
    void initializerBarcodeList() {
        barcodeList = prepareScannerData();
    }

    @AfterEach
    void cleanUp() {
        barcodeList.clear();
    }

    @Test
    void correctScansListShouldHaveThreeElements() {
        //given
        Virtua virtua = Virtua.builder().idVirtua(1L).signature("signature1").barcode("barcode1").build();

        //when
        scannerService.addToCorrectScans(new CorrectScan(FormatDateTime.convertToLocalDateTime(
                "1982-12-13 12:23:32"), virtua));
        scannerService.addToCorrectScans(new CorrectScan(FormatDateTime.convertToLocalDateTime(
                "1992-12-13 12:23:32"), virtua));
        scannerService.addToCorrectScans(new CorrectScan(FormatDateTime.convertToLocalDateTime(
                "1999-12-13 12:23:32"), virtua));

        //then
        assertThat(scannerService.getCorrectScans(), hasSize(3));
        assertThat(scannerService.getCorrectScans().get(0).getCreatedDate(), equalTo(
                FormatDateTime.convertToLocalDateTime("1982-12-13 12:23:32")));
    }

    @Test
    void badBarcodesListShouldHaveTwoElement() {
        //given
        given(virtuaRepository.findByBarcode("barcode3")).willReturn(Virtua.builder()
                .idVirtua(3L).signature("signature3").barcode("barcode3").build());
        virtuaRepository.findByBarcode("barcode3");

        //when
        List<ScannerData> badBarcodes = scannerService.extractBadBarcodes(barcodeList);

        //then
        assertThat(badBarcodes, hasSize(2));
        assertThat(badBarcodes.get(0).getBarcode(), equalTo("barcode1"));

    }

    @Test
    void correctScansListShouldHaveOneElement() {
        //given
        given(virtuaRepository.findByBarcode("barcode3")).willReturn(Virtua.builder()
                .idVirtua(3L).signature("signature3").barcode("barcode3").build());
        virtuaRepository.findByBarcode("barcode3");

        //when
        scannerService.extractBadBarcodes(barcodeList);
        List<CorrectScan> correctScans = scannerService.getCorrectScans();

        //then
        assertThat(correctScans, hasSize(1));
        assertThat(correctScans.get(0).getCreatedDate(), equalTo(
                FormatDateTime.convertToLocalDateTime("1992-12-13 12:23:32")));

    }

    @Test
    void jsonMappingShouldConvertStringToListScannerDataObject() throws JsonProcessingException {
        //given
        String json = "[\n" +
                "    {\n" +
                "        \"barcode\":\"barcode3\",\n" +
                "        \"createdDate\":\"2019-12-20 13:13:13\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"barcode\":\"barcode4\",\n" +
                "        \"createdDate\":\"1982-11-19 12:12:12\"\n" +
                "    }\n" +
                "]";

        //when
        List<ScannerData> map = scannerService.barcodeMapping(json);

        //then
        assertThat(map, hasSize(2));
        assertThat(map.get(1).getBarcode(), equalTo("barcode4"));
    }

    @Test
    void scannerDataListShouldBeConvertedToBadScanObjectList() {
        //given
        //when
        List<BadScan> badScans = scannerService.addAllToBadScans(barcodeList);

        //then
        assertThat(badScans, hasSize(3));
        assertThat(badScans.get(2).getBarcode(), equalTo("barcode3"));
    }

    private List<ScannerData> prepareScannerData() {
        ScannerData scan1 = new ScannerData("barcode1", "1982-12-13 12:23:32");
        ScannerData scan2 = new ScannerData("barcode2", "1987-12-13 12:23:32");
        ScannerData scan3 = new ScannerData("barcode3", "1992-12-13 12:23:32");

        return new ArrayList<>(Arrays.asList(scan1, scan2, scan3));
    }

}