/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project_test;

import hu.unideb.inf.Location;
import hu.unideb.inf.Person;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
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
        underTest1 = new Person("Kalányos Pirike", LocalDate.of(2000, 10, 24), locations1, FXCollections.observableArrayList());
        underTest2 = new Person("Bodor Péter", LocalDate.of(1999, 07, 11), locations2, FXCollections.observableArrayList());

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
        Person p = new Person("Szabó Bence", LocalDate.of(2001, 8, 14), locations, FXCollections.observableArrayList());
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
        Person p = new Person("Szabó Bence", LocalDate.of(2001, 8, 14), test, FXCollections.observableArrayList());
        assertEquals(p.getLocations(), test);
        test.add(new Location("Párizs", 43.102363, 19.246212, 10.5, "", ""));
        p.setLocations(test);
        assertEquals(p.getLocations(), test);
        assertEquals(p.getLocations().get(1).getName(), "Párizs");
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
    public void TestGetLocationListNames() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person("Szabó Bence", LocalDate.of(2001, 8, 14), locations, FXCollections.observableArrayList());
        Assertions.assertEquals(p.getLocationListNames(), FXCollections.observableArrayList());
    }
    
    @Test
    public void TestSetLocationListNames() {
        underTest1.setLocationListNames(FXCollections.observableArrayList("Párizs", "Róma"));
        underTest2.setLocationListNames(FXCollections.observableArrayList("Budapest", "Debrecen"));
        Assertions.assertEquals(underTest1.getLocationListNames(), FXCollections.observableArrayList("Párizs", "Róma"));
        Assertions.assertNotEquals(underTest1.getLocationListNames(), FXCollections.observableArrayList());
        Assertions.assertEquals(underTest2.getLocationListNames(), FXCollections.observableArrayList("Budapest", "Debrecen"));
        Assertions.assertNotEquals(underTest2.getLocationListNames(), FXCollections.observableArrayList());
    }
    
    @Test
    public void TestConstructor() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Firenze", 43.792363, 11.246212, 10.5, "", "mediterrán"));
        Person p = new Person("Kalányos Pirike", LocalDate.of(2000, 10, 24), locations, FXCollections.observableArrayList());
        assertEquals(p.getName(), "Kalányos Pirike");
        assertEquals(p.getDateOfBirth(), LocalDate.of(2000, 10, 24));
        assertEquals(p.getLocations(), locations);
        assertEquals(p.getLocationListNames(), FXCollections.observableArrayList());
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
}

