package pl.pzdev2.scan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pzdev2.scan.BadScan;

public interface BadScanRepository extends JpaRepository<BadScan, Long> {
}
