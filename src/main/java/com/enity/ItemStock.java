package com.enity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("item_stock")
public class ItemStock {
    @TableField("item_id")
    @TableId
    Long itemId;
    Long stock;
}
