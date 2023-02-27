package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Command;
import com.ironhack.homework2.enums.Industry;
import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;
import com.ironhack.homework2.objects.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CRMCommandMenuTest {
    CRMMenu crmCommand;
    Lead lead1, lead2;
    Map<Integer, Lead> leadMap1;
    Industry industry1,industry2;
    Contact contact1,contact2;
    List<Contact> contactList, contactList2;
    Opportunity opportunity1,opportunity2;
    List<Opportunity> opportunityList, opportunityList2;
    Account account1, account2;
    Map<Integer, Account> accountMap1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        crmCommand = new CRMMenu();
        lead1 = new Lead("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        lead2 = new Lead("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        leadMap1 = crmCommand.leadMap;
        leadMap1.put(lead1.getId(), lead1);
        leadMap1.put(lead2.getId(), lead2);
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        crmCommand.contactMap.put(contact1.getId(),contact1);
        crmCommand.contactMap.put(contact2.getId(),contact2);
        contactList = List.of(contact1);
        contactList2 = List.of(contact2);
        industry1 = Industry.ECOMMERCE;
        industry2 = Industry.OTHER;
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,0, contact2);
        opportunityList = List.of(opportunity1);
        opportunityList2 = List.of(opportunity2);
        crmCommand.opportunityMap.put(opportunity1.getId(),opportunity1);
        crmCommand.opportunityMap.put(opportunity2.getId(),opportunity2);
        accountMap1 = crmCommand.accountMap;
        account1 = new Account(industry1,456,"Barcelona","España",contactList,opportunityList);
        account2 = new Account(industry2,56,"Roma","Italia",contactList2,opportunityList2);
        accountMap1.put(account1.getId(), account1);
        accountMap1.put(account2.getId(), account2);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        lead1.setLeadIdCounter(1);
        account1.setAccountIdCounter(300000); //reseteamos el id_counter para que no nos afecte en los test
        opportunity1.setOpportunityIdCounter(200000);
        contact1.setContactIdCounter(100000);

        System.setOut(originalOut);
    }

    @Test
    void getCommandString_OnlyLetters_CorrectOutput(){
        assertEquals("CLOSE_WON", crmCommand.getCommandString("close won"));
        assertEquals("CONVERT", crmCommand.getCommandString("  convert "));
    }

    @Test
    void getCommandString_LettersAndNumbers_CorrectOutput(){
        assertEquals("CLOSE_WON", crmCommand.getCommandString("close won 12"));
        assertEquals("CONVERT", crmCommand.getCommandString(" convert 5"));
    }

    @Test
    void getCommandString_OnlyNumbers_EmptyOutput(){
        assertEquals("", crmCommand.getCommandString("12"));
    }

    @Test
    void getCommandString_OnlySpaces_EmptyOutput(){
        assertEquals("", crmCommand.getCommandString("  "));
    }

    @Test
    void getCommandString_EmptyString_EmptyOutput(){
        assertEquals("", crmCommand.getCommandString(""));
    }

    @Test
    void getCommandId_OnlyLetters_NegativeOutput(){
        assertEquals(-1, crmCommand.getCommandId("close won"));
    }

    @Test
    void getCommandId_LettersAndNumbers_CorrectOutput(){
        assertEquals(12, crmCommand.getCommandId("close won 12"));
        assertEquals(5, crmCommand.getCommandId(" convert 5 "));
    }

    @Test
    void getCommandId_OnlyNumbers_CorrectOutput(){
        assertEquals(12, crmCommand.getCommandId("12"));
        assertEquals(5, crmCommand.getCommandId("  5  "));
    }

    @Test
    void getCommandId_OnlySpaces_NegativeOutput(){
        assertEquals(-1, crmCommand.getCommandId("  "));
    }

    @Test
    void getCommandId_EmptyString_NegativeOutput(){
        assertEquals(-1, crmCommand.getCommandId(""));
    }

    @Test
    void menuActions_showExistingLeads_LeadMap() throws InterruptedException {
        crmCommand.menuActions(Command.SHOW_LEADS,-1);
        assertEquals("id: 1 -> name: Alex Bermejo" + "\n" +
                "id: 2 -> name: Alejandro Hernandez" , outContent.toString().trim());
    }

    @Test
    void menuActions_showNoLeads_LeadMap() throws InterruptedException {
        crmCommand.leadMap = new HashMap<>();
        crmCommand.menuActions(Command.SHOW_LEADS,-1);
        assertEquals("There are no Leads yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_showExistingContacts_ContactMap() throws InterruptedException {
        crmCommand.menuActions(Command.SHOW_CONTACTS,-1);
        assertEquals("id: 100001 -> name: Alejandro Hernandez" + "\n" +
                "id: 100000 -> name: Alex Bermejo" , outContent.toString().trim());
    }

    @Test
    void menuActions_showNoContacts_ContactMap() throws InterruptedException {
        crmCommand.contactMap = new HashMap<>();
        crmCommand.menuActions(Command.SHOW_CONTACTS,-1);
        assertEquals("There are no Contacts yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_showExistingOpportunites_OpportunityMap() throws InterruptedException {
        crmCommand.menuActions(Command.SHOW_OPPORTUNITIES,-1);
        assertEquals("id: 200001 -> decision maker: Alejandro Hernandez" + "\n" +
                "id: 200000 -> decision maker: Alex Bermejo" , outContent.toString().trim());
    }

    @Test
    void menuActions_showNoOpportunities_OpportunityMap() throws InterruptedException {
        crmCommand.opportunityMap = new HashMap<>();
        crmCommand.menuActions(Command.SHOW_OPPORTUNITIES,-1);
        assertEquals("There are no Opportunities yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_showExistingAccounts_AccountMap() throws InterruptedException {
        crmCommand.menuActions(Command.SHOW_ACCOUNTS,-1);
        assertEquals("id: 300000 -> name of first contact: Alex Bermejo\n"+
                "id: 300001 -> name of first contact: Alejandro Hernandez" , outContent.toString().trim());
    }

    @Test
    void menuActions_showNoAccounts_AccountMap() throws InterruptedException {
        crmCommand.accountMap = new HashMap<>();
        crmCommand.menuActions(Command.SHOW_ACCOUNTS,-1);
        assertEquals("There are no Accounts yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpExistingLeads_LeadMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_LEAD,1);
        assertEquals("=== Lead 1 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
                "- email: alex.bermejo@gmail.com\n- company name: Accenture" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoExistingLeads_LeadMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_LEAD,20);
        assertEquals("Id not found, try again" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoLeads_LeadMap() throws InterruptedException {
        crmCommand.leadMap = new HashMap<>();
        crmCommand.menuActions(Command.LOOKUP_LEAD,1);
        assertEquals("There are no Leads yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpExistingContacts_ContactMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_CONTACT,100000);
        assertEquals("=== Contact 100000 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
        "- email: alex.bermejo@gmail.com\n- company name: Accenture" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoExistingContacts_ContactMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_CONTACT,100020);
        assertEquals("Id not found, try again" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoContacts_ContactMap() throws InterruptedException {
        crmCommand.contactMap = new HashMap<>();
        crmCommand.menuActions(Command.LOOKUP_CONTACT,100000);
        assertEquals("There are no Contacts yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpExistingOpportunites_OpportunityMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_OPPORTUNITY,200000);
        assertEquals("=== Opportunity 200000 ===\n- product: BOX\n- trucks quantity: 9356\n" +
                "- decision maker:  CONTACT 100000\n   - name: Alex Bermejo\n   - phone number: +34 652899076\n" +
                "   - email: alex.bermejo@gmail.com\n   - company name: Accenture\n- status: OPEN" ,
                outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoExistingOpportunites_OpportunityMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_OPPORTUNITY,200020);
        assertEquals("Id not found, try again" ,
                outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoOpportunities_OpportunityMap() throws InterruptedException {
        crmCommand.opportunityMap = new HashMap<>();
        crmCommand.menuActions(Command.LOOKUP_OPPORTUNITY,200000);
        assertEquals("There are no Opportunities yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpExistingAccounts_AccountMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_ACCOUNT,300000);
        assertEquals("=== Account 300000 ===\n- industry: ECOMMERCE\n- employee count: 456\n- city: " +
                "Barcelona\n- country: España\n- " +
                "id's of the contacts: \n100000\n- id's of the opportunities: \n200000" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoExistingAccounts_AccountMap() throws InterruptedException {
        crmCommand.menuActions(Command.LOOKUP_ACCOUNT,300020);
        assertEquals("Id not found, try again" , outContent.toString().trim());
    }

    @Test
    void menuActions_LookUpNoAccounts_AccountMap() throws InterruptedException {
        crmCommand.accountMap = new HashMap<>();
        crmCommand.menuActions(Command.LOOKUP_ACCOUNT,300000);
        assertEquals("There are no Accounts yet" , outContent.toString().trim());
    }

    @Test
    void menuActions_CloseWonExistingOpportunity_CloseWonMessage() throws InterruptedException {
        crmCommand.menuActions(Command.CLOSE_WON,200000);
        assertEquals("Status of Opportunity 200000 changed to CLOSED_WON", outContent.toString().trim());
    }

    @Test
    void menuActions_CloseWonNoExistingOpportunity_CloseWonMessage() throws InterruptedException {
        crmCommand.menuActions(Command.CLOSE_WON,200000);
        assertEquals("Status of Opportunity 200000 changed to CLOSED_WON", outContent.toString().trim());
    }

    @Test
    void menuActions_CloseLostNoExistingOpportunity_CloseLostMessage() throws InterruptedException {
        crmCommand.menuActions(Command.CLOSE_LOST,200000);
        assertEquals("Status of Opportunity 200000 changed to CLOSED_LOST", outContent.toString().trim());
    }

    @Test
    void menuActions_helpCommand_HelpMessage() throws InterruptedException {
        crmCommand.menuActions(Command.HELP,-1);
        assertEquals("=== Possible Commands ===\n"+
                "NEW LEAD -> Add Lead to the CRM system\n" +
                "SHOW LEADS -> Display a list of all the LEADS' id and name\n" +
                "SHOW CONTACTS -> Display a list of all the CONTACTS' id and name\n" +
                "SHOW OPPORTUNITIES -> Display a list of all the OPPORTUNITIES' id and name of the decision maker\n" +
                "SHOW ACCOUNTS -> Display a list of all the ACCOUNTS id and name of the first contact\n" +
                "LOOKUP LEAD <ID> -> Display the selected LEAD's details with the indicated Id Number\n" +
                "LOOKUP ACCOUNT <ID> -> Display the selected ACCOUNT's details with the indicated Id Number\n" +
                "LOOKUP OPPORTUNITY <ID> -> Display the selected OPPORTUNITY's details with the indicated Id Number\n" +
                "LOOKUP CONTACT <ID> -> Display the selected CONTACT's details with the indicated Id Number\n" +
                "CONVERT <ID> -> Converts the selected LEAD in CONTACT, OPPORTUNITY and ACCOUNT " +
                "and removes it from the system\n" +
                "CLOSE LOST <ID> -> Changes the selected OPPORTUNITY status to CLOSE-LOST\n" +
                "CLOSE WON <ID> -> Changes the selected OPPORTUNITY status to CLOSE-WON\n" +
                "QUIT -> Terminates the CRM system",outContent.toString().trim());
    }
}