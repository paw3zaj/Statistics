package pl.pzdev2.scan.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pzdev2.scan.Scan;

public interface ScanRepository extends JpaRepository<Scan, Long> {

	@Query(value = "select * from scan s where"
			+ " year(s.created_date) = ?1 and month(s.created_date) = ?2", nativeQuery = true)
	List<Scan> findByYearAndMonth(int year, int month);
}
