package cn.qiucode.cms.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @program: cms
 * @description: 事件发布注解
 * @author: 上官江北
 * @create: 2021-08-21 17:09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Publisher {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
