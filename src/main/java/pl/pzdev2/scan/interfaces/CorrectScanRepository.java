package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pzdev2.scan.CorrectScan;


public interface CorrectScanRepository extends JpaRepository<CorrectScan, Long> {
}
