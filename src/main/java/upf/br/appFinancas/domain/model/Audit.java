package upf.br.appFinancas.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.time.Instant;

@Embeddable
@Data
public class Audit {

    @Column(name = "created_at",
         columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created_at;

    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updated_at;

}
