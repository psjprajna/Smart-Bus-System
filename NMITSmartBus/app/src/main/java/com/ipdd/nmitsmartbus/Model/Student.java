package com.ipdd.nmitsmartbus.Model;

public class Student {

    private String Name,Regno,Stop,Phone;

    public Student() {
    }

    public Student(String name, String regno, String stop, String phone) {
        Name = name;
        Regno = regno;
        Stop = stop;
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegno() {
        return Regno;
    }

    public void setRegno(String regno) {
        Regno = regno;
    }

    public String getStop() {
        return Stop;
    }

    public void setStop(String stop) {
        Stop = stop;
    }
}
