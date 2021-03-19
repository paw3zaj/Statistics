package pl.pzdev2.virtua.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pzdev2.virtua.VirtuaAuditLog;

import java.util.List;

public interface VirtuaAuditLogRepository extends JpaRepository<VirtuaAuditLog, Long> {

    List<VirtuaAuditLog> findByIdVirtua(Long idVirtua);
//    List<VirtuaAuditLog> findByBarcode(String barcode);
}
