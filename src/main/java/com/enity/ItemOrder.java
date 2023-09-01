package com.enity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("item_order")
public class ItemOrder {
    @TableId
    private Long orderId;
    private String customerPhone;
    private Item item;
    private Long buyNum;
    @TableField(exist = false)
    private Date createTime;
}
