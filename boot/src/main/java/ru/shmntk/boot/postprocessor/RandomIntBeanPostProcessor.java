package ru.shmntk.boot.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.shmntk.boot.util.annotations.RandomInt;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class RandomIntBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(RandomInt.class)) {
                declaredField.setAccessible(true);
                ReflectionUtils.setField(declaredField, bean, new Random().nextInt(50));
            }
        }
        return bean;
    }
}
