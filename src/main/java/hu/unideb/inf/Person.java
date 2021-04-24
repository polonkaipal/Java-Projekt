/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author polon
 */
@Entity
public class Person {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "locations")
    @OneToMany(targetEntity = Location.class)
    private List<Location> locations;
    
    
    
    private ObservableList<String> names;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthOfYear
     */
    public LocalDate getBirthOfYear() {
        return dateOfBirth;
    }

    /**
     * @param birthOfYear the birthOfYear to set
     */
    public void setBirthOfYear(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }
    
    /**
     * @return the location list names
     */
    public ObservableList<String> getLocationListNames() {
        return names;
    }

    /**
     * @param locationListNames the locationListNames to set
     */
    public void setLocationListNames(ObservableList<String> names) {
        this.names = names;
    }

    
    public Person(String name, LocalDate dateOfBirth, List<Location> locations, ObservableList<String> names) {
        this.names = names;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.locations = locations;
    }

}
