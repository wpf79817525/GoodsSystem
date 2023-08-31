package com.controller;

import com.enity.Item;
import com.service.ItemService;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") long id){
        return itemService.selectById(id);
    }

    @PostMapping
    public Result insert(@RequestBody Item item){
        return itemService.insertItem(item);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") long id){
        return itemService.deleteById(id);
    }

    @PutMapping
    public Result update(@RequestBody Item item){
        return itemService.modifyWithItem(item);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getAll(Item item,@PathVariable("currentPage") int currentPage,@PathVariable("pageSize") int pageSize){
        Result result = itemService.getWithPage(item,currentPage,pageSize);
        return result;
    }

//    @GetMapping
//    public Result getAll(){
//        List<Item> itemList = itemService.list();
//        Result result = new Result();
//        result.setData(itemList);
//        result.setFlag(true);
//        return result;
//    }
}
