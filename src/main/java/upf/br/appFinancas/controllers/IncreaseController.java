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

import upf.br.appFinancas.domain.model.Increase;
import upf.br.appFinancas.domain.repositories.IncreaseRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/increase")
public class IncreaseController {
    
    @Autowired
    private IncreaseRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Increase> list() {
        return repository.findAll();
    }

    @GetMapping("/{increaseId}")
    public ResponseEntity<?> findById(@PathVariable Long increaseId) {
        Optional<Increase> increaseOptional = repository.findById(increaseId);

        if (increaseOptional.isPresent()) {
            return ResponseEntity.ok(increaseOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Increase createAccount(@RequestBody Increase increase) {
        return repository.save(increase);
    }

    @PutMapping("/{increase_countId}")
    public ResponseEntity<?> update(@PathVariable Long increaseId, @RequestBody Increase increase) {
        Optional<Increase> increaseOptional = repository.findById(increaseId);

        if(increaseOptional.isPresent()){
            Increase increaseCurrent = increaseOptional.get();

            BeanUtils.copyProperties(increase, increaseCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(increaseCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{increase_countId}")
    public ResponseEntity<?> delete(@PathVariable Long increase) {
        repository.deleteById(increase);
        return ResponseEntity.noContent().build();
    }
}
