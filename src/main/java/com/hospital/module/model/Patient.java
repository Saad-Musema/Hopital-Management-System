package com.hospital.module.model;

import java.util.Date;


public class Patient extends Person {
    private int PatientID;
    private Date admissionDate;
    private Date dischargeDate;

    // Constructors
    public Patient() {
        super();
    }

    public Patient(String name, int age, String gender, String phoneNumber, String address ) {
        super(name, age, gender, phoneNumber, address);
    }



    // Getters and Setters
    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Patient{" +
                "Patient Id" + getId() + '\'' +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", Phone Number='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", admissionDate=" + admissionDate +
                ", dischargeDate=" + dischargeDate +
                '}';
    }
}

