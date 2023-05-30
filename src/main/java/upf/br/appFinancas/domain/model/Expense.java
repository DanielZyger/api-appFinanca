package upf.br.appFinancas.domain.model;

import java.time.Instant;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_expense")
public class Expense extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "date")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Embedded
    private Audit audit = new Audit();

    @PrePersist
    private void prePersist() {
        audit.setCreated_at(Instant.now());
    }

    @PreUpdate
    private void preUpdate() {
        audit.setUpdated_at(Instant.now());
    }
}

