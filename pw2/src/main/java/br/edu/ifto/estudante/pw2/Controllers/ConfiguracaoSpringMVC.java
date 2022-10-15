package br.edu.ifto.estudante.pw2.Controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifto.estudante.pw2.Entities.Venda;
import br.edu.ifto.estudante.pw2.Repositories.VendaRepository;

@Configuration
public class ConfiguracaoSpringMVC implements WebMvcConfigurer {
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index"); 
    }
}
