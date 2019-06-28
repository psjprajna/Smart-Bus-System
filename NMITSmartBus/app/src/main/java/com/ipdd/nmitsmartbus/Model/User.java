package com.ipdd.nmitsmartbus.Model;

public class User {
    private String Name;
    private String Password;
    private String Regno;
    private String Phone;
    private String Salary;
    private String Designation;
    private String Stop;


    public User() {
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getRegno() {
        return Regno;
    }

    public void setRegno(String regno) {
        Regno = regno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }
    public String getStop()
    {
        return Stop;
    }
    public void setStop(String stop)
    {
        Stop = stop;
    }
}
