package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class SingleInstance {
    private static final Logger logger = LoggerFactory.getLogger(SingleInstance.class);
    private final Map<Class<?>, Object> instances = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T InstanceStatic(Class<T> t) {
        T target = null;
        try {
            var constructor = t.getDeclaredConstructor();
            constructor.setAccessible(true);
            target = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.toString());
        }
        return target;
    }

    public <T> T Instance(Class<T> t) {
        T target = (T) this.instances.get(t);
        if (target == null) {
            target = SingleInstance.InstanceStatic(t);
            this.instances.put(t, target);
        }
        return target;
    }
}
