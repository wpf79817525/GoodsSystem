package com;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enity.Item;
import com.enity.ItemOrder;
import com.enity.ItemStock;
import com.mapper.ItemMapper;
import com.mapper.ItemStockMapper;
import com.service.ItemOrderService;
import com.service.ItemService;
import com.service.ItemStockService;
import com.utils.MysqlIdWorker;
import com.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SpringBootProjectApplicationTests {
    @Test
    void contextLoads(@Autowired ItemService itemService) {
        Item item = new Item();
        item.setName("milk");
        item.setPrice(5.00);
        item.setBrand("麦趣尔");
        item.setSpec("10cm*10cm");
        item.setImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F4025638d-e461-4fa0-a392-e6999fd9296a%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1695818656&t=50c7aeb01f9b8af9d8b18a0cf45f54bf");
        itemService.save(item);
    }

    @Test
    void contextLoads2(@Autowired ItemService itemService) {
        itemService.removeById(1);
    }

    @Test
    void contextLoads3(@Autowired ItemStockMapper itemStockMapper) {
        long stock = itemStockMapper.getStockById(2L);
        System.out.println(stock);
    }

    @Test
    void contextLoads4(@Autowired ItemMapper itemMapper) {
        Page<Item> page = new Page<>(1,1);
        Item item = new Item();
        item.setName("yy");
        itemMapper.getPage(page,item);
        List<Item> records = page.getRecords();
        for (Item record : records) {
            System.out.println(record);
        }
        System.out.println(records);
    }

    @Test
    void contextLoads5(@Autowired ItemService itemService) {
        Result re = itemService.getWithPage(new Item(), 1, 1);
        Page<Item> data = (Page<Item>)re.getData();
        List<Item> records = data.getRecords();
        System.out.println(records);
    }

    @Test
    void contextLoads6(@Autowired ItemStockService itemStockService) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(2L);
        itemStock.setStock(99L);
//        itemStockService.updateById(itemStock);
        List<ItemStock> list = itemStockService.list();
        System.out.println(list);
    }
    @Test
    void contextLoads7(@Autowired ItemStockMapper itemStockMapper) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(2L);
        itemStock.setStock(99L);
        int i = itemStockMapper.updateById(itemStock);
        System.out.println(i);
    }

    @Test
    void contextLoads8(@Autowired ItemStockMapper itemStockMapper) {
        long stockById = itemStockMapper.getStockById(2L);
        System.out.println(stockById);
    }

    @Test
    void contextLoads9(@Autowired ItemService itemService) {
        Item item = new Item();
        item.setId(4L);
        item.setName("yyy");
        boolean b = itemService.updateById(item);
        System.out.println(b);
    }

    @Test
    void contextLoads10(@Autowired MysqlIdWorker idWorker) {
        long nextId = idWorker.nextId();
        System.out.println(nextId);
    }

    @Test
    void contextLoads11(@Autowired ItemOrderService itemOrderService) {
        ItemOrder order = new ItemOrder();
        order.setItem(new Item());
        Result result = itemOrderService.getWithPage(order, 1, 2);
        Page<ItemOrder> page = (Page<ItemOrder>) result.getData();
        for (ItemOrder itemOrder : page.getRecords()) {
            System.out.println(itemOrder);
        }
    }
}
