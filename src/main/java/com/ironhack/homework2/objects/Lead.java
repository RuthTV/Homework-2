package com.ironhack.homework2.objects;

public class Lead {
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    protected int id;
    protected static int leadIdCounter = 1;

    public Lead(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.id = leadIdCounter;
        leadIdCounter++;
    }

    //getters and setters needed
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setLeadIdCounter(int leadIdCounter) {    //es necesario para resetear el counter en los test
        Lead.leadIdCounter = leadIdCounter;
    }

    //Strings to Print
    public String showLead(){
        return ("id: " + id + " -> name: " + name);
    }

    @Override
    public String toString() {
        return "=== Lead " + id + " ===\n"+
                "- name: " + name + "\n"+
                "- phone number: " + phoneNumber + "\n"+
                "- email: " + email + "\n"+
                "- company name: " + companyName + "\n";
    }
}
