package org.example;

public class User {
    public String firstName;
    public User(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "User{" + "firstName='" + firstName + '\'' + '}';
    }
}
