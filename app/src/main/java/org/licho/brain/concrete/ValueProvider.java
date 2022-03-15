package org.licho.brain.concrete;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public class ValueProvider {
    private static Object instance;

    private static class Container<E> {
        public E create(Class<E> clazz) throws NoSuchMethodException, InvocationTargetException,
                InstantiationException,
                IllegalAccessException {
            return clazz.getDeclaredConstructor().newInstance();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T Instance(Class<T> clazz) {
        if (instance == null) {
            try {
                instance = new Container<T>().create(clazz);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) instance;
    }
}
