package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatingObject {
    Scanner scanner = new Scanner(System.in);

    //CREATION METHODS
    public Map<Integer, Lead> creatingLead(Map<Integer,Lead> leadMap){
        Lead lead = new Lead(writeName(), writePhoneNumber(), writeEmail(), writeCompanyName());
        leadMap.put(lead.getId(), lead);
        System.out.println("New LEAD created with id " + lead.getId());
        return leadMap;
    }

    public Contact creatingContact(Lead lead){
        Contact contact = new Contact(lead.getName(), lead.getPhoneNumber(),
                lead.getEmail(), lead.getCompanyName());
        System.out.println("New CONTACT created with id " + contact.getId());
        return contact;
    }

    public List<Contact> creatingContactList(Contact contact, List<Contact> contactList){
        contactList.clear();
        contactList.add(contact);
        return contactList;
    }

    public Map<Integer, Contact> creatingContactMap(Contact contact, Map<Integer, Contact> contactMap) {
        contactMap.put(contact.getId(), contact);
        return contactMap;
    }

    //READ INPUT METHODS
    public String writeName(){
        System.out.println("Please write the first name and last name");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        name = name.trim();
        while (!objectName(name)){
            System.out.println("Please insert a correct name");
            name = scanner.nextLine();
        }
        return name;
    }

    public String writePhoneNumber(){
        System.out.println("Please write the phone number (format +34 654321987)");
        scanner = new Scanner(System.in);
        String phoneNumber = scanner.nextLine();
        phoneNumber = phoneNumber.trim();
        while (!objectPhoneNumber(phoneNumber)){
            System.out.println("Please insert a correct phone number");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    public String writeEmail(){
        System.out.println("Please write your email");
        scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        email = email.trim();
        while (!objectEmail(email)) {
            System.out.println("Please insert a correct email");
            email = scanner.nextLine();
        }
        return email;
    }

    public String writeCompanyName(){
        System.out.println("Please write your company name");
        scanner = new Scanner(System.in);
        String companyName = scanner.nextLine();
        companyName = companyName.trim();
        while(!objectCompanyName(companyName)){
            System.out.println("Please write a correct company name");
            companyName = scanner.nextLine();
        }
        return companyName;
    }

    //VALIDATE INPUT METHODS
    public Boolean objectName(String name){
        name = name.trim();
        String[] arrayName = name.split(" ");
        if(!name.matches("[a-zA-Z ]+") || arrayName.length < 2 ){
            return false;
        }
        return true;
    }

    public Boolean objectPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.trim();
        final String regex = "^\\+\\d{1,3}\s\\d{6,12}"; // Has a + at the beginning, followed by 2-3 numbers (prefix),
        // a blanc space and 6-12 numbers (number)
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
       if (!matcher.matches()){
           return false;
        }
        return true;
    }

    public Boolean objectEmail(String email){
        email = email.trim();
        if (email.contains("@") && email.contains(".") && !email.contains(" ")) {
            String[] email1 = email.split("@");
            if (email1[1].contains(".")) {
                return true;
            }
        }
        return false;
    }

    public Boolean objectCompanyName(String companyName){
        companyName = companyName.trim();
        if(companyName.isEmpty()){
            return false;
        }
        return true;
    }
}
