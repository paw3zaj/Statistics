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
	int countAllScansForTheMonth(int year, int month);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.correct_scan = false and s.year = ? and s.month = ?", nativeQuery = true)
	int countAllIncorrectScansForTheMonth(int year, int month);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.year = ?", nativeQuery = true)
	int countAllScansForTheYear(int year);

	@Query(value = "select count(id)" +
			" from scan s" +
			" where s.correct_scan = false and s.year = ?", nativeQuery = true)
	int countAllIncorrectScansForTheYear(int year);

	@Query(value = "select ROW_NUMBER() over (order by count(s.id) desc) as id," +
			" v.author, v.title, count(s.id) as amount" +
			" from scan s join virtua v on s.virtua_id_virtua = v.id_virtua" +
			" where s.year = ? and s.month = ?" +
			" group by v.author, v.title" +
			" order by amount desc", nativeQuery = true)
	List<Month> countTotalScansForTheMonth(int year, int month);

	@Query(value = "select ROW_NUMBER() over (order by count(s.id) desc) as id," +
			" v.author, v.title, count(s.id) as amount" +
			" from scan s join virtua v on s.virtua_id_virtua = v.id_virtua" +
			" where s.year = ?" +
			" group by v.author, v.title" +
			" order by amount desc", nativeQuery = true)
	List<Month> countTotalScansForTheYear(int year);

	@Query(value = "select count(id) from scan s " +
			"join virtua v on s.virtua_id_virtua = v.id_virtua " +
			"where  s.year= ? and s.month = ? " +
			"and v.title = ?", nativeQuery = true)
	int countTitleScansForTheMonth(Integer year, Integer month, String title);

	@Query(value = "select count(id) from scan s " +
			"join virtua v on s.virtua_id_virtua = v.id_virtua " +
			"where  s.year= ? and s.month = ? " +
			"and v.barcode = ?", nativeQuery = true)
	int countBarcodeScansForTheMonth(Integer year, Integer month, String barcode);
}