
import datalayer.ArtistDAO;
import datalayer.PodiumDAO;
import java.sql.Date;
import java.time.LocalDateTime;
import model.Artist;
import model.Performance;
import model.Podium;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

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
        Podium po = new Podium(" henk");
        System.out.println(b.getName());
        PodiumDAO.getInstance().createPodium(po);
        LocalDateTime d;
//        Date date = new Date()
//        Performance p = new Performance(po, a, , );
        
    }
    
}
