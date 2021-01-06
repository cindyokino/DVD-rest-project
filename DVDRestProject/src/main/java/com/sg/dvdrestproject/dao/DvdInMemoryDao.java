package com.sg.dvdrestproject.dao;

import com.sg.dvdrestproject.entity.Dvd;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cindy
 */
@Repository
@Profile("memory")
public class DvdInMemoryDao implements DVDDao{
    private static final List<Dvd> dvds = new ArrayList<>();

    @Override
    public Dvd add(Dvd dvd) {
        int nextId = dvds.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        dvd.setId(nextId);
        dvds.add(dvd);
        return dvd;
    }

    @Override
    public List<Dvd> getAll() {
        return new ArrayList<>(dvds);
    }

    @Override
    public Dvd findById(int id) {
        return dvds.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Dvd dvd) {
        int index = 0;
        while (index < dvds.size()
                && dvds.get(index).getId() != dvd.getId()) {
            index++;
        }

        if (index < dvds.size()) {
            dvds.set(index, dvd);
        }
        return index < dvds.size();
    }

    @Override
    public boolean deleteById(int id) {
        return dvds.removeIf(i -> i.getId() == id);
    }

    @Override
    public List<Dvd> findByTitle(String title) {
        return dvds.stream()
                .filter(i -> i.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findByDirector(String director) {
        return dvds.stream()
                .filter(i -> i.getDirector().equals(director))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findByRating(String rating) {
        return dvds.stream()
                .filter(i -> i.getRating().equals(rating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findByReleaseYear(int year) {
        return dvds.stream()
                .filter(i -> i.getReleaseYear() == year)
                .collect(Collectors.toList());
    }
    
}
