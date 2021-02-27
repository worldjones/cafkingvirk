package dtos;

import entities.JokeEntity;


public class JokeDTO {
    
    private int id;
    private String theJoke;

    public JokeDTO(JokeEntity joke) {
        this.id = joke.getId();
        this.theJoke = joke.getTheJoke();
    }    

    public int getId() {
        return id;
    }

    public String getTheJoke() {
        return theJoke;
    }
    
    
    
}