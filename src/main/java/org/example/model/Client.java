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
}
