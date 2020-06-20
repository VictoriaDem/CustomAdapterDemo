package com.example.customadapterdemo;
import androidx.annotation.NonNull;

public class User {
    String name, phoneNumber;
    Sex sex;

    public User(String name, String phoneNumber, Sex sex) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return phoneNumber;
    }

    public Sex getSex() {
        return sex;
    }

    @NonNull
    @Override
    public String toString() {
        return "Пользователь {\n" +
                "Имя: " + this.name + "\n" +
                "Номер телефона: " + this.phoneNumber + "\n" +
                "Пол: " + this.sex + "\n" + "}\n";
    }
}

