package com.sg.dvdrestproject.dao;

import com.sg.dvdrestproject.entity.Dvd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author chris
 */
@Repository
@Profile("database")
public class DvdDatabaseDao implements DVDDao {
    
    private final JdbcTemplate jdbc;

    @Autowired
    public DvdDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Dvd add(Dvd dvd) {
                
        final String sql = "INSERT INTO dvd(title, releaseYear, director, rating, notes) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, dvd.getTitle());
            statement.setInt(2, dvd.getReleaseYear());
            statement.setString(3, dvd.getDirector());
            statement.setString(4, dvd.getRating());
            statement.setString(5, dvd.getNotes());
            return statement;

        }, keyHolder);

        dvd.setId(keyHolder.getKey().intValue());

        return dvd;        
    }

     @Override
    public List<Dvd> getAll() {

        final String SELECT_ALL_DVDS = "SELECT * FROM dvd";

        return jdbc.query(SELECT_ALL_DVDS, new DVDMapper());
    }

    @Override
    public Dvd findById(int id) {

        try {
            final String SELECT_DVD_BY_ID = "SELECT * fROM dvd WHERE id = ?";

            return jdbc.queryForObject(SELECT_DVD_BY_ID, new DVDMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean update(Dvd dvd) {

        final String UPDATE_DVD = "UPDATE dvd SET title = ?, releaseYear = ?, director = ?, rating = ?, notes = ? WHERE id = ?";

        if (findById(dvd.getId()) != null) {
            jdbc.update(UPDATE_DVD, dvd.getTitle(), dvd.getReleaseYear(), dvd.getDirector(), dvd.getRating(), dvd.getNotes(), dvd.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {

        final String DELETE_DVD_BY_ID = "DELETE FROM dvd WHERE id = ?";

        jdbc.update(DELETE_DVD_BY_ID, id);

        if (findById(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Dvd> findByTitle(String title) {

        final String SELECT_DVD_BY_TITLE = "SELECT * FROM dvd WHERE title = ?";

        return jdbc.query(SELECT_DVD_BY_TITLE, new DVDMapper(), title);
    }

    @Override
    public List<Dvd> findByReleaseYear(int releaseYear) {

        final String SELECT_DVD_BY_RELEASE_YEAR = "SELECT * FROM dvd WHERE releaseYear = ?";

        return jdbc.query(SELECT_DVD_BY_RELEASE_YEAR, new DVDMapper(), releaseYear);
    }

    @Override
    public List<Dvd> findByDirector(String director) {

        final String SELECT_DVD_BY_DIRECTOR = "SELECT * FROM dvd WHERE director = ?";

        return jdbc.query(SELECT_DVD_BY_DIRECTOR, new DVDMapper(), director);
    }

    @Override
    public List<Dvd> findByRating(String rating) {

        final String SELECT_DVD_BY_RATING = "SELECT * FROM dvd WHERE rating = ?";

        return jdbc.query(SELECT_DVD_BY_RATING, new DVDMapper(), rating);
    }

    private static final class DVDMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int index) throws SQLException {

            Dvd dvd = new Dvd();
            dvd.setId(rs.getInt("id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseYear(rs.getInt("releaseYear"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));

            return dvd;
        }

    }

}