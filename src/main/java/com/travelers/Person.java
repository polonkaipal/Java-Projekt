/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author polon
 */
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    
    @Column(name = "dateOfBirth", nullable = false, unique = false)
    private LocalDate dateOfBirth;
    
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Location> locations = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
    public Person(){
        
    }

    public Person(String name, LocalDate dateOfBirth, List<Location> locations) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public void removeLocation(Location location) {
        this.locations.remove(location);
    }
    
    public void removeAllLocation() {
        this.locations.removeAll(locations);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
