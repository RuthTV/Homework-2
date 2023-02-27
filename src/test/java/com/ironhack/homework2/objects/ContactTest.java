package com.ironhack.homework2.objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
        Contact contact1,contact2,contact3;
    @BeforeEach
    void setUp() {
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contact3 = new Contact("Anastasia Garcia","+34 456735",
                "anastigarcA.aLMeid@accenture.com","Accenture");

    }
    @AfterEach
    void tearDown() {
        contact1.setContactIdCounter(100000); //reseteamos el id_counter para que no nos afecte en los test
    }

    @Test
    void testToString_insertContact_returnStringContact() {
        assertEquals("=== Contact 100000 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
                "- email: alex.bermejo@gmail.com\n- company name: Accenture\n",contact1.toString());
        assertEquals("=== Contact 100001 ===\n- name: Alejandro Hernandez\n- phone number: +4 652899076\n" +
                "- email: alejandrito.divertido@hotmail.com\n- company name: Accenture\n",contact2.toString());
        assertEquals("=== Contact 100002 ===\n- name: Anastasia Garcia\n- phone number: +34 456735\n" +
                "- email: anastigarcA.aLMeid@accenture.com\n- company name: Accenture\n",contact3.toString());
    }

    @Test
    void printDecisionMaker_differentContacts_returnStringContact() {
        assertEquals(" CONTACT 100000\n   - name: Alex Bermejo\n   - phone number: +34 652899076\n" +
                "   - email: alex.bermejo@gmail.com\n   - company name: Accenture\n",contact1.printDecisionMaker());
        assertEquals(" CONTACT 100001\n   - name: Alejandro Hernandez\n   - phone number: +4 652899076\n" +
                "   - email: alejandrito.divertido@hotmail.com\n   - company name: Accenture\n",
                contact2.printDecisionMaker());
        assertEquals(" CONTACT 100002\n   - name: Anastasia Garcia\n   - phone number: +34 456735\n" +
                "   - email: anastigarcA.aLMeid@accenture.com\n   - company name: Accenture\n",
                contact3.printDecisionMaker());
    }

    @Test
    void showContact_differentContact_returnIdName() {
        assertEquals("id: 100000 -> name: Alex Bermejo",contact1.showContact());
        assertEquals("id: 100001 -> name: Alejandro Hernandez",contact2.showContact());
        assertEquals("id: 100002 -> name: Anastasia Garcia",contact3.showContact());
    }


}