/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author veres
 */
public class JpaPersonDAOTest {
    
    @Mock
    private PersonDAO underTestPersonDAO;
    private LocationDAO underTestLocationDAO;
    private List<Person> originalPersonList = new ArrayList<>();
    private List<Location> originalLocationList = new ArrayList<>();
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTestPersonDAO = new JpaPersonDAO();
        underTestLocationDAO = new JpaLocationDAO();
        originalPersonList.addAll(underTestPersonDAO.getPersons());
        originalLocationList.addAll(underTestLocationDAO.getLocations());
    }
    
    @AfterEach
    public void tearDown() {
        for (Location l : underTestLocationDAO.getLocations()) {
            if (originalLocationList.indexOf(l) == -1) {
                underTestLocationDAO.deleteLocation(l);
            }
        }
        for (Person p : underTestPersonDAO.getPersons()) {
            if (originalPersonList.indexOf(p) == -1) {
                System.out.println(p);
                underTestPersonDAO.deletePerson(p);
            }
        }
        underTestPersonDAO.close();
        underTestLocationDAO.close();
    }

    @Test
    public void TestSavePersonAndSaveLocation() {
        Location location = new Location("JpaDAOTestLocation1", 12.345, 23.456, 56.435, "", "");
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        Person person = new Person("JpaDAOTestPerson1", LocalDate.of(1500, 10, 10), locations);
        underTestLocationDAO.saveLocation(location);
        underTestPersonDAO.savePerson(person);
        Assertions.assertNotEquals(underTestLocationDAO.getLocations().indexOf(location), -1);
        Assertions.assertNotEquals(underTestPersonDAO.getPersons().indexOf(person), -1);
    }
    
    @Test
    public void TestUpdatePersonAndUpdateLocation() {
        Location location = new Location("JpaDAOTestLocation2", 12.345, 23.456, 56.435, "", "");
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        Person person = new Person("JpaDAOTestPerson2", LocalDate.of(1500, 10, 10), locations);
        underTestLocationDAO.saveLocation(location);
        underTestPersonDAO.savePerson(person);
        person.setName("JpaDAOTestPersonUpdate");
        underTestPersonDAO.updatePerson(person);
        location.setName("JpaDAOTestLocationUpdate");
        underTestLocationDAO.updateLocation(location);
        Assertions.assertEquals(underTestLocationDAO.getLocations().get(underTestLocationDAO.getLocations().indexOf(location)).getName(), "JpaDAOTestLocationUpdate");
        Assertions.assertEquals(underTestPersonDAO.getPersons().get(underTestPersonDAO.getPersons().indexOf(person)).getName(), "JpaDAOTestPersonUpdate");
    }
}
