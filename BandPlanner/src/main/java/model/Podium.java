package model;

import java.util.UUID;

/**
 *
 * @author maxim
 */
public class Podium {
    
    private String name;
    private UUID podiumId;
    
    public Podium(String name, UUID id){
        this.name = name;
        this.podiumId = id;
    }
    
    public Podium(String name){
        this.name = name;
        podiumId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getPodiumId() {
        return podiumId;
    }

    public void setPodiumId(UUID podiumId) {
        this.podiumId = podiumId;
    }
    
}
