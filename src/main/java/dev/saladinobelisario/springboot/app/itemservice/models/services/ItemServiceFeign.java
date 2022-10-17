package dev.saladinobelisario.springboot.app.itemservice.models.services;

import dev.saladinobelisario.springboot.app.itemservice.clients.ProductClientRest;
import dev.saladinobelisario.springboot.app.itemservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductClientRest clientFeign;

    @Override
    public List<Item> findAll() {
        return clientFeign.listProducts().stream()
                .map(product -> new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findByID(Long id, Integer quantity) {
        return new Item(clientFeign.detail(id), quantity);
    }
}
