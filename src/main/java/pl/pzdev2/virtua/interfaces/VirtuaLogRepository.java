package pl.pzdev2.virtua.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pzdev2.virtua.VirtuaLog;

public interface VirtuaLogRepository extends JpaRepository<VirtuaLog, Long> {

	List<VirtuaLog> findByBarcode(String barcode);
}
