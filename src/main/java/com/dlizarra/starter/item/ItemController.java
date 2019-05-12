package com.dlizarra.starter.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

   private final ItemService itemService;

    ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/items/add")
    void addItem(@RequestBody Item item){
        this.itemService.addItem(item);
    }

    @GetMapping("/items")
    Iterable<Item> all(){
        return itemService.getItems();
    }

    @DeleteMapping("/items/{itemId}/delete")
    void delete(@PathVariable Integer itemId){
        this.itemService.deleteItem(itemId);
    }

    @GetMapping("/items/sum")
    ItemSum calculateSum(){
        return itemService.calculateSum();
    }

    @PutMapping("/items/{itemId}/edit")
    void editItem(@PathVariable Integer itemId, @RequestBody  Item item){
        this.itemService.editItem(itemId, item);
    }

}
