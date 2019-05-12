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

public class ItemControllerTest extends AbstractEndpointTest {
    @Qualifier("itemRepository")
    @Autowired
    private ItemRepository itemRepository;



    @Override
    @Before
    public void setUp(){
        super.setUp();
    }


    @After
    public void tearDown() throws Exception{
        itemRepository.deleteAll();
    }


    @Test
    public void addItemTest() throws Exception{

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

        Item createdItem = itemRepository.findItemById(1);
        Assert.assertNotNull(createdItem);
    }

    @Test
    public void getItemsTest() throws Exception{
        String uri = "/items";

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

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Item[] items = super.mapFromJson(content, Item[].class);
        Assert.assertTrue(items.length > 1);
    }
}
