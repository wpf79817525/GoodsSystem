package com.enity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


@Data
@TableName("item_order")
public class ItemOrder {
    @TableId
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    private String customerPhone;
    private Item item;
    private Long buyNum;
    @TableField(exist = false)
    @JsonSerialize(using= ToStringSerializer.class)
    private MyDate createTime;
}
