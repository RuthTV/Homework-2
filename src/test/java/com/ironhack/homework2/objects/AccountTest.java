package com.ironhack.homework2.objects;

import com.ironhack.homework2.enums.Industry;
import com.ironhack.homework2.enums.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Industry industry1;
    Contact contact1,contact2,contact3;
    List<Contact> contactList;
    Opportunity opportunity1,opportunity2,opportunity3;
    List<Opportunity> opportunityList;
    Account account1;

    @BeforeEach
    void setUp() {
        industry1 = Industry.ECOMMERCE;
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contact3 = new Contact("Anastasia Garcia","+34 456735",
                "anastigarcA.aLMeid@accenture.com","Accenture");
        contactList = List.of(contact1,contact2,contact3);
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,0, contact2);
        opportunity3 = new Opportunity(Product.HYBRID,-34, contact3);
        opportunityList = List.of(opportunity1,opportunity2,opportunity3);
        account1 = new Account(industry1,456,"Barcelona","España",contactList,opportunityList);

    }

    @AfterEach
    void tearDown() {
        account1.setAccountIdCounter(300000); //reseteamos el id_counter para que no nos afecte en los test
        opportunity1.setOpportunityIdCounter(200000);
        contact1.setContactIdCounter(100000);

    }

    @Test
    void testToString() {
        assertEquals("=== Account 300000 ===\n- industry: ECOMMERCE\n- employee count: 456\n" +
                "- city: Barcelona\n- country: España\n- " +
                "id's of the contacts: \n100000\n100001\n100002\n" +
                "- id's of the opportunities: \n200000\n200001\n200002\n",account1.toString());
    }

    @Test
    void printIdsContactList() {
        assertEquals("100000\n100001\n100002\n",
                account1.printIdsContactList(contactList));
    }

    @Test
    void printIdsOpportunityList() {
        assertEquals("200000\n200001\n200002\n",account1.printIdsOpportunityList(opportunityList));
    }

    @Test
    void showAccount() {
        assertEquals("id: 300000 -> name of first contact: Alex Bermejo",
                account1.showAccount());
    }


}