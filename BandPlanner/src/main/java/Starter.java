
import datalayer.ArtistDAO;
import model.Artist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maxim
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Artist a = new Artist("Henk2", "Trash Metal");
        ArtistDAO.getInstance().createArtist(a);
        
        Artist b = ArtistDAO.getInstance().getArtistById(a.getArtistId());
        
        System.out.println(b.getName());
        
    }
    
}
