package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enity.ItemOrder;
import org.apache.ibatis.annotations.Param;

public interface ItemOrderMapper extends BaseMapper<ItemOrder> {
    Page<ItemOrder> getPage(Page<ItemOrder> page,@Param("io") ItemOrder itemOrder);

    int save(ItemOrder itemOrder);

    ItemOrder getById(long id);
}
