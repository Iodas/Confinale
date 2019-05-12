package com.dlizarra.starter.item;

import com.dlizarra.starter.user.User;
import com.dlizarra.starter.user.UserDto;
import com.dlizarra.starter.user.UserRepository;
import com.dlizarra.starter.user.UserService;
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

    //private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public ItemService(@Qualifier("itemRepository") ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    //It says it needs a return type?! Thats where I stopped because I didn't have any more proper time to look over the already provided user classes wit hall those security and role stuff
    /*
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }*/

    public void addItem(Item item){
        item.setCreationTime(LocalDateTime.now());
        if (item.getItemname() == null || item.getPrice() == null || item.getUsername() == null){
            throw new InvalidItemInputException("Please provide a valid Input!");
        }
        //couldn't initialize userrepository above..
        //wanted to add a new user when there hasnt been a user with such username yet and always add the shopping price to the user attribute
        /*
        if (userRepository.findByUsername(item.getUsername()) == null){
            UserDto newUser = new UserDto();
            newUser.setShoppingSum(item.getPrice());
            newUser.setUsername(item.getUsername());
            userService.createUser(newUser);
        }
        */
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

    public ItemSum calculateSum(){
        Iterable<Item> items = itemRepository.findAll();
        ItemSum itemSum = new ItemSum();
        itemSum.setSum(0f);
        items.forEach(item -> itemSum.setSum(itemSum.getSum() + item.getPrice()));
        return itemSum;
    }

    public void editItem(Integer itemId, Item item){
        Item oldItem = itemRepository.findItemById(itemId);
        //overrides the old item because the changed item still has the same id
        oldItem.setUsername(item.getUsername());
        oldItem.setPrice(item.getPrice());
        oldItem.setItemname(item.getItemname());
        itemRepository.save(oldItem);
    }
}
