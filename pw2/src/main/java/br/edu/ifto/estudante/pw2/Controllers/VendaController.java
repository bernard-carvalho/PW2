package br.edu.ifto.estudante.pw2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifto.estudante.pw2.Entities.Venda;
import br.edu.ifto.estudante.pw2.Repositories.ProdutoRepository;
import br.edu.ifto.estudante.pw2.Repositories.VendaRepository;

@Transactional
@RestController
@RequestMapping("vendas")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    Venda carrinho;

    @GetMapping("/{id}/detalhes")
    public ModelAndView detalhar(ModelMap model, @PathVariable("id") Long id){
            model.addAttribute("venda", repository.findById(id).get());
        return new ModelAndView("/vendas/details",model);
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
            model.addAttribute("vendas", repository.findAll());
        return new ModelAndView("/vendas/list", model);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getAll() {
        try {
            List<Venda> items = new ArrayList<Venda>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Venda> getById(@PathVariable("id") Long id) {
        Optional<Venda> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody Venda item) {
        try {
            Venda savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Venda> update(@PathVariable("id") Long id, @RequestBody Venda item) {
        Optional<Venda> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            Venda existingItem = existingItemOptional.get();
            System.out.println("TODO for developer - update logic is unique to entity and must be implemented manually.");
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
