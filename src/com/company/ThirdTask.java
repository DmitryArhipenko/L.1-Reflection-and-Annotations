package com.company;

import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;

public class ThirdTask {

    static String path = ("G:\\MyKievProgRepository\\L.1 Reflection and Annotations\\Person.bin");
    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException {

        Person person = new Person("Dmitry","Arhipenko");
        Class<?> cls = Person.class;

        Field [] fields = cls.getDeclaredFields();

        String serializedString = new String();
        System.out.println("Serialization data:");
        for(Field field : fields)
        {
            if(field.isAnnotationPresent(Save.class))
                serializedString += (field.get(person).toString());
            serializedString += (",");
        }

        System.out.println(serializedString);
        Serialize(serializedString);

        String deserializedString = DeSerialize();
        String stringArray [] = deserializedString.split(",");
        System.out.println("Deserialized strings");
        System.out.println(Arrays.toString(stringArray));

    }

    public static void Serialize(String s) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(s);
        objectOutputStream.close();
    }

    public static String DeSerialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String string = (String) objectInputStream.readObject();
        return string;
    }
}

final class Person implements Serializable {

    @Save
    String name;

    @Save
    String surname;

    public Person(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

}