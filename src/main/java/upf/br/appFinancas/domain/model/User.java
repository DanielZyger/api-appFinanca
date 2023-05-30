package upf.br.appFinancas.domain.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 300)
    private String name;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "password", length = 60)
    private String password;

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
