package com.ironhack.homework2.objects;

public class Contact extends Lead {
    private static int contactIdCounter = 100000;

    public Contact(String name, String phoneNumber, String email, String companyName) {
        super(name, phoneNumber, email, companyName);
        leadIdCounter--;
        this.id = contactIdCounter;
        contactIdCounter++;
    }

    //getters and setters needed
    public static void setContactIdCounter(int contactIdCounter) { //es necesario para resetear el counter en los test
        Contact.contactIdCounter = contactIdCounter;
    }

    //Strings to Print
    public String showContact() {
        return ("id: " + id + " -> name: " + getName());
    }

    @Override
    public String toString() {
        return "=== Contact " + id + " ===\n"+
                "- name: " + getName() + "\n"+
                "- phone number: " + getPhoneNumber() + "\n"+
                "- email: " + getEmail() + "\n"+
                "- company name: " + getCompanyName() + "\n";
    }

    public String printDecisionMaker() {
        return  " CONTACT " + id + "\n"+
                "   - name: " + getName() + "\n"+
                "   - phone number: " + getPhoneNumber() + "\n"+
                "   - email: " + getEmail() + "\n"+
                "   - company name: " + getCompanyName() + "\n";
    }
}