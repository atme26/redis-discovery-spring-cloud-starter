package com.huaching.starter.redis.discovery.client;

import com.huaching.starter.redis.discovery.registry.RedisRegistration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

public class RedisDiscoveryClient implements DiscoveryClient {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String description() {
        return "基于redis的注册中心，并提供服务列表";
    }

    @Override
    public ServiceInstance getLocalServiceInstance() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {

        return redisTemplate.opsForList().range(serviceId, 0, -1).
                parallelStream().map((Function<String, ServiceInstance>) s -> {
            RedisRegistration redisRegistration = new RedisRegistration();
            redisRegistration.setApplicationName(serviceId);
            String hostName = StringUtils.split(s, ":")[0];
            String port = StringUtils.split(s, ":")[1];
            redisRegistration.setHost(hostName);
            redisRegistration.setPort(Integer.parseInt(port));
            //redisRegistration
            return redisRegistration;
        }).collect(Collectors.toList());


    }

    @Override
    public List<String> getServices() {
        List<String> list = new ArrayList<>();
        list.addAll(redisTemplate.keys("*"));
        return list;
    }
}