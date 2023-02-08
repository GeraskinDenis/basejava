package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Создаём объект
        Resume resume = new Resume("UUID_1");

        // Способ №1: Получаем массив методов в котором перебором можно найти нужный нам метод по имени и вызвать его
        System.out.println("\nThe way #1");
        Method[] methods = resume.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(resume));
            }
        }

        // Способ №2: Получаем метод по имени
        System.out.println("\nThe way #2");
        Method toString = resume.getClass().getDeclaredMethod("toString");
        System.out.println(toString.invoke(resume));
    }
}
