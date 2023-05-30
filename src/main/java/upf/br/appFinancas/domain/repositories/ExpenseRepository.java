package upf.br.appFinancas.domain.repositories;

import upf.br.appFinancas.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {
}

