package com.example.limjichen.recycling2.shopping_library;

/**
 * Created by LIMJICHEN on 12/2/2018.
 */

public class Album {
    private String name;
    private String numOfSongs;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, String numOfSongs, int thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return numOfSongs;
    }

    /*public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }*/

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
