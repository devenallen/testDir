package edu.uga.cs4370;

public class MovieAndCast {
    public String name;
    public int id;
    public int year;
    public int length;
    public String url;
    public String actor;

    MovieAndCast(String title, String url, int id, int year, int length, String Actor) {
        this.name = title;
        this.id = id;
        this.url = url;
        this.year = year;
        this.length = length;
        this.actor = actor;
    }
}
