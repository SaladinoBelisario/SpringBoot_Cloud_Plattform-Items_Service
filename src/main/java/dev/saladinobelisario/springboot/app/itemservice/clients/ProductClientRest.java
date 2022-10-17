package dev.saladinobelisario.springboot.app.itemservice.clients;

import dev.saladinobelisario.springboot.app.itemservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductClientRest {

    @GetMapping("/listar")
    public List<Product> listProducts();

    @GetMapping("/producto/{id}")
    public Product detail(@PathVariable Long id);
}
