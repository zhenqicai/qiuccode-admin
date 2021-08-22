package cn.qiucode.cms.event;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.SetUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;


import java.util.Set;

/**
 * @program: cms
 * @description:
 * @author: 上官江北
 * @create: 2021-08-21 17:16
 */
public class UserAuthenticationUpdatedEvent  extends ApplicationEvent {

    private Set<Long> userIds = Sets.newCopyOnWriteArraySet();

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

    public UserAuthenticationUpdatedEvent(@NonNull Object source) {
        super(source);
    }

    public void cleanSet(Set<Long> toClean) {
        userIds = SetUtils.disjunction(userIds, toClean);
    }

}
