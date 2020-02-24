package org.launchcode.construction.controllers;

import org.launchcode.construction.data.ItemRepository;
import org.launchcode.construction.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity getItems(@RequestParam Optional<Double> price, @RequestParam Optional<Boolean> newItem) {
        if(price.isPresent() && !newItem.isPresent()){
            if(itemRepository.findByPrice(price.get()) == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(itemRepository.findByPrice(price.get()), HttpStatus.OK);
        }
        else if(!price.isPresent() && newItem.isPresent()) {
            if(itemRepository.findByNewItem(newItem.get()) == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(itemRepository.findByNewItem(newItem.get()), HttpStatus.OK);
        }
        else if(price.isPresent() && newItem.isPresent()) {
            if(itemRepository.findByPriceAndNewItem(price.get(), newItem.get()) == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(itemRepository.findByPriceAndNewItem(price.get(), newItem.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(itemRepository.findAll(), HttpStatus.OK);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getItemById(@PathVariable("id") int id) {
        if(itemRepository.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(itemRepository.findById(id), HttpStatus.OK);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item postItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity putItem(@PathVariable("id") int id, @RequestBody Item item) {
        if(itemRepository.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(itemRepository.save(item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteItem(@PathVariable("id") int id) {
        Optional<Item> item = itemRepository.findById(id);
        if(!item.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            itemRepository.delete(item.get());
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
