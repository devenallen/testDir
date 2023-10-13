package edu.uga.cs4370;

public class Movie {
    public String name;
    public int id;
    public int year;
    public int length;
    public String url;

    Movie(String title, String url, int id, int year, int length) {
        this.name = title;
        this.id = id;
        this.url = url;
        this.year = year;
        this.length = length;
    }
}
