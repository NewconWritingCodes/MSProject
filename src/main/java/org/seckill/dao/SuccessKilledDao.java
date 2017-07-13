package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by liukang on 2017/7/13.
 */

/*
为什么这么设计

 */
public interface SuccessKilledDao {

    int insertSuccessKilled(long seckillId,long userPhone);


    SuccessKilled queryByIdWithSeckill(long seckillId);



}
