package br.edu.ifto.estudante.pw2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifto.estudante.pw2.Entities.ClientePF;
import br.edu.ifto.estudante.pw2.Repositories.ClientePFRepository;

@RestController
@RequestMapping("/clientes/pf")
class ClientesPFController {

    @Autowired
    ClientePFRepository repository;

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("clientespf",repository.findAll());
        return new ModelAndView("/clientes/pf/list",model);
    }

    @GetMapping
    public ResponseEntity<List<ClientePF>> getAll() {
        try {
            List<ClientePF> items = new ArrayList<ClientePF>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientePF> getById(@PathVariable("id") Long id) {
        Optional<ClientePF> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    @PostMapping
    public ResponseEntity<ClientePF> create(@RequestBody ClientePF item) {
        try {
            ClientePF savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
 */  
    @PostMapping
    @ModelAttribute(value = "ClientePF")
    public ModelAndView create(ClientePF clientepf) {
        repository.save(clientepf);
        return new ModelAndView("redirect:/clientes/pf/list");
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientePF> update(@PathVariable("id") Long id, @RequestBody ClientePF item) {
        Optional<ClientePF> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            ClientePF existingItem = existingItemOptional.get();
            existingItem.setEmail(item.getEmail());
            existingItem.setCpf(item.getCpf());
            //existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}