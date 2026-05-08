package models;

public class Game {
    private int id;
    private String name;
    private String genre;
    private String plataform;
    private String imagePath;
	private boolean featured;
	
	public Game() {
	}

    public Game(int id, String name, String genre, String plataform, String imagePath, boolean featured) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.plataform = plataform;
        this.imagePath = imagePath;
        this.featured = featured;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlataform() {
        return plataform;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public boolean getFeatured() {
        return featured;
    }

    @Override
    public String toString() {
        return name + " (" + genre + " - " + plataform + ")";
    }
}