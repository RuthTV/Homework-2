package com.ironhack.homework2.objects;

import com.ironhack.homework2.enums.Industry;
import java.util.List;

public class Account {
    //Account Objects
    private int id;
    private static int accountIdCounter = 300000;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Contact> contactList;
    private List<Opportunity> opportunityList;

    //Constructor
    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList,
                   List<Opportunity> opportunityList) {
        this.id = accountIdCounter;
        accountIdCounter++;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    //getters and setters needed
    public Integer getId() {
        return id;
    }

    public static void setAccountIdCounter(int accountIdCounter) {    //this setter is not necessary in the class but we need it to reset the c the tests
        Account.accountIdCounter = accountIdCounter;
    }

    //Strings to Print
    public String showAccount() {
        return ("id: " + id + " -> name of first contact: " + contactList.get(0).getName());
    }

    @Override
    public String toString() {
        return "=== Account " + id + " ===\n"+
                "- industry: " + industry +"\n"+
                "- employee count: " + employeeCount + "\n"+
                "- city: " + city + "\n"+
                "- country: " + country + "\n"+
                "- id's of the contacts: \n" + printIdsContactList(contactList) +
                "- id's of the opportunities: \n" + printIdsOpportunityList(opportunityList);
    }

    public String printIdsContactList(List<Contact> contactList){
        String idsList = "";
        for (Contact contact : contactList){
            idsList += Long.toString(contact.getId()) + "\n";
        }
        return idsList;
    }

    public String printIdsOpportunityList(List<Opportunity> opportunityList){
        String idsList = "";
        for (Opportunity opportunity : opportunityList){
            idsList += Long.toString(opportunity.getId()) + "\n";
        }
        return idsList;
    }
}
