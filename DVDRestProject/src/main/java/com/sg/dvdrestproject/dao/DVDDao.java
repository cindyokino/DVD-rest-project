/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdrestproject.dao;

import com.sg.dvdrestproject.entity.Dvd;
import java.util.List;

/**
 *
 * @author GrQuil
 */
public interface DVDDao {
    
    Dvd add(Dvd dvd);

    List<Dvd> getAll();

    Dvd findById(int id);

    // true if item exists and is updated
    boolean update(Dvd dvd);

    // true if item exists and is deleted
    boolean deleteById(int id);

    public List<Dvd> findByTitle(String title);

    public List<Dvd> findByReleaseYear(int year);
    
    public List<Dvd> findByDirector(String director);
    
    public List<Dvd> findByRating(String rating);
    
}