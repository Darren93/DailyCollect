package com.darren.spring.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE })
public @interface XMLMapping {
	 /**
     * xml节点对于映射名称
     * @return
     */
    String value();
}
