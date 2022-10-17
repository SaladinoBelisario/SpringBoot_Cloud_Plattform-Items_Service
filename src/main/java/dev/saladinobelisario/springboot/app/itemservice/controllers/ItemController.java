package dev.saladinobelisario.springboot.app.itemservice.controllers;

import dev.saladinobelisario.springboot.app.itemservice.models.Item;
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

    @GetMapping("/item/{id}/cantidad/{quantity}")
    public Item itemDetail(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findByID(id, quantity);
    }
}
