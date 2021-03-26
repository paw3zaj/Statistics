package pl.pzdev2.virtua;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class VirtuaAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreated;
    private String barcode;
    @Column(nullable = false)
    private String property;
    private String oldValue;
    private String newValue;

}
