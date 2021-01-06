/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdrestproject.controller;

import com.sg.dvdrestproject.dao.DVDDao;
import com.sg.dvdrestproject.entity.Dvd;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dvdapi")
public class DvDController {
    
    private final DVDDao dao;

    public DvDController(DVDDao dao) {
        this.dao = dao;
    }

    /** Get dvd by Id
     * 
     * @param id
     * @return 
     */
    @GetMapping("/dvd/{id}")
    public ResponseEntity<Dvd> findById(@PathVariable int id) {
    Dvd result = dao.findById(id);
    if (result == null) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
}  
    
    /** Get all Dvd's
     * 
     * @return 
     */
    @GetMapping ("/dvds")
    public List<Dvd> all() {
        return dao.getAll();
    }
    
    
    /** Get all Dvd's with the same title
     * 
     * @param title
     * @return 
     */
    @GetMapping("/dvds/title/{title}")
    public ResponseEntity<List<Dvd>> findByTitle(@PathVariable String title) {
    List<Dvd> result = dao.findByTitle(title);
    if (result == null || result.isEmpty()) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
    
    
} 
    /** Get all Dvd's with the same Release Year
     * 
     * @param releaseYear
     * @return 
     */
    @GetMapping("/dvds/year/{releaseYear}")
    public ResponseEntity<List<Dvd>> findByYear(@PathVariable int releaseYear) {
    List<Dvd> result = dao.findByReleaseYear(releaseYear);
    if (result == null) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
    
    
} 
    /** Get all Dvd's by the same director
     * 
     * @param director
     * @return 
     */
    @GetMapping("/dvds/director/{director}")
    public ResponseEntity<List<Dvd>> findByDirector(@PathVariable String director) {
    List<Dvd> result = dao.findByDirector(director);
    if (result == null) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
    
    
}   
    
    /** Get all Dvd's with the same rating
     * 
     * @param rating
     * @return 
     */
    @GetMapping("/dvds/rating/{rating}")
    public ResponseEntity<List<Dvd>> findByRating(@PathVariable String rating) {
    List<Dvd> result = dao.findByRating(rating);
    if (result == null) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
    
    
} 

    /** Post a new Dvd 
     * 
     * @param dvd
     * @return 
     */
    @PostMapping("/dvd")
    @ResponseStatus(HttpStatus.CREATED)
    public Dvd create(@RequestBody Dvd dvd) {
    return dao.add(dvd);
}
    
/** Update an already existing dvd 
 * 
 * @param id
 * @param dvd
 * @return 
 */
@PutMapping("/dvd/{id}")
public ResponseEntity update(@PathVariable int id, @RequestBody Dvd dvd) {
    ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
    if (id != dvd.getId()) {
        response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    } else if (dao.update(dvd)) {
        response = new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return response;
}

/** Delete an already existing Dvd
 * 
 * @param id
 * @return 
 */
@DeleteMapping("/dvd/{id}")
public ResponseEntity delete(@PathVariable int id) {
    if (dao.deleteById(id)) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity(HttpStatus.NOT_FOUND);
}
    
    
    
}
