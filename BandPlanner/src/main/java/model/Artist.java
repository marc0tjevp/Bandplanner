package model;

import java.util.UUID;

/**
 *
 * @author maxim
 */
public class Artist {
    
    private String name;
    private String description;
    private UUID artistId;
    
    public Artist() {
        
    }
    
    //Constructor with ID in param to set
    public Artist(String name, String description, UUID id) {
        this.name = name;
        this.description = description;
        artistId = id;
    }

    //Constructor with random generated ID
    public Artist(String name, String description) {
        this.name = name;
        this.description = description;
        artistId = UUID.randomUUID();
    }
    
    //Constructor with only name for view purposes
    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }
    
}
