package pl.pzdev2.scan;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;


public interface CorrectScanRepository extends JpaRepository<CorrectScan, Long> {
	
//	@Query(value = "select * from scan s where s.scan_type = 'correct' and"
//			+ " year(s.created_date) = ?1 and month(s.created_date) = ?2", nativeQuery = true)
//	List<CorrectScan> findByYearAndMonth(int year, int month);
//	
//	@Query(value = "select count(id) from scan s left join virtua v"
//			+ " on s.virtua_id_virtua = v.id_virtua where s.scan_type = 'correct' and"
//			+ " year(s.created_date) = ?1 and month(s.created_date) = ?2 and v.barcode = ?3", nativeQuery = true)
//	int countForMonth(int year, int month, String barcode);

}
