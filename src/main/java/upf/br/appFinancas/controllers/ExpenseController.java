package upf.br.appFinancas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import upf.br.appFinancas.domain.model.Expense;
import upf.br.appFinancas.domain.repositories.ExpenseRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/expense")
public class ExpenseController {
    
    @Autowired
    private ExpenseRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Expense> list() {
        return repository.findAll();
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<?> findById(@PathVariable Long expenseId) {
        Optional<Expense> expenseOptional = repository.findById(expenseId);

        if (expenseOptional.isPresent()) {
            return ResponseEntity.ok(expenseOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Expense createAccount(@RequestBody Expense expense) {
        return repository.save(expense);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<?> update(@PathVariable Long expenseId, @RequestBody Expense expense) {
        Optional<Expense> expenseOptional = repository.findById(expenseId);

        if(expenseOptional.isPresent()){
            Expense expenseCurrent = expenseOptional.get();

            BeanUtils.copyProperties(expense, expenseCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(expenseCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> delete(@PathVariable Long expenseId) {
        repository.deleteById(expenseId);
        return ResponseEntity.noContent().build();
    }
}
