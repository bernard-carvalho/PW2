package br.edu.ifto.estudante.pw2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifto.estudante.pw2.Entities.Produto;
import br.edu.ifto.estudante.pw2.Repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/show")
    public ModelAndView show(ModelMap model){
        model.addAttribute("produtos",repository.findAll());
        return new ModelAndView("/produtos/show",model);
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("produtos", repository.findAll());
        return new ModelAndView("/produtos/list", model);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        try {
            List<Produto> items = new ArrayList<Produto>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") Long id) {
        Optional<Produto> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ModelAttribute(value = "Produto")
    public ModelAndView create(@Valid Produto produto, BindingResult result, ModelMap model) {
        if(result.hasErrors())
        {
            model.addAttribute("produtos", repository.findAll());
            return new ModelAndView("/produtos/list", model);            
        }   
        repository.save(produto);
        return new ModelAndView("redirect:/produtos/list");
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody Produto item) {
        Optional<Produto> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            Produto existingItem = existingItemOptional.get();
            existingItem.setNome(item.getNome());
            existingItem.setPreco(item.getPreco());
            
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