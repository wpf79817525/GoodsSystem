package com.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.enity.ItemOrderIdMaker;
import com.mapper.ItemOrderIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class MysqlIdWorker {
    //2023-01-01 00:00:00 的时间戳
    private final long BEGIN_SECONDS = 1672531200L;
    @Autowired
    private ItemOrderIdMapper itemOrderIdMapper;

    public long nextId() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取当前时间的时间戳
        long nowTimeStamp = now.toEpochSecond(ZoneOffset.UTC);
        // 计算ID构成中的时间戳
        long timeStamp = nowTimeStamp - BEGIN_SECONDS;
        // 将当前时间按指定格式转换为字符串形式
        int year = now.getYear();
        int month = now.getMonth().getValue();
        int day = now.getDayOfMonth();
        // 获取id后32位的部分
//        long id = stringRedisTemplate.opsForValue().increment("irc:" + keyPreFix + ":" + date);
        QueryWrapper<ItemOrderIdMaker> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year",year).eq("month",month).eq("day",day);
        ItemOrderIdMaker idMaker = itemOrderIdMapper.selectOne(queryWrapper);
        if (idMaker == null)
        {
            idMaker = new ItemOrderIdMaker();
            idMaker.setYear(year);
            idMaker.setMonth(month);
            idMaker.setDay(day);
            idMaker.setCount(0L);
            itemOrderIdMapper.insert(idMaker);
        }
        long id = idMaker.getCount();
        idMaker.setCount(id + 1);
        itemOrderIdMapper.update(idMaker,queryWrapper);
        // 将时间戳和id进行拼接
        long real_id = (timeStamp << 32) | id;
        return real_id;
    }
}
