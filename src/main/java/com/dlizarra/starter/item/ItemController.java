package com.dlizarra.starter.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
