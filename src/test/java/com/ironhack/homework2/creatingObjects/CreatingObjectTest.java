package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreatingObjectTest {
    private Lead lead;
    private Contact contact;
    private CreatingObject creatingObject;
    List<Contact> contactList = new ArrayList<>();
    Map<Integer, Contact> contactMap = new HashMap<>();
    Map<Integer, Lead> leadMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        creatingObject = new CreatingObject();
        lead = new Lead("Alex Bermejo","+34 652899076","alex.bermejo@gmail.com",
                "Accenture");
        contact = new Contact("Alex Bermejo","+34 652899076","alex.bermejo@gmail.com",
                "Accenture");
    }

    @AfterEach
    void tearDown() {
        lead.setLeadIdCounter(1); //reseteamos el id_counter para que no nos afecte en los test
        contact.setContactIdCounter(100000);
    }

    @Test
    void objectName_first_second() {
        assertEquals(true,creatingObject.objectName("Alex Bermejo"));
        assertEquals(true,creatingObject.objectName("Alex Bermejo Garcia"));
    }

    @Test
    void objectName_not_first_second() {
     assertEquals(false,creatingObject.objectName("AlexBermejo"));
    }

    @Test
    void objectPhoneNumber_valid_format() {
        assertEquals(true,creatingObject.objectPhoneNumber("+34 652899076"));
    }

    @Test
    void objectPhoneNumber_invalid_format() {
        assertEquals(false,creatingObject.objectPhoneNumber("1987"));
    }

    @Test
    void objectEmail_valid_format() {
        assertEquals(true,creatingObject.objectEmail("alex.bermejo@gmail.com"));
    }

    @Test
    void objectEmail_invalid_format() {
        assertEquals(false,creatingObject.objectEmail("alex.bermejogmail.com"));
        assertEquals(false,creatingObject.objectEmail("alexbermejo@gmailcom"));
        assertEquals(false,creatingObject.objectEmail("alex.be rmejo@gmail.com"));
    }

    @Test
    void objectCompanyName_valid_format() {
        assertEquals(true,creatingObject.objectCompanyName("Accenture"));
    }
    @Test
    void objectCompanyName_invalid_format() {
        assertEquals(false,creatingObject.objectCompanyName(""));
    }

    @Test
    void creatingContact(){
        Contact resultContact = creatingObject.creatingContact(lead);
        assertEquals("Alex Bermejo", resultContact.getName());
        assertEquals(100001, resultContact.getId());
        assertEquals("+34 652899076", resultContact.getPhoneNumber());
    }

    @Test
    void creatingContactList() {
        assertTrue(creatingObject.creatingContactList(contact,contactList).contains(contact));
    }

    @Test
    void creatingContactMap() {
        assertTrue(creatingObject.creatingContactMap(contact,contactMap).containsKey(contact.getId()));
    }
}
