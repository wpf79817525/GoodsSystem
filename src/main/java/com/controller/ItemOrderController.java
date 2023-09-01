package com.controller;

import com.enity.Item;
import com.enity.ItemOrder;
import com.service.ItemOrderService;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class ItemOrderController {
    @Autowired
    private ItemOrderService itemOrderService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") long id){
        return itemOrderService.selectById(id);
    }

    @PostMapping
    public Result insert(@RequestBody ItemOrder itemOrder){
        return itemOrderService.createItemOrder(itemOrder);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") long id){
        return itemOrderService.deleteById(id);
    }

    @PutMapping
    public Result update(@RequestBody ItemOrder itemOrder){
        return itemOrderService.modifyWithItem(itemOrder);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getAll(ItemOrder itemOrder,@PathVariable("currentPage") int currentPage,@PathVariable("pageSize") int pageSize){
        Item item = itemOrder.getItem();
        itemOrder.setItem(item == null ? new Item():item);
        Result result = itemOrderService.getWithPage(itemOrder,currentPage,pageSize);
        return result;
    }
}
