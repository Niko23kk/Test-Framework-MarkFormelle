package service;

import model.User;

public class UserCreator {

    public static final String USER_NAME="test.data.user.username";
    public static final String USER_SURNAME="test.data.user.surname";
    public static final String USER_PASSWORD="test.data.user.userpassword";
    public static final String USER_PHONE_NUMBER="test.data.user.userphonenumber";
    public static final String USER_EMAIL="test.data.user.useremail";

    public static User withAllProperty(){
        return new User(TestDataReader.getTestData(USER_NAME),
                TestDataReader.getTestData(USER_SURNAME),
                TestDataReader.getTestData(USER_PASSWORD),
                TestDataReader.getTestData(USER_PHONE_NUMBER),
                TestDataReader.getTestData(USER_EMAIL));
    }

    public static User withEmailAndPsssword(){
        return new User(TestDataReader.getTestData(USER_EMAIL),
        TestDataReader.getTestData(USER_PASSWORD));
    }
}
