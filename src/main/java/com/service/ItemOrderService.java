package com.service;

import com.enity.Item;
import com.enity.ItemOrder;
import com.utils.Result;

public interface ItemOrderService {
    Result saveItemOrderWithTransactional(ItemOrder itemOrder);
    Result createItemOrder(ItemOrder itemOrder);
    Result getWithPage(ItemOrder itemOrder, int currentPage, int pageSize);

    Result modifyWithItem(ItemOrder itemOrder);

    Result deleteById(long id);

    Result selectById(long id);
}
