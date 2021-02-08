package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pzdev2.scan.Scan;

public interface ScanRepository extends JpaRepository<Scan, Long> {

	@Query(value = "select count(id) from scan s"
			+ " where s.year = ?1 and s.month = ?2", nativeQuery = true)
	int countAllScansInMonth(int year, int month);
}
