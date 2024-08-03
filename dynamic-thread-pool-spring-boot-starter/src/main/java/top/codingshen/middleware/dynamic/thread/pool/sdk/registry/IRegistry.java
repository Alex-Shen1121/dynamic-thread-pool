package top.codingshen.middleware.dynamic.thread.pool.sdk.registry;

import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @ClassName IRegistry
 * @Description 注册中心接口
 * @Author alex_shen
 * @Date 2024/8/3 - 23:51
 */
public interface IRegistry {

    /**
     * 上报线程池
     * @param threadPoolConfigEntities 线程池配置
     */
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities);

    /**
     * 上报线程池参数
     * @param threadPoolConfigEntity 线程池配置
     */
    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
