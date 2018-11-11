package com.huaching.starter.redis.discovery.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author xiajl
 */
@ConditionalOnProperty(value = "spring.cloud.config.discovery.enabled", matchIfMissing = false)
@Configuration
@Import( {
//    RedisRepositoriesAutoConfiguration.class,
    RedisAutoConfiguration.class,
    RedisRegistryAutoConfiguration.class} )
public class RedisDiscoveryClientConfigServiceBootstrapConfiguration {

}
