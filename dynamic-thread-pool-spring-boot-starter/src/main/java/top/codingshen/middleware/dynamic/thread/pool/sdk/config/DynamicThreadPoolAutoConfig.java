package top.codingshen.middleware.dynamic.thread.pool.sdk.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.codingshen.middleware.dynamic.thread.pool.sdk.domain.DynamicThreadPoolService;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName DynamicThreadPoolAutoConfig
 * @Description 动态配置入口
 * @Author alex_shen
 * @Date 2024/8/3 - 17:44
 */
@Configuration
public class DynamicThreadPoolAutoConfig {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);

    @Bean("dynamicThreadPollService")
    public DynamicThreadPoolService dynamicThreadPollService(ApplicationContext applicationContext, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {

        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");

        if (StringUtils.isBlank(applicationName)) {
            applicationName = "dynamic-thread-pool-test-default";
            logger.warn("动态线程池, 启动提示, SpringBoot 应用未配置 spring.application.name 无法获取到应用名称");
        }

        return new DynamicThreadPoolService(applicationName, threadPoolExecutorMap);
    }

}
