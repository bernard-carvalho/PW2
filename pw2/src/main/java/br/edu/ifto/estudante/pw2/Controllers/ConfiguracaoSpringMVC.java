package br.edu.ifto.estudante.pw2.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifto.estudante.pw2.Entities.Produto;
import br.edu.ifto.estudante.pw2.Repositories.ProdutoRepository;

@Configuration
@Controller
public class ConfiguracaoSpringMVC implements WebMvcConfigurer {
    
    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        List<Produto> produtos = produtoRepository.findAll();
        /*
         * Verificar uma forma de enviar a /home um list<Produto>.
        */
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
    }
}
