package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Industry;
import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatingAccountTest {
    CreatingAccount creatingAccount;
    List<Contact> contactList = new ArrayList<>();
    Account account;
    Account account1;
    Industry industry1;
    Contact contact1,contact2,contact3;
    List<Opportunity> opportunityList;
    Opportunity opportunity1,opportunity2,opportunity3;
    List<Contact> contactList1;
    List<Opportunity> opportunityList1;

    @BeforeEach
    void setUp() {
        creatingAccount = new CreatingAccount();
        industry1 = Industry.ECOMMERCE;
        contact1 = new Contact("Alex Bermejo","+34 652899076","alex.bermejo@gmail.com",
                "Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contact3 = new Contact("Anastasia Garcia","+34 456735",
                "anastigarcA.aLMeid@accenture.com","Accenture");
        contactList1 = List.of(contact1,contact2,contact3);
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,0, contact2);
        opportunity3 = new Opportunity(Product.HYBRID,-34, contact3);
        opportunityList1 = List.of(opportunity1,opportunity2,opportunity3);
        account = new Account(industry1,456,"Barcelona","España",
                contactList,opportunityList);
        account1 = new Account(industry1,456,"Barcelona","España",
                contactList1,opportunityList1);
    }

    @AfterEach
    void tearDown() {
        account1.setAccountIdCounter(300000); //reseteamos el id_counter para que no nos afecte en los test
        opportunity1.setOpportunityIdCounter(200000);
        contact1.setContactIdCounter(100000);
    }

    @Test
    void selectIndustryCondition_False_BadStament() {
        assertFalse(creatingAccount.selectIndustryCondition("String product"));
        assertFalse(creatingAccount.selectIndustryCondition("Hel1lo"));
        assertFalse(creatingAccount.selectIndustryCondition("1246%"));
    }

    @Test
    void selectIndustryCondition_True_GoodStament() {
        assertTrue(creatingAccount.selectIndustryCondition("ProducE"));
        assertTrue(creatingAccount.selectIndustryCondition(" eCoMMerce"));
        assertTrue(creatingAccount.selectIndustryCondition("MANUFACTURING"));
        assertTrue(creatingAccount.selectIndustryCondition("Medical"));
        assertTrue(creatingAccount.selectIndustryCondition("other"));
    }

    @Test
    void employeeCountCondition_False_BadStament() {
        assertFalse(creatingAccount.employeeCountCondition("String product"));
        assertFalse(creatingAccount.employeeCountCondition("-12"));
        assertFalse(creatingAccount.employeeCountCondition("!1246%"));
    }

    @Test
    void employeeCountCondition_True_GoodStament() {
        assertTrue(creatingAccount.employeeCountCondition(" 4"));
        assertTrue(creatingAccount.employeeCountCondition("1234 "));
    }

    @Test
    void writeCityCondition_False_BadStament() {
        assertFalse(creatingAccount.writeCityCondition("String1product"));
        assertFalse(creatingAccount.writeCityCondition("!Hola%"));
    }

    @Test
    void writeCityyCondition_True_GoodStament() {
        assertTrue(creatingAccount.writeCityCondition("Micronizados Naturales"));
        assertTrue(creatingAccount.writeCityCondition(" LacKar"));
    }

    @Test
    void writeCountryCondition_False_BadStament() {
        assertFalse(creatingAccount.writeCountryCondition("String1product"));
        assertFalse(creatingAccount.writeCountryCondition("!Hola%"));
    }

    @Test
    void writeCountryCondition_True_GoodStament() {
        assertTrue(creatingAccount.writeCountryCondition(" Micronizados Naturales"));
        assertTrue(creatingAccount.writeCountryCondition("LacKar"));

    }
}
