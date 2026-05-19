package org.example.model;

public class Client {
    private Integer id;
    private String name;
    private String email;
    private int age;

    public Client() {
    }

    public Client(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
