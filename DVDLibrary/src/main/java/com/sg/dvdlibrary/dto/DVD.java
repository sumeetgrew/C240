/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author Sumeet
 */
public class DVD {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String director;
    private String studio;
    private String userRating;

    public DVD(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getRelease() {
        return releaseDate;
    }
    
    public void setRelease(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMPAARating() {
        return mpaaRating;
    }
    
    public void setMPAARating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getStudio() {
        return studio;
    }
    
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    public String getUserRating() {
        return userRating;
    }
    
    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}