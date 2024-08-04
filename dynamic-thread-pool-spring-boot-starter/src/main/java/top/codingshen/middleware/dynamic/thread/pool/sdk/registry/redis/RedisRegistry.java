package top.codingshen.middleware.dynamic.thread.pool.sdk.registry.redis;

import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.valobj.RegistryEnumVO;
import top.codingshen.middleware.dynamic.thread.pool.sdk.registry.IRegistry;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName RedisRegistry
 * @Description Redis 注册中心
 * @Author alex_shen
 * @Date 2024/8/3 - 23:52
 */
public class RedisRegistry implements IRegistry {

    private final RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    /**
     * 上报线程池
     *
     * @param threadPoolConfigEntities 线程池配置
     */
    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities) {
        RList<ThreadPoolConfigEntity> list = redissonClient.getList(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEY.getKey());

        Map<String, List<String>> redisThreadPoolMap = list.stream()
                .collect(
                        Collectors.groupingBy(
                                ThreadPoolConfigEntity::getAppName,
                                Collectors.mapping(ThreadPoolConfigEntity::getThreadPoolName, Collectors.toList())));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
            if (redisThreadPoolMap.containsKey(threadPoolConfigEntity.getAppName()) && redisThreadPoolMap.get(threadPoolConfigEntity.getAppName()).contains(threadPoolConfigEntity.getThreadPoolName())) {
                ThreadPoolConfigEntity redisEntity = list.stream().filter(t -> Objects.equals(t.getAppName(), threadPoolConfigEntity.getAppName()) && Objects.equals(t.getThreadPoolName(), threadPoolConfigEntity.getThreadPoolName())).findFirst().orElse(null);
                if (!redisEntity.equals(threadPoolConfigEntity)) {
                    list.remove(redisEntity);
                    list.add(threadPoolConfigEntity);
                }
            }
        }
    }

    /**
     * 上报线程池参数
     *
     * @param threadPoolConfigEntity 线程池配置
     */
    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey() + ":" + threadPoolConfigEntity.getAppName() + ":" + threadPoolConfigEntity.getThreadPoolName();
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(threadPoolConfigEntity, Duration.ofDays(30));
    }
}
