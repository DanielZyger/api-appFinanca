package upf.br.appFinancas.domain.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@MappedSuperclass
public class Account {
    @Column(name = "description")
    private String description;

    @Column(name = "valuee")
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;

    
    }
}
    
