package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.statistics.interfaces.Month;

import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, Long> {

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.year = ? and s.month = ?", nativeQuery = true)
	int countAllScansInMonth(int year, int month);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.scan_type = 'bad' and s.year = ? and s.month = ?"
			, nativeQuery = true)
	int countIncorrectScansInMonth(int year, int month);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.year = ?", nativeQuery = true)
	int countAllScansInYear(int year);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.scan_type = 'bad' and s.year = ?", nativeQuery = true)
	int countIncorrectScansInYear(int year);

	@Query(value = "select count(id) from scan s " +
			"join virtua v on s.virtua_id_virtua = v.id_virtua " +
			"where  s.year= ?1 and s.month = ?2 " +
			"and v.barcode = ?3", nativeQuery = true)
	int countBookScansInMonth(int year, int month, String barcode);

	@Query(value = "select ROW_NUMBER() over (order by count(s.id) desc) as id," +
			" v.author, v.title, count(s.id) as amount" +
			" from scan s join virtua v on s.virtua_id_virtua = v.id_virtua" +
			" where s.year = ? and s.month = ?" +
			" group by v.author, v.title" +
			" order by amount desc", nativeQuery = true)
	List<Month> countTotalScansInMonth(int year, int month);

	@Query(value = "select ROW_NUMBER() over (order by count(s.id) desc) as id," +
			" v.author, v.title, count(s.id) as amount" +
			" from scan s join virtua v on s.virtua_id_virtua = v.id_virtua" +
			" where s.year = ?" +
			" group by v.author, v.title" +
			" order by amount desc", nativeQuery = true)
	List<Month> countTotalScansInYear(int year);
}
