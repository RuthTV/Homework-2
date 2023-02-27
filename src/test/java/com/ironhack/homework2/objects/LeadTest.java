package com.ironhack.homework2.objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadTest {

    Lead lead1,lead2,lead3;

    @BeforeEach
    void setUp() {
        lead1 = new Lead("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        lead1.setId(1);
        lead2 = new Lead("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        lead2.setId(2);
        lead3 = new Lead("Anastasia Garcia","+34 456735",
                "anastigarcA.aLMeid@accenture.com","Accenture");
        lead3.setId(3);
    }

    @AfterEach
    void tearDown() {
        lead1.setLeadIdCounter(1); //reseteamos el id_counter para que no nos afecte en los test
    }
    @Test
    void showLead_differentContact_returnLead() {
        assertEquals("id: 1 -> name: Alex Bermejo",lead1.showLead());
        assertEquals("id: 2 -> name: Alejandro Hernandez",lead2.showLead());
        assertEquals("id: 3 -> name: Anastasia Garcia",lead3.showLead());
    }

    @Test
    void testToString_differentContacts_returnStringLead() {
        assertEquals("=== Lead 1 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
                "- email: alex.bermejo@gmail.com\n- company name: Accenture\n",lead1.toString());
        assertEquals("=== Lead 2 ===\n- name: Alejandro Hernandez\n- phone number: +4 652899076\n" +
                "- email: alejandrito.divertido@hotmail.com\n- company name: Accenture\n",lead2.toString());
        assertEquals("=== Lead 3 ===\n- name: Anastasia Garcia\n- phone number: +34 456735\n" +
                "- email: anastigarcA.aLMeid@accenture.com\n- company name: Accenture\n",lead3.toString());
    }
}