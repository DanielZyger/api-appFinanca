package upf.br.appFinancas.domain.model;

import java.time.Instant;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_invest")
public class Invest extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "goal")
    private Float goal;
    
    @Column(name = "percentage")
    private Float percentage;

    @Column(name = "date")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getGoal() {
        return goal;
    }

    public void setGoal(Float goal) {
        this.goal = goal;
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
    
