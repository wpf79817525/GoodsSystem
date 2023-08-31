package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enity.Item;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper extends BaseMapper<Item> {
    IPage<Item> getPage(Page<Item> iPage,@Param("it") Item item);

    Item getById(long id);
}
