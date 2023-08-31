package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enity.ItemStock;

public interface ItemStockMapper extends BaseMapper<ItemStock> {
    long getStockById(Long id);
}
