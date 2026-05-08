package models;

import java.util.List;

public class Game {
    private int id;
    private String name;
    private List<String> genres;
    private List<String> modes;
    private List<String> platforms;
    private String imagePath;
	private boolean featured;
	
	public Game() {
	}

    public Game(int id, String name, List<String> genres, List<String> modes, List<String> platforms, String imagePath, boolean featured) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.modes = modes;
        this.platforms = platforms;
        this.imagePath = imagePath;
        this.featured = featured;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public List<String> getGenres() {
        return genres;
    }
    
    public List<String> getModes() {
        return modes;
    }

    public List<String> getPlatforms() {
        return platforms;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public boolean getFeatured() {
        return featured;
    }

    @Override
    public String toString() {
        return name
        		+ " (" + genres + ")"
        		+ " (" + modes + ")"
				+ " (" + platforms + ")";
    }
}