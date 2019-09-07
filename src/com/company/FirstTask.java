package com.company;

import java.lang.reflect.*;

public class FirstTask {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException
    {
        Class<?> cls = Test.class;
        Method [] methods = cls.getDeclaredMethods();

        for(Method method : methods)
            if(method.isAnnotationPresent(MyAnnotations.class))
            {
                MyAnnotations annotation = method.getAnnotation(MyAnnotations.class);
                int result;
                result = (Integer) method.invoke(new Test(),annotation.param1(),annotation.param2());
                System.out.println(result);
            }
    }
}

class Test {

    @MyAnnotations(param1 = 14, param2 = 88)
    public int testMethod (int a , int b)
    {
        return a + b;
    }

}