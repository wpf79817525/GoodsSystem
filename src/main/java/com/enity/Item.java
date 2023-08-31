package com.enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Item {
    @TableId(type=IdType.AUTO)
    private Long id;
    private String name;
    private String image;
    private Double price;
    private String brand;
    private String spec;
    @TableField(exist = false)
    private Long stock;
    @TableField(exist = false)
    private LocalDateTime createTime;
    @TableField(exist = false)
    private LocalDateTime updateTime;
}
