package ru.snslabs.icq.model;

import java.io.Serializable;
import java.util.*;

/**
 * ћодель контактного листа
 */
public class ContactList implements Serializable {

    private Map contacts = new LinkedHashMap(); // список контактов
 
    public ContactList() {
    }

    public void addContact(String uin, String nick, String dsc){
        contacts.put(uin, new Contact(uin, nick, dsc));
    }

    public Map getContacts() {
        return contacts;
    }

    public Contact getContact(String uin){
        return (Contact) contacts.get(uin);
    }

    public void setContacts(Map contacts) {
        this.contacts = contacts;
    }

    public String[] getContactsUINs(){
        return (String[]) contacts.keySet().toArray();
    }

    public Collection getContactsArray(){
        if(!contacts.isEmpty()){
            return  contacts.values();
        }
        else{
            return new ArrayList();
        }
    }

    public String getNickName(String uin){
        Contact contact = ((Contact) contacts.get(uin));
        if(contact != null){
            return contact.getNick();
        }
        else{
            return "-"+uin;
        }
    }

}
