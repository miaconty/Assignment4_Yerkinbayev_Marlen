package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtils {
    public static void inspectClass(Class<?> clazz) {
        System.out.println("=== Reflection Analysis: " + clazz.getSimpleName() + " ===");

        System.out.println("-> Fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("   " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("-> Methods (First 5):");
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods)
                .limit(5)
                .forEach(m -> System.out.println("   " + m.getName()));

        System.out.println("=============================================");
    }
}