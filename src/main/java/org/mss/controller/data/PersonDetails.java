package org.mss.controller.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class PersonDetails {

    private final int personId;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty phoneNumber;

    public PersonDetails(Integer personId, String name, String email, String phoneNumber) {
        this.personId = personId;
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
