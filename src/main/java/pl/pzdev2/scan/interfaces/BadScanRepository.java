package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.pzdev2.scan.BadScan;

public interface BadScanRepository extends JpaRepository<BadScan, Long> {
	
	@Query(value = "select count(id) from scan s where s.scan_type = 'bad'"
			+ " and year(s.created_date) = ?1 and month(s.created_date) = ?2"
			, nativeQuery = true)
	int countIncorrectScansInMonth(int year, int month);

}
