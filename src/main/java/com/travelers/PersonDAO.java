/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.util.List;

/**
 *
 * @author veres
 */
public interface PersonDAO  extends AutoCloseable {
    public void savePerson(Person p);
    public void deletePerson(Person p); 
    public void updatePerson(Person p);  
    public List<Person> getPersons();
    
    @Override
    default public void close(){        
    }
}
