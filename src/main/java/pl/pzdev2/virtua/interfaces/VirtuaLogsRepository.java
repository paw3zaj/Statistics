package pl.pzdev2.virtua.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pzdev2.virtua.VirtuaLogs;

public interface VirtuaLogsRepository extends JpaRepository<VirtuaLogs, Long> {

	List<VirtuaLogs> findByBarcode(String barcode);
}
