package com.huaching.starter.redis.discovery.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class RedisAutoServiceRegistration extends
    AbstractAutoServiceRegistration<RedisRegistration> {

    @Autowired
    private RedisRegistration redisRegistration;

    public RedisAutoServiceRegistration(ServiceRegistry<RedisRegistration> serviceRegistry, AutoServiceRegistrationProperties properties) {
        super(serviceRegistry);
//         serviceRegistry.register(getRegistration());
    }

    @Override
    protected int getConfiguredPort() {
        return redisRegistration.getPort();
    }

    @Override
    protected void setConfiguredPort(int port) {

    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected RedisRegistration getRegistration() {
        return redisRegistration;
    }

    @Override
    protected RedisRegistration getManagementRegistration() {
        return null;
    }
}