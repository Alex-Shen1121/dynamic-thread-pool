package top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ThreadPoolConfigEntity
 * @Description 线程池配置实体对象
 * @Author alex_shen
 * @Date 2024/8/3 - 20:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreadPoolConfigEntity {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 线程池名称
     */
    private String threadPoolName;

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maximumPoolSize;

    /**
     * 当前活跃线程数
     */
    private int activeCount;

    /**
     * 当前池中线程数
     */
    private int poolSize;

    /**
     * 队列类型
     */
    private String queueType;

    /**
     * 当前队列任务数
     */
    private int queueSize;

    /**
     * 队列剩余任务数
     */
    private int remainingCapacity;

}
