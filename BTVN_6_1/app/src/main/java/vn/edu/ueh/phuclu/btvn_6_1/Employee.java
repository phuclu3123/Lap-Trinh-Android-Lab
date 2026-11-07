package vn.edu.ueh.phuclu.btvn_6_1;

public class Employee {
    private final int id;
    private String name;
    private double cSal;
    private int age;
    private String gender;
    private String hobby;

    public Employee(int id, String name, double cSal, int age, String gender, String hobby) {
        this.id = id;
        this.name = name;
        this.cSal = cSal;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCSal() {
        return cSal;
    }
    public void setCSal(double cSal) {
        this.cSal = cSal;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}