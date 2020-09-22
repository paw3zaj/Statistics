package pl.pzdev2.virtua;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VirtuaRepository extends JpaRepository<Virtua, Long> {
	
	Virtua findByIdVirtua(Long id);
	Virtua findByBarcode(String barcode);

	@Transactional
	@Modifying
	@Query("update Virtua v set v.readingRoom =  ?1, v.signature = ?3, v.barcode = ?4, v.author = ?5, v.title = ?6 where v.idVirtua = ?2")
	void setFixedVirtua(String readingRoom, Long idVirtua, String signature, String barcode, String author, String title);
	
	@Query("select e from Virtua e where year(e.createdDate) = ?1 and month(e.createdDate) = ?2")
	List<Virtua> findByYearAndMonth(int year, int month);

}
