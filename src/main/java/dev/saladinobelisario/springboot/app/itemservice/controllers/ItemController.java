package dev.saladinobelisario.springboot.app.itemservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dev.saladinobelisario.springboot.app.itemservice.models.Item;
import dev.saladinobelisario.springboot.app.itemservice.models.Product;
import dev.saladinobelisario.springboot.app.itemservice.models.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceRestTemplate")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> listItems(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @GetMapping("/item/{id}/cantidad/{quantity}")
    public Item itemDetail(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findByID(id, quantity);
    }

    public Item fallbackMethod(Long id, Integer quantity){
        Item item = new Item();
        Product product = new Product();

        product.setId(id);
        product.setName("Dummy product");
        product.setPrice(666.00);

        item.setQuantity(quantity);
        item.setProduct(product);

        return item;
    }
}
