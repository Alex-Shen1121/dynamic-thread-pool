package top.codingshen.middleware.dynamic.thread.pool.sdk.domain;

import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @ClassName IDynamicThreadPoolService
 * @Description 动态线程池服务
 * @Author alex_shen
 * @Date 2024/8/3 - 20:56
 */
public interface IDynamicThreadPoolService {

    /**
     * 查询线程池列表
     *
     * @return 线程池列表
     */
    List<ThreadPoolConfigEntity> queryThreadPoolList();

    /**
     * 根据线程池名称查询线程池
     *
     * @param threadPoolName 线程池名称
     * @return 线程池
     */
    ThreadPoolConfigEntity queryThreadPoolByName(String threadPoolName);

    /**
     * 更新线程池
     *
     * @param threadPoolConfigEntity 线程池配置实体
     */
    void updateThreadPool(ThreadPoolConfigEntity threadPoolConfigEntity);
}
