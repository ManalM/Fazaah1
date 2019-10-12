package com.example.fazaah;

public class Accounts {
    private String mName;
    private String mNumber;
    private String mEmail;
    private int mAge;
    private String mGender;
    private String mHelthStatus;
    private String mTypeOfAccount;


    public Accounts() {
    }

    public Accounts(String name, String number, String email, String gender, String typeOfAccount) {
        mName = name;
        mNumber = number;
        mEmail = email;
        mGender = gender;
        mTypeOfAccount = typeOfAccount;
    }

    public Accounts(String name, String number, String email, int age, String gender, String helthStatus, String typeOfAccount) {
        mName = name;
        mNumber = number;
        mEmail = email;
        mAge = age;
        mGender = gender;
        mHelthStatus = helthStatus;
        mTypeOfAccount = typeOfAccount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getHelthStatus() {
        return mHelthStatus;
    }

    public void setHelthStatus(String helthStatus) {
        mHelthStatus = helthStatus;
    }

    public String getTypeOfAccount() {
        return mTypeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        mTypeOfAccount = typeOfAccount;
    }
}

