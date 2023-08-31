package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enity.ItemStock;
import com.mapper.ItemMapper;
import com.enity.Item;
import com.mapper.ItemStockMapper;
import com.service.ItemService;
import com.service.ItemStockService;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemStockService itemStockService;
    @Override
    public Result getWithPage(Item item,int currentPage, int pageSize) {
        Page<Item> page = new Page<>(currentPage,pageSize);
        itemMapper.getPage(page,item);
        if (currentPage > page.getPages())
        {
            page.setCurrent(page.getPages());
            itemMapper.getPage(page,item);
        }
        Result result = new Result();
        result.setData(page);
        result.setFlag(true);
        return result;
    }

    @Override
    public Result modifyWithItem(Item item) {
        boolean success = updateById(item);
        Result result = new Result();
        result.setFlag(success);
        result.setMessage(success ? "修改成功...":"修改失败...");
        return result;
    }

    // 此处应该对Item和ItemStock同时操作(事务)
    @Override
    @Transactional
    public Result deleteById(long id) {
        boolean successItem = removeById(id);
        boolean successStock = itemStockService.removeById(id);
        boolean success = successItem && successStock;
        Result result = new Result();
        result.setFlag(success);
        result.setMessage(success ? "删除成功...":"删除失败...");
        return result;
    }

    // 此处应该对Item和ItemStock同时操作(事务)
    @Override
    @Transactional
    public Result insertItem(Item item) {
        boolean successItem = save(item);
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(item.getId());
        itemStock.setStock(item.getStock());
        boolean successStock = itemStockService.save(itemStock);
        boolean success = successItem && successStock;
        Result result = new Result();
        result.setFlag(success);
        result.setMessage(success ? "添加成功...":"添加失败...");
        return result;
    }

    @Override
    public Result selectById(long id) {
        Item item = itemMapper.getById(id);
        Result result = new Result();
        result.setFlag(item != null);
        result.setData(item);
        return result;
    }
}
