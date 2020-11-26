package pl.pzdev2.statistics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.scan.interfaces.BadScanRepository;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.Status;
import pl.pzdev2.virtua.Virtua;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    private Virtua v1;
    private Virtua v2;
    private Virtua v3;
    private List<CorrectScan> scans;

    @InjectMocks
    private StatisticsService statisticsService;
    @Mock
    private ScanRepository scanRepository;
    @Mock
    private BadScanRepository badScanRepository;
    @Mock
    private CorrectScanRepository correctScanRepository;

    @BeforeEach
    void initializerCorrectScansList() {
        scans = prepareScanData();
    }

    @AfterEach
    void cleanUp() {
        scans.clear();
    }

    @Test
    void mapAfterGroupingShouldHaveThreeElements() {
        //given
        //when
        Map<Virtua, Integer> groupedScans = statisticsService.groupingOfScans(scans);

        //then
        assertThat(groupedScans.size(), equalTo(3));
        assertThat(groupedScans.get(v1), equalTo(4));
        assertTrue(groupedScans.containsKey(v3));
    }

    @TestFactory
    Collection<DynamicTest> mapShouldBeSortedFromTheHighestValueToTheLowest() {
        //given
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        Map<Virtua, Integer> groupedScans = statisticsService.groupingOfScans(scans);
        Integer cache = 5;

        //when
        Map<Virtua, Integer> sortedScans = statisticsService.sortScans(groupedScans);

        //then
        for(Integer v : sortedScans.values()) {

            final Integer previousV = cache;
            Executable executable = () -> assertThat(v, lessThan(previousV));
            cache = v;

            String name = "Value in order: " + v;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    private List<CorrectScan> prepareScanData() {
        v1 = Virtua.builder().idVirtua(1L).signature("signature1").barcode("barcode1").status(Status.IN).build();
        v2 = Virtua.builder().idVirtua(2L).signature("signature2").barcode("barcode2").status(Status.IN).build();
        v3 = Virtua.builder().idVirtua(3L).signature("signature3").barcode("barcode3").status(Status.IN).build();
        var cS1 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v1);
        var cS2 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v2);
        var cS3 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v3);
        var cS4 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v1);
        var cS5 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v1);
        var cS6 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v2);
        var cS7 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v3);
        var cS8 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v3);
        var cS9 = new CorrectScan(FormatDateTime.convertToLocalDateTime("1999-12-13 12:23:32"), v1);

        return new ArrayList<>(List.of(cS1, cS2, cS3, cS4, cS5, cS6, cS7, cS8, cS9));
    }
}