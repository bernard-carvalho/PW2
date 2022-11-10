package br.edu.ifto.estudante.pw2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.edu.ifto.estudante.pw2.Entities.Cliente;
import br.edu.ifto.estudante.pw2.Entities.ClientePF;
import br.edu.ifto.estudante.pw2.Entities.ItemVenda;
import br.edu.ifto.estudante.pw2.Entities.Produto;
import br.edu.ifto.estudante.pw2.Entities.Venda;
import br.edu.ifto.estudante.pw2.Repositories.ClientePFRepository;
import br.edu.ifto.estudante.pw2.Repositories.ClienteRepository;
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
    ClienteRepository clienteRepository;

    @Autowired
    ClientePFRepository clientePFRepository;

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

    @PostMapping("/carrinho/add/{qntdProduto}")
    public RedirectView addItemNocarrinho(@PathVariable("qntdProduto") Integer qntdProduto, @RequestBody Produto produto){
        boolean found = false;
        for(int i=0;i<carrinho.getItensVenda().size();i++){
            if(carrinho.getItensVenda().get(i).getProduto().getId()==produto.getId())
            {
                found=true;
                if(qntdProduto!=0)
                carrinho.getItensVenda().get(i).setQuantidade(qntdProduto);
                if(qntdProduto==0)
                    carrinho.getItensVenda().remove(i);
                break;
            }
        }
        if(!found)
        {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(qntdProduto);
            itemVenda.setPrecoUnitario(produto.getPreco());
            carrinho.getItensVenda().add(itemVenda);
        }
        return new RedirectView("vendas/carrinho");
    }
    
    
   


    @GetMapping("/carrinho")
    public ModelAndView listCarrinho(ModelMap model){
        model.addAttribute("carrinho", carrinho);
        model.addAttribute("clientes",clienteRepository.findAll());
        return new ModelAndView("/carrinho/list", model);
    }

    @GetMapping("/carrinho/apagar")
    public RedirectView resetar(HttpSession sessao){
        sessao.invalidate();
        return new RedirectView("/");
    }

    @PostMapping("/carrinho/finalizar")
    public RedirectView finalizarVenda(ModelMap model, @RequestBody ClientePF cliente, BindingResult result,HttpSession sessao, RedirectAttributes atributos){
        ClientePF cliente2 = clientePFRepository.findById(cliente.getId()).get();
        

        List<String> erros =  new ArrayList<>();
        if(carrinho.getItensVenda().size()==0){
            System.out.println("\nENTROU AQUI\n");
            erros.add("Venda vazia não será realizada");
            System.out.println(erros.size());
        }
        if(result.hasErrors()){
            for(int i=0;i<result.getErrorCount();i++){
                System.out.println("encontrado erro no campo: "+result.getFieldErrors().get(i).getField());
                if(!"CPF".equals(result.getFieldErrors().get(i).getField()))
                    erros.add(result.getAllErrors().get(i).getDefaultMessage().toString());
            }
        }
        if(erros.size()!=0){
            System.out.println("\n\nTAMBEM ENTROU AQUI");
            System.out.println(erros.size());
            System.out.println(erros);
            atributos.addFlashAttribute("erros",erros);
            return new RedirectView("/vendas/carrinho");
        }
        carrinho.setCliente(cliente2);
        repository.save(carrinho);
        sessao.invalidate();
        return new RedirectView("/");
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
    public ResponseEntity<Venda> create(@RequestBody @Valid Venda item, BindingResult result, ModelMap model) {
        try {
            if(result.hasErrors()){
                model.addAttribute("vendas", repository.findAll());
            }

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
    
    @GetMapping("cliente/{id}")
    public ModelAndView vendasDoCliente(@PathVariable("id") Long idCliente, ModelMap model){
        
        // List<Venda> vendas = repository.findAll();
        // List<Venda> clienteEspecifico = new ArrayList<>();

        // for(int i=0; i<vendas.size();i++){
        //     if(vendas.get(i).getCliente().getId().equals(idCliente))
        //     {
        //         clienteEspecifico.add(vendas.get(i));
        //     }
        // }
        //model.addAttribute("vendas", clienteEspecifico);
        Cliente cliente = clienteRepository.findById(idCliente).get();
        model.addAttribute("vendas", cliente.getVendas());
        return new ModelAndView("/vendas/list",model);
    } /**/
}
