package com.enity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("item_order")
public class ItemOrder {
    @TableId
    private Long orderId;
    private String userPhone;
    private Long itemId;
    private Long itemStock;
    @TableField(exist = false)
    private LocalDateTime createTime;
}
