package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {


    private HashMap<String,Movie> moviesHashMap;
    private HashMap<String,Director> directorHashMap;
    private HashMap<String, List<String>> moviesDirectorHashMap;

    public MovieRepository() {
        this.moviesHashMap = new HashMap<>();
        this.directorHashMap = new HashMap<>();
        this.moviesDirectorHashMap = new HashMap<>();
    }

    public void addMovie(Movie movie){
       String movieName = movie.getName();
       moviesHashMap.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        directorHashMap.put(directorName,director);
    }

    //Movie director pair different from kunal, check here first if there is error
    public void addMovieDirectorPair(String movieName,String directorName){
        if(moviesHashMap.containsKey(movieName) && directorHashMap.containsKey(directorName)){
            moviesHashMap.put(movieName,moviesHashMap.get(movieName));
            directorHashMap.put(directorName,directorHashMap.get(directorName));
            List<String> currentMovies = new ArrayList<>();
            if (moviesDirectorHashMap.containsKey(directorName)) currentMovies = moviesDirectorHashMap.get(directorName);
            currentMovies.add(movieName);
            moviesDirectorHashMap.put(directorName,currentMovies);
        }
    }


    public Movie getMovieByName(String name){
        if(moviesHashMap.containsKey(name)){
            return moviesHashMap.get(name);
        }
        return null;
    }

    public Director getDirectorByName(String name){
        if(directorHashMap.containsKey(name)){
            return directorHashMap.get(name);
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String name){
        List<String> allMovies = new ArrayList<>();
        if(moviesDirectorHashMap.containsKey(name)){
            allMovies = moviesDirectorHashMap.get(name);
        }
        return allMovies;
    }

    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();
        for(String movie:moviesHashMap.keySet()){
            allMovies.add(movie);
        }
        return allMovies;
    }

    public void deleteDirectorByName(String name){
        List<String> movieList = new ArrayList<>();
        if (moviesDirectorHashMap.containsKey(name)){
            movieList = moviesDirectorHashMap.get(name);
            for (String movie:movieList){
                if(moviesHashMap.containsKey(movie)){
                    moviesHashMap.remove(movie);
                }
            }
        }
        if(directorHashMap.containsKey(name)){
            directorHashMap.remove(name);
        }
        moviesDirectorHashMap.remove(name);
    }

    public void deleteAllDirectors(){
        directorHashMap = new HashMap<>();
        HashSet<String> movies = new HashSet<>();
        for(String director:moviesDirectorHashMap.keySet()){
            for (String movieName: moviesDirectorHashMap.get(director)){
                movies.add(movieName);
            }
        }
        for (String movie:movies){
            if(moviesHashMap.containsKey(movie)){
                moviesHashMap.remove(movie);
            }
        }
        moviesDirectorHashMap = new HashMap<>();
    }

}
