package upf.br.appFinancas.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upf.br.appFinancas.domain.model.Category;
import upf.br.appFinancas.domain.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findById(@PathVariable Long categoryId) {
        Optional<Category> categoryOptional = repository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            return ResponseEntity.ok(categoryOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category add(@RequestBody Category category) {
        return repository.save(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(
            @PathVariable Long categoryId,
            @RequestBody Category category) {
        Optional<Category> categoryOptional = repository.findById(categoryId);

        if (categoryOptional.isPresent()){
            Category categoryCurrent = categoryOptional.get();

            BeanUtils.copyProperties(category, categoryCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(categoryCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Long categoryId) {
        repository.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }

}


