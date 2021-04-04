package ru.netology;

public class User {
    private String name;
    private String sex;
    private int age;
    private String parol;

    public User() {
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public User(String name, String sex, int age, String parol) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.parol = parol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("[User: %s, sex: %s, age: %d]",name,sex,age);
    }
}
