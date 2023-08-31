package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enity.ItemOrder;
import com.enity.ItemStock;
import com.mapper.ItemOrderMapper;
import com.service.ItemOrderService;
import com.service.ItemStockService;
import com.utils.MysqlIdWorker;
import com.utils.Result;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemOrderServiceImpl extends ServiceImpl<ItemOrderMapper, ItemOrder> implements ItemOrderService {
    @Autowired
    private ItemStockService itemStockService;
    @Autowired
    private MysqlIdWorker idWorker;
    @Override
    public Result createItemOrder(ItemOrder itemOrder) {
        // 1. 需要判断订单对应库存是否充足，充足则进行操作，否则不进行
        // 这里存在线程安全问题，不过反正基本是单线程，为了避免错误解决一下
        // 思路：使用sychronized
        Long itemId = itemOrder.getItemId();
        synchronized (itemId.toString().intern()){
            QueryWrapper<ItemStock> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("item_id",itemId).select("stock");
            Long leftStock = itemStockService.getOne(queryWrapper).getStock();
            Long needStock = itemOrder.getItemStock();
            // 订单需要的库存量太多
            if (needStock > leftStock)
            {
                Result result = new Result();
                result.setFlag(false);
                result.setMessage("库存不足，当前订单需求数量 > 库存数量，订单创建失败，请重试...");
                return result;
            }
            // 处理订单
            // 添加订单行，修改库存(利用事务控制)
            ItemOrderService proxy = (ItemOrderService) AopContext.currentProxy();
            return proxy.saveItemOrderWithTransactional(itemOrder);
        }
    }

    @Override
    @Transactional
    public Result saveItemOrderWithTransactional(ItemOrder itemOrder) {
        // 生成订单id
        Long itemOrderId = idWorker.nextId();
//        int a = 1 / 0;
        itemOrder.setOrderId(itemOrderId);
        Result result = new Result();
        boolean successSave = save(itemOrder);
        boolean successReduce = itemStockService.update().setSql("stock = stock - " + itemOrder.getItemStock()).eq("item_id",itemOrder.getItemId()).update();
        boolean success = successSave && successReduce;
        result.setFlag(success);
        if (!success)
        {
            // TODO 这里其实可以修改异常处理器进行控制
            result.setMessage("订单创建异常...，订单保存状态：" + successSave + "，库存扣减状态" + successReduce);
            System.out.println(result.getMessage());
            throw new RuntimeException();
        }
        result.setData(itemOrder.getOrderId());
        result.setMessage("订单创建成功...");
        return result;
    }
}
