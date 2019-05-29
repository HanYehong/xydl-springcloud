package com.njit.xydl.life.common.task;

import com.alibaba.fastjson.JSON;
import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.express.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/28 17:34
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private ExpressMapper expressMapper;

    /**
     * 一分钟执行一次
     */
    @Scheduled(fixedRate = 1000 * 60)
    public void reportCurrentTime(){
        checkDeadlineOrderTask();
    }

    private void checkDeadlineOrderTask() {
        System.out.println("----开始检查过期订单----");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -4);
        String beginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("周期：" + beginTime + " ~ " + endTime);
        List<Express> timeoutOrderList =
                expressMapper.selectTimeOutOrderBySugmentTime(beginTime, endTime);
        if (CollectionUtils.isEmpty(timeoutOrderList)) {
            System.out.println("sys：不存在过期订单");
            return;
        }
        System.out.println("sys：有部分过期订单，即将进行清理……" + JSON.toJSONString(timeoutOrderList));
        expressMapper.updateTimeOutOrderStatus(timeoutOrderList);
        System.out.println("sys：清理完成");
    }

}
