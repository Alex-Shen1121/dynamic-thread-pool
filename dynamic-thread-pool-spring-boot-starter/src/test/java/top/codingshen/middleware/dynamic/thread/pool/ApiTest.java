package top.codingshen.middleware.dynamic.thread.pool;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.DynamicThreadPoolService;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @ClassName ApiTest
 * @Description 单元测试
 * @Author alex_shen
 * @Date 2024/8/3 - 17:46
 */
@SpringBootTest
public class ApiTest {

    private DynamicThreadPoolService dynamicThreadPoolService;

    @Test
    public void queryThreadPoolListTest() {
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        System.out.println(JSON.toJSONString(threadPoolConfigEntities));
    }
}
