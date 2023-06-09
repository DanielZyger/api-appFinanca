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

import upf.br.appFinancas.domain.model.User;
import upf.br.appFinancas.domain.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> list() {
        return repository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId) {
        Optional<User> userOptional = repository.findById(userId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> userOptional = repository.findById(userId);

        if(userOptional.isPresent()){
            User userCurrent = userOptional.get();

            BeanUtils.copyProperties(user, userCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(userCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        repository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


}
