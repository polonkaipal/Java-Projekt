/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import hu.unideb.inf.Tag;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 *
 * @author veres
 */
public class TagTest {

    @Mock
    private Tag underTest1, underTest2;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest1 = new Tag(); underTest1.setId(1); underTest1.setName("Egy");
        underTest2 = new Tag(); underTest2.setId(2); underTest2.setName("Kettő");
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void TestDefaultConstructor() {
        Tag t = new Tag();
        Assertions.assertNull(t.getName(), "Üres");  
    }
    
    @Test
    public void TestGetId() {
        assertEquals(underTest1.getId(), 1);
        assertEquals(underTest2.getId(), 2);
    }
    
    @Test
    public void TestSetId() {
        assertEquals(underTest1.getId(), 1);
        underTest1.setId(11);
        assertEquals(underTest1.getId(), 11);
        assertNotEquals(underTest1.getId(), 1);
    }
    
    @Test
    public void TestGetName() {
        assertEquals(underTest1.getName(), "Egy");
        assertEquals(underTest2.getName(), "Kettő");
    }
    
    @Test
    public void TestSetName() {
        assertEquals(underTest1.getName(), "Egy");
        assertEquals(underTest2.getName(), "Kettő");
        underTest1.setName("one");
        underTest2.setName("two");
        assertEquals(underTest1.getName(), "one");
        assertNotEquals(underTest1.getName(), "Egy");
        assertEquals(underTest2.getName(), "two");
        assertNotEquals(underTest2.getName(), "Kettő");
    }
}
