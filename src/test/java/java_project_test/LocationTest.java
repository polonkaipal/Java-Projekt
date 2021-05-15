/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project_test;

import hu.unideb.inf.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author veres
 */
public class LocationTest {
    
    @Mock
    private Location underTest1, underTest2;

    @BeforeAll //@BeforeClass
    public static void setUpClass() {
    }

    @AfterAll //@AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest1 = new Location("Firenze", 43.792363, 11.246212, 10.5, "kepFirenze", "zivataros");
        underTest2 = new Location("Paphos", 34.852734, 32.372928, 50.456, "Picture", "csendes, nyugodt");
    }

    @AfterEach //@After
    public void tearDown() {
    }
    
    @Test
    public void TestGetId() {
        Assertions.assertEquals(underTest1.getId(), 0);
        Assertions.assertEquals(underTest2.getId(), 0);
    }
    
    @Test
    public void TestSetId() {
        underTest1.setId(11);
        underTest2.setId(22);
        Assertions.assertNotEquals(underTest1.getId(), underTest2.getId());
        assertEquals(underTest1.getId(), 11);
        assertEquals(underTest2.getId(), 22);
    }
    
    @Test
    public void TestGetName() {
        assertEquals(underTest1.getName(), "Firenze");
        assertEquals(underTest2.getName(), "Paphos");
    }
    
    @Test
    public void TestSetName() {
        underTest1.setName("Párizs");
        underTest2.setName("Kiskunfélegyháza");
        assertNotEquals(underTest1.getName(), "Firenze");
        assertNotEquals(underTest2.getName(), "Paphos");
        assertEquals(underTest1.getName(), "Párizs");
        assertEquals(underTest2.getName(), "Kiskunfélegyháza");
    }
    
    @Test
    public void TestGetLatitude() {
        assertEquals(underTest1.getLatitude(), 43.792363);
        assertEquals(underTest2.getLatitude(), 34.852734);
    }
    
    @Test
    public void TestSetLatitude() {
        underTest1.setLatitude(12.123);
        underTest2.setLatitude(32.456);
        assertNotEquals(underTest1.getLatitude(), 43.792363);
        assertNotEquals(underTest2.getLatitude(), 34.852734);
        assertEquals(underTest1.getLatitude(), 12.123);
        assertEquals(underTest2.getLatitude(), 32.456);
    }
    
    @Test
    public void TestGetLongitude() {
        assertEquals(underTest1.getLongitude(), 11.246212);
        assertEquals(underTest2.getLongitude(), 32.372928);
    }
    
    @Test
    public void TestSetLongitude() {
        underTest1.setLongitude(12.123);
        underTest2.setLongitude(32.456);
        assertNotEquals(underTest1.getLongitude(), 11.246212);
        assertNotEquals(underTest2.getLongitude(), 32.372928);
        assertEquals(underTest1.getLongitude(), 12.123);
        assertEquals(underTest2.getLongitude(), 32.456);
    }
    
    @Test
    public void TestGetAltitude() {
        assertEquals(underTest1.getAltitude(), 10.5);
        assertEquals(underTest2.getAltitude(), 50.456);
    }
    
    @Test
    public void TestSetAltitude() {
        underTest1.setAltitude(12.123);
        underTest2.setAltitude(32.456);
        assertNotEquals(underTest1.getAltitude(), 10.5);
        assertNotEquals(underTest2.getAltitude(), 50.456);
        assertEquals(underTest1.getAltitude(), 12.123);
        assertEquals(underTest2.getAltitude(), 32.456);
    }
    
    @Test
    public void TestGetImage() {
        assertEquals(underTest1.getImage(), "kepFirenze");
        assertEquals(underTest2.getImage(), "Picture");
    }
    
    @Test
    public void TestSetImage() {
        underTest1.setImage("PictureFirenze");
        underTest2.setImage("kepPaphos");
        assertNotEquals(underTest1.getImage(), "kepFirenze");
        assertNotEquals(underTest2.getImage(), "Picture");
        assertEquals(underTest1.getImage(), "PictureFirenze");
        assertEquals(underTest2.getImage(), "kepPaphos");
    }
    
    @Test
    public void TestGetDetails() {
        assertEquals(underTest1.getDetails(), "zivataros");
        assertEquals(underTest2.getDetails(), "csendes, nyugodt");
    }
    
    @Test
    public void TestSetDetails() {
        underTest1.setDetails("viharos, hűvös");
        underTest2.setDetails("mediterrán");
        assertNotEquals(underTest1.getDetails(), "zivataros");
        assertNotEquals(underTest2.getDetails(), "csendes, nyugodt");
        assertEquals(underTest1.getDetails(), "viharos, hűvös");
        assertEquals(underTest2.getDetails(), "mediterrán");
    }

    
    @Test
    public void TestConstructor() {
        
    }
}
