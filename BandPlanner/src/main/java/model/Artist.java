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
    
    public Artist(String name, String description, UUID id) {
        this.name = name;
        this.description = description;
        artistId = id;
    }

    public Artist(String name, String description) {
        this.name = name;
        this.description = description;
        artistId = UUID.randomUUID();
    }
    
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
