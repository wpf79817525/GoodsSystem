package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enity.Item;
import com.utils.Result;

public interface ItemService extends IService<Item> {

    Result getWithPage(Item item,int currentPage, int pageSize);

    Result modifyWithItem(Item item);

    Result deleteById(long id);

    Result insertItem(Item item);

    Result selectById(long id);
}
