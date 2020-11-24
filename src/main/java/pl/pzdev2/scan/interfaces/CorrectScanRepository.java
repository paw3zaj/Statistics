package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pzdev2.scan.CorrectScan;

import java.util.List;

public interface CorrectScanRepository extends JpaRepository<CorrectScan, Long> {

    @Query(value = "select * from scan s where year(s.created_date) = ?1"
            + " and month(s.created_date) = ?2", nativeQuery = true)
    List<CorrectScan> findAllCorrectScansInMonth(int year, int month);

    @Query(value = "select * from scan s where"
            + " year(s.created_date) = ?1", nativeQuery = true)
    List<CorrectScan> findAllCorrectScansInYear(int year);
}
