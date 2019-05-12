package com.dlizarra.starter.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;


    @Autowired
    public ItemService(@Qualifier("itemRepository") ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item){
        item.setCreationTime(LocalDateTime.now());
        if (item.getItemname() == null || item.getPrice() == null || item.getUsername() == null){
            throw new InvalidItemInputException("Please provide a valid Input!");
        }

        itemRepository.save(item);
    }

    public Iterable<Item> getItems(){
        return this.itemRepository.findAll();
    }

    public void deleteItem(Integer itemId){
        Item item = itemRepository.findItemById(itemId);
        if (item == null){
            throw new ItemNotFoundException("Item was not found in the Repository");
        }
        itemRepository.delete(item);
    }
}
