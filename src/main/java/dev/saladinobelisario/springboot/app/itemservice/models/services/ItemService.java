package dev.saladinobelisario.springboot.app.itemservice.models.services;

import dev.saladinobelisario.springboot.app.itemservice.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findByID(Long id, Integer quantity);
}
