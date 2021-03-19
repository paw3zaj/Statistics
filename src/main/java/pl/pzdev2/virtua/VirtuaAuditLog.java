package pl.pzdev2.virtua;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VirtuaAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreated;
    private Long idVirtua;
    @Column(nullable = false)
    private String property;
    private String oldValue;
    private String newValue;

    public VirtuaAuditLog() {
    }

    public VirtuaAuditLog(LocalDate dateCreated, Long idVirtua, String property, String oldValue, String newValue) {
        this.dateCreated = dateCreated;
        this.idVirtua = idVirtua;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getProperty() {
        return property;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }
}
