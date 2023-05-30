package upf.br.appFinancas.domain.repositories;

import upf.br.appFinancas.domain.model.Invest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestRepository
        extends JpaRepository<Invest, Long> {
}


