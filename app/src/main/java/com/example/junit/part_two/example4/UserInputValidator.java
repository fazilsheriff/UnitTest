package com.example.junit.part_two.example4;

public class UserInputValidator {

    public boolean isValidFullName(String fullName) {
        return FullNameValidator.isValidFullName(fullName);
    }

    public boolean isValidUsername(String username) {
        return ServerUsernameValidator.isValidUsername(username);
    }
}
