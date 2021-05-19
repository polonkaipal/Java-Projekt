/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

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
        underTest1.setLatitude(18.123);
        underTest2.setLatitude(36.456);
        assertNotEquals(underTest1.getLatitude(), 43.792363);
        assertNotEquals(underTest2.getLatitude(), 34.852734);
        assertEquals(underTest1.getLatitude(), 18.123);
        assertEquals(underTest2.getLatitude(), 36.456);
    }
    
    @Test
    public void TestGetLongitude() {
        assertEquals(underTest1.getLongitude(), 11.246212);
        assertEquals(underTest2.getLongitude(), 32.372928);
    }
    
    @Test
    public void TestSetLongitude() {
        underTest1.setLongitude(14.123);
        underTest2.setLongitude(35.456);
        assertNotEquals(underTest1.getLongitude(), 11.246212);
        assertNotEquals(underTest2.getLongitude(), 32.372928);
        assertEquals(underTest1.getLongitude(), 14.123);
        assertEquals(underTest2.getLongitude(), 35.456);
    }
    
    @Test
    public void TestGetAltitude() {
        assertEquals(underTest1.getAltitude(), 10.5);
        assertEquals(underTest2.getAltitude(), 50.456);
    }
    
    @Test
    public void TestSetAltitude() {
        underTest1.setAltitude(19.123);
        underTest2.setAltitude(33.456);
        assertNotEquals(underTest1.getAltitude(), 10.5);
        assertNotEquals(underTest2.getAltitude(), 50.456);
        assertEquals(underTest1.getAltitude(), 19.123);
        assertEquals(underTest2.getAltitude(), 33.456);
    }
    
    @Test
    public void TestGetImage() {
        assertEquals(underTest1.getImg(), "kepFirenze");
        assertEquals(underTest2.getImg(), "Picture");
    }
    
    @Test
    public void TestSetImage() {
        underTest1.setImg("PictureFirenze");
        underTest2.setImg("kepPaphos");
        assertNotEquals(underTest1.getImg(), "kepFirenze");
        assertNotEquals(underTest2.getImg(), "Picture");
        assertEquals(underTest1.getImg(), "PictureFirenze");
        assertEquals(underTest2.getImg(), "kepPaphos");
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
        Location testLoc = new Location("Firenze", 43.792363, 11.246212, 10.5, "kepFirenze", "zivataros");
        
        assertEquals(testLoc.getName(), "Firenze");
        assertEquals(testLoc.getLatitude(), 43.792363);
        assertEquals(testLoc.getLongitude(), 11.246212);
        assertEquals(testLoc.getAltitude(), 10.5);
        assertEquals(testLoc.getImg(), "kepFirenze");
        assertEquals(testLoc.getDetails(), "zivataros");
    }
    
    @Test
    public void TestDefaultConstructor() {
        Location testLoc = new Location();
        Assertions.assertNull(testLoc.getName());
        Assertions.assertEquals(testLoc.getLatitude(), 0.0);
        Assertions.assertEquals(testLoc.getLongitude(), 0.0);
        Assertions.assertEquals(testLoc.getAltitude(), 0.0);
    }
    
    @Test
    public void TestEquals() {
        Location loc1 = new Location("Firenze", 43.792363, 11.246212, 11.5, "kepFirenze", "zivataros");
        Location loc2 = new Location("Firenze", 43.792363, 11.246212, 10.5, "kepFirenze", "zivataros");
        Location loc3 = new Location("Firenze", 43.792363, 12.246212, 10.5, "kepFirenze", "zivataros");
        Location loc4 = new Location("Amsterdam", 43.792363, 12.246212, 10.5, "kepFirenze", "zivataros");
        assertFalse(underTest1.equals(underTest2));
        assertFalse(underTest1.equals(underTest2.getName()));
        assertFalse(underTest1.equals(null));
        assertTrue(underTest1.equals(underTest1));
        assertFalse(loc1.equals(loc2));
        assertFalse(loc1.equals(loc3));
        assertFalse(loc3.equals(loc4));
    }
    
    @Test
    public void TestToString() {
        Assertions.assertEquals(underTest1.toString(), "Firenze");
        Assertions.assertEquals(underTest2.toString(), "Paphos");
    }
}
