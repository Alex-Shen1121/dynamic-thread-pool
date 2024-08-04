package top.codingshen.middleware.dynamic.thread.pool.sdk.trigger.job;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import top.codingshen.middleware.dynamic.thread.pool.sdk.registry.IRegistry;

import java.util.List;

/**
 * @ClassName ThreadPoolDataReportJob
 * @Description 线程池数据上报任务
 * @Author alex_shen
 * @Date 2024/8/4 - 00:17
 */
@Slf4j
public class ThreadPoolDataReportJob {

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolDataReportJob(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void execReportThreadPoolList() {
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPool(threadPoolConfigEntities);
        log.info("动态线程池, 上报线程池信息:{}", JSON.toJSONString(threadPoolConfigEntities));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            log.info("动态线程池, 上报线程池配置:{}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }

}
