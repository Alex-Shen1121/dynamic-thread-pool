package top.codingshen.conifg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "thread.pool.executor.config", ignoreInvalidFields = true)
public class ThreadPoolConfigProperties {

    /** 核心线程数 */
    private Integer corePoolSize = 20;
    /** 最大线程数 */
    private Integer maxPoolSize = 200;
    /** 最大等待时间 */
    private Long keepAliveTime = 10L;
    /** 最大队列数 */
    private Integer blockQueueSize = 5000;
    /**
     * AbortPolicy：丢弃任务并抛出RejectedExecutionException异常。<br/>
     * DiscardPolicy：直接丢弃任务，但是不会抛出异常<br/>
     * DiscardOldestPolicy：将最早进入队列的任务删除，之后再尝试加入队列的任务被拒绝<br/>
     * CallerRunsPolicy：如果任务添加线程池失败，那么主线程自己执行该任务<br/>
     */
    private String policy = "AbortPolicy";

}
