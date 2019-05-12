package com.dlizarra.starter.item;


import com.dlizarra.starter.support.jpa.CustomJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findItemByItemname(String itemname);
    Item findItemById(Integer id);
}
