package cn.qiucode.cms.event;

import cn.qiucode.cms.annotation.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * @program: cms
 * @description: 用户权限更新事件
 * @author: 上官江北
 * @create: 2021-08-21 17:11
 */
@Publisher
public class UserAuthenticationUpdatedEventPublisher implements ApplicationEventPublisherAware, ApplicationContextAware {

    private final Logger log = LoggerFactory.getLogger(UserAuthenticationUpdatedEventPublisher.class);

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }

    public void publishEvent(Set<Long> userId) {
        UserAuthenticationUpdatedEvent event = new UserAuthenticationUpdatedEvent(applicationContext);
        event.setUserIds(userId);
        applicationEventPublisher.publishEvent(event);
        log.info("broadcast UserAuthenticationUpdatedEvent");
    }
}
