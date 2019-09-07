package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class SecondTask {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException
    {
        Class<?> cls = TextContainer.class;
        if(cls.isAnnotationPresent(SaveTo.class))
        {
            SaveTo saveTo = cls.getAnnotation(SaveTo.class);
            Method [] methods = cls.getDeclaredMethods();

            for(Method m : methods)
                if(m.isAnnotationPresent(Saver.class))
                    m.invoke(new TextContainer(),saveTo.path());
        }
    }
}

@SaveTo(path = "G:\\MyKievProgRepository\\L.1 Reflection and Annotations\\file.txt")
class TextContainer {
    String text ="qwerty";

    @Saver
    public void save(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
}