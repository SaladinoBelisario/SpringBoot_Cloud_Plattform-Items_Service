package dev.saladinobelisario.springboot.app.itemservice.models.services;

import dev.saladinobelisario.springboot.app.itemservice.models.Item;
import dev.saladinobelisario.springboot.app.itemservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate restClient;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(
                restClient.getForObject("http://products-service/listar", Product[].class));

        return products.stream()
                .map(product -> new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findByID(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = restClient.getForObject("http://products-service/producto/{id}", Product.class, pathVariables);

        return new Item(product, quantity);
    }
}
