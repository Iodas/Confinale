package com.dlizarra.starter.item;

import com.dlizarra.starter.support.AbstractEndpointTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

public class ItemControllerTest extends AbstractEndpointTest {
    @Qualifier("itemRepository")
    @Autowired
    private ItemRepository itemRepository;



    @Override
    @Before
    public void setUp(){
        super.setUp();
        Item item = new Item();
        item.setUsername("Michael");
        item.setItemname("Banane");
        item.setPrice(5.9f);
        itemRepository.save(item);

        Item item1 = new Item();
        item1.setUsername("Michael1");
        item1.setItemname("Banane1");
        item1.setPrice(6.9f);
        itemRepository.save(item1);
    }


    @After
    public void tearDown() throws Exception{
        itemRepository.deleteAll();
    }


    @Test
    public void addItemTest() throws Exception{
        tearDown();
        String uri = "/items/add";

        Item item = new Item();
        item.setUsername("Michael");
        item.setItemname("Banane");
        item.setPrice(5.9f);

        String inputJson = super.mapToJson(item);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue(status == 200);

        //unable to use method findItemById because id is not generated yet so we use this method even though the same person might buy the same item for the same price twice which would cause an error
        Item createdItem = itemRepository.findItemByItemnameAndUsernameAndPrice(item.getItemname(), item.getUsername(), item.getPrice());
        Assert.assertNotNull(createdItem);
    }

    @Test
    public void getItemsTest() throws Exception{
        String uri = "/items";


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Item[] items = super.mapFromJson(content, Item[].class);
        Assert.assertTrue(items.length > 1);
    }

    @Test
    public void deleteItemTest() throws Exception{
        Integer item1Id = itemRepository.findItemByItemnameAndUsernameAndPrice("Banane1", "Michael1", 6.9f).getId();

        String uri = "/items/" + item1Id + "/delete";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        Optional<Item> optionalItem = Optional.ofNullable(itemRepository.findItemById(item1Id));
        Assert.assertTrue(!optionalItem.isPresent());
    }

    @Test
    public void calculateSumTest() throws Exception{
        String uri = "/items/sum";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        ItemSum itemSum = super.mapFromJson(content, ItemSum.class);
        Assert.assertTrue(itemSum.getSum() == 12.8f);

    }
}
