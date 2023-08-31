package com.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("itemorder_id_maker")
public class ItemOrderIdMaker {
    private Integer year;
    private Integer month;
    private Integer day;
    private Long count;
}
