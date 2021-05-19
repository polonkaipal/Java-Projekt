/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author veres
 */
public class PersonTest {

    @Mock
    private Person underTest1, underTest2;

    @BeforeAll //@BeforeClass
    public static void setUpClass() {
    }

    @AfterAll //@AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Location> locations1 = new ArrayList<>();
        List<Location> locations2 = new ArrayList<>();
        locations1.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        locations2.add(new Location("Paphos", 34.852734, 32.372928, 50.456, "", ""));
        underTest1 = new Person("Kalányos Pirike", LocalDate.of(2000, 10, 24), locations1);
        underTest2 = new Person("Bodor Péter", LocalDate.of(1999, 07, 11), locations2);

    }

    @AfterEach //@After
    public void tearDown() {
    }
    
    @Test
    public void TestGetName() {
        assertEquals(underTest1.getName(), "Kalányos Pirike");
        assertEquals(underTest2.getName(), "Bodor Péter");
    }
    
    @Test
    public void TestSetName() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person("Szabó Bence", LocalDate.of(2001, 8, 14), locations);
        p.setName("Kovács Gyula");
        Assertions.assertNotEquals(p.getName(), "Szabó Bence");
        Assertions.assertEquals(p.getName(), "Kovács Gyula");
    }
    
    @Test
    public void TestGetDateOfBirth() {
        Assertions.assertEquals(underTest1.getDateOfBirth(), LocalDate.of(2000, 10, 24));
        Assertions.assertEquals(underTest2.getDateOfBirth(), LocalDate.of(1999, 07, 11));
    }
    
    @Test
    public void TestSetDateOfBirth() {
        underTest1.setDateOfBirth(LocalDate.of(2001, 8, 16));
        Assertions.assertNotEquals(underTest1.getDateOfBirth(), LocalDate.of(2001, 8, 14));
        Assertions.assertEquals(underTest1.getDateOfBirth(), LocalDate.of(2001, 8, 16));
        underTest1.setDateOfBirth(LocalDate.of(2001, 8, 14));
    }
    
    @Test
    public void TestGetLocations() {
        List<Location> test = new ArrayList<>();
        test.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Assertions.assertEquals(underTest1.getLocations(), test);
        Assertions.assertEquals(underTest2.getLocations().get(0).getName(), "Paphos");
    }
    
    @Test
    public void TestSetLocations() {
        List<Location> test = new ArrayList<>();
        test.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person("Szabó Bence", LocalDate.of(2001, 8, 14), test);
        assertEquals(p.getLocations(), test);
        test.add(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        p.setLocations(test);
        assertEquals(p.getLocations(), test);
        assertEquals(p.getLocations().get(1).getName(), "Párizs");
    }
    
    @Test
    public void TestGetId() {
        Assertions.assertEquals(underTest1.getId(), 0);
        Assertions.assertEquals(underTest2.getId(), 0);
    }
    
    @Test
    public void TestSetId() {
        underTest1.setId(21);
        underTest2.setId(22);
        Assertions.assertNotEquals(underTest1.getId(), underTest2.getId());
        assertEquals(underTest1.getId(), 21);
        assertEquals(underTest2.getId(), 22);
    }
    
    @Test
    public void TestAddLocation() {
        List<Location> test = new ArrayList<>();
        test.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        assertEquals(underTest1.getLocations(), test);
        underTest1.addLocation(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        test.add(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        assertEquals(underTest1.getLocations(), test);
    }
    
    @Test
    public void TestRemoveLocation() {
        List<Location> test = new ArrayList<>();
        test.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        assertEquals(underTest1.getLocations(), test);
        test.add(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        underTest1.addLocation(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        underTest1.removeLocation(underTest1.getLocations().get(1));
        test.remove(1);
        assertEquals(underTest1.getLocations(), test);
    }
    
    @Test
    public void TestConstructor() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person("Kalányos Pirike", LocalDate.of(2000, 10, 24), locations);
        assertEquals(p.getName(), "Kalányos Pirike");
        assertEquals(p.getDateOfBirth(), LocalDate.of(2000, 10, 24));
        assertEquals(p.getLocations(), locations);
    }
    
    @Test
    public void TestDefaultConstructor() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person();
        Assertions.assertNull(p.getName());
        Assertions.assertNull(p.getDateOfBirth());
        Assertions.assertEquals(p.getLocations(), new ArrayList<Location>());
    }
    
    @Test
    public void TestToString() {
        Assertions.assertEquals(underTest1.toString(), "Kalányos Pirike");
    }
    
    @Test
    public void TestRemoveAllLocation() {
        underTest1.removeAllLocation();
        Assertions.assertTrue(underTest1.getLocations().isEmpty());
    }
}

