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

import upf.br.appFinancas.domain.model.Invest;
import upf.br.appFinancas.domain.repositories.InvestRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/invest")
public class InvestController {
    
    @Autowired
    private InvestRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Invest> list() {
        return repository.findAll();
    }

    @GetMapping("/{investId}")
    public ResponseEntity<?> findById(@PathVariable Long investId) {
        Optional<Invest> investOptional = repository.findById(investId);

        if (investOptional.isPresent()) {
            return ResponseEntity.ok(investOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invest createAccount(@RequestBody Invest invest) {
        return repository.save(invest);
    }

    @PutMapping("/{invest_countId}")
    public ResponseEntity<?> update(@PathVariable Long investId, @RequestBody Invest invest) {
        Optional<Invest> investOptional = repository.findById(investId);

        if(investOptional.isPresent()){
            Invest investCurrent = investOptional.get();

            BeanUtils.copyProperties(invest, investCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(investCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{invest_countId}")
    public ResponseEntity<?> delete(@PathVariable Long invest) {
        repository.deleteById(invest);
        return ResponseEntity.noContent().build();
    }
}
