package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {


    HashMap<String,Movie> moviesHashMap = new HashMap<>();
    HashMap<String,Director> directorHashMap = new HashMap<>();
    HashMap<String, List<String>> moviesDirectorHashMap = new HashMap<>();

    public void addMovie(Movie movie){
       String movieName = movie.getName();
       moviesHashMap.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        directorHashMap.put(directorName,director);
    }

}
