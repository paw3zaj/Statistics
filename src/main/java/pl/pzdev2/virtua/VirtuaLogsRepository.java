package pl.pzdev2.virtua;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtuaLogsRepository extends JpaRepository<VirtuaLogs, Long> {

	List<VirtuaLogs> findByBarcode(String barcode);
}
