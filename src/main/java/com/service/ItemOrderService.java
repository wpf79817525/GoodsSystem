package com.service;

import com.enity.ItemOrder;
import com.utils.Result;

public interface ItemOrderService {
    Result saveItemOrderWithTransactional(ItemOrder itemOrder);
    Result createItemOrder(ItemOrder itemOrder);
}
