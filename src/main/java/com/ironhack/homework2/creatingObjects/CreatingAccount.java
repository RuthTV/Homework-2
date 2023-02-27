package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import com.ironhack.homework2.enums.Industry;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CreatingAccount {
    Scanner scanner = new Scanner(System.in);

    //CREATION METHODS
    public Map<Integer, Account> creatingAccount(List<Contact> contactList,
                                                 List<Opportunity> opportunityList, Map<Integer,
                                                 Account> accountMap){
        Account account = new Account(selectIndustry(), employeeCount(), writeCity(),
                writeCounty(), contactList, opportunityList);
        accountMap.put(account.getId(), account);
        System.out.println("New ACCOUNT created with id " + account.getId());
        return accountMap;
    }

    //READ INPUT METHODS
    public Industry selectIndustry() throws IllegalArgumentException {
        System.out.println("Please write the industry of the company: PRODUCE, " +
                "ECOMMERCE, MANUFACTURING, MEDICAL, OTHER");
        scanner = new Scanner(System.in);
        String industry = scanner.next();
        industry = industry.trim().toUpperCase();
        while (!selectIndustryCondition(industry)){
            System.out.println("Please write a correct product type");
            industry = scanner.nextLine();
            industry = industry.trim().toUpperCase();
        }
        return Industry.valueOf(industry);
    }

    public int employeeCount() {
        System.out.println("Please write the number of employees in the company ");
        scanner = new Scanner(System.in);
        String employeeCount = scanner.nextLine();
        employeeCount = employeeCount.trim();
        while (!employeeCountCondition(employeeCount)) {
            System.out.println("Please insert a correct number of employees");
            employeeCount = scanner.nextLine();
        }
        return Integer.parseInt(employeeCount);
    }

    public String writeCity(){
        System.out.println("Please write the City of the company");
        scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();
        cityName = cityName.trim();
        while (!writeCityCondition(cityName)){
            System.out.println("Please insert a correct city");
            cityName = scanner.nextLine();
        }
        return cityName;
    }

    public String writeCounty(){
        System.out.println("Please write the Country of the company");
        scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();
        countryName = countryName.trim();
        while (!writeCountryCondition(countryName)){
            System.out.println("Please insert a correct country");
            countryName = scanner.nextLine();
        }
        return countryName;
    }

    //VALIDATE INPUT METHODS
    public boolean selectIndustryCondition(String industry){
        industry = industry.trim().toUpperCase();
        if(!industry.equals("PRODUCE") && !industry.equals("ECOMMERCE") && !industry.equals("MANUFACTURING")
                && !industry.equals("MEDICAL") && !industry.equals("OTHER")){
            return false;
        }
        return true;
    }

    public boolean employeeCountCondition(String employeeCount){
        employeeCount = employeeCount.trim();
        if(!employeeCount.matches("\\d+")){
            return false;
        }
        return true;
    }

    public boolean writeCityCondition(String cityName){
        cityName = cityName.trim();
        if(!cityName.matches("[a-zA-Z ]+")){
            return false;
        }
        return true;
    }

    public boolean writeCountryCondition(String countryName){
        countryName = countryName.trim();
        if(!countryName.matches("[a-zA-Z ]+")){
            return false;
        }
        return true;
    }

}
