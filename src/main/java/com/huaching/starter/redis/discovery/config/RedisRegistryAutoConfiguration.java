package com.huaching.starter.redis.discovery.config;

import com.huaching.starter.redis.discovery.client.RedisDiscoveryClient;
import com.huaching.starter.redis.discovery.registry.RedisAutoServiceRegistration;
import com.huaching.starter.redis.discovery.registry.RedisRegistration;
import com.huaching.starter.redis.discovery.registry.RedisServiceRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClientAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiajl
 */
@Configuration
//@ConditionalOnBean(annotation = EnableRedisDiscoveryClient.class)
@ConditionalOnProperty(value = "spring.redis.registry.enabled", matchIfMissing = true)
@AutoConfigureBefore({NoopDiscoveryClientAutoConfiguration.class,
    CommonsClientAutoConfiguration.class, ServiceRegistryAutoConfiguration.class })
public class RedisRegistryAutoConfiguration {

    @Bean
    RedisServiceRegistry redisServiceRegistry() {
        return new RedisServiceRegistry();
    }

    @Bean
    RedisAutoServiceRegistration redisAutoServiceRegistration(RedisServiceRegistry redisServiceRegistry) {
        return new RedisAutoServiceRegistration(redisServiceRegistry, new AutoServiceRegistrationProperties());
    }

    /*@Bean
    RedisTemplate setRedisTemplate(){
      RedisSerializer stringSerializer = new StringRedisSerializer();
      redisTemplate.setHashValueSerializer(stringSerializer);
      return redis
    }*/

    @Bean
    RedisRegistration redisRegistration(){
        return new RedisRegistration();
    }


    @Bean
    RedisDiscoveryClient redisDiscoveryClient() {
        return new RedisDiscoveryClient();
    }
}