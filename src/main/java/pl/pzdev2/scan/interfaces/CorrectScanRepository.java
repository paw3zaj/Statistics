package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.statistics.interfaces.Month;

import java.util.List;

public interface CorrectScanRepository extends JpaRepository<CorrectScan, Long> {

    @Query(value = "select * from scan s where s.year = ?1"
            + " and s.month = ?2", nativeQuery = true)
    List<CorrectScan> findAllCorrectScansInMonth(int year, int month);

    @Query(value = "select * from scan s where"
            + " s.year = ?1", nativeQuery = true)
    List<CorrectScan> findAllCorrectScansInYear(int year);

    @Query(value = "select count(id) from scan s " +
            "join virtua v on s.virtua_id_virtua = v.id_virtua " +
            "where  s.year= ?1 and s.month = ?2 " +
            "and v.barcode = ?3", nativeQuery = true)
    int countMonthlyBookBorrowing(int year, int month, String barcode);

    @Query(value = "select v.author, v.title, count(s.id) as amount" +
            " from scan s join virtua v on s.virtua_id_virtua = v.id_virtua" +
            " where s.year = ? and s.month = ?" +
            " group by v.author, v.title" +
            " order by amount desc"
            , nativeQuery = true)
    List<Month> countTotalScansInMonth(int year, int month);
}
