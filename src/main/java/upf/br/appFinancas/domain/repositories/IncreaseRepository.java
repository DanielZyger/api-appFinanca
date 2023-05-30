package upf.br.appFinancas.domain.repositories;

import upf.br.appFinancas.domain.model.Increase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncreaseRepository
        extends JpaRepository<Increase, Long> {
}

