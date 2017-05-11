package controller;

import datalayer.ArtistDAO;
import datalayer.PerformanceDAO;
import datalayer.PodiumDAO;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import model.Artist;
import model.Performance;
import model.Podium;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author maxim
 */
@Controller("defaultC")
public class DefaultController {

    ArtistDAO artistDAO = ArtistDAO.getInstance();
    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();
    PodiumDAO podiumDAO = PodiumDAO.getInstance();

    // Index Page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {

        map.put("getAllArtists", artistDAO.getAllArtists());
        map.put("getAllPerformances", performanceDAO.getAllPerformances());
        map.put("getAllPodia", podiumDAO.getAllPodia());

        return "index";
    }

    // Add Artist Page
    @RequestMapping(value = "/addArtist", method = RequestMethod.GET)
    public String addArtist(Model model) {
        
        Artist a = new Artist();
        
        model.addAttribute("artist", a);
        
        return "addArtist";
    }
    
    // Add Podium Page
    @RequestMapping(value = "/addPodium", method = RequestMethod.GET)
    public String addPodium(Model model) {
        
        Podium p = new Podium();
        
        model.addAttribute("podium", p);
        
        return "addPodium";
    }
    
    // Add Performance Page
    @RequestMapping(value = "/addPerformance", method = RequestMethod.GET)
    public String addPerformance(Model model, ModelMap map) {
        
        map.put("getAllArtists", artistDAO.getAllArtists());
        map.put("getAllPodia", podiumDAO.getAllPodia());

        Performance p = new Performance();
        
        model.addAttribute("performance", p);
        
        return "addPerformance";
    }
    
    // Update Artist Page
    @RequestMapping(value = "/editArtist", method = RequestMethod.GET)
    public String getArtist(HttpServletRequest request, ModelMap map, Model model) {
        
        Artist a = new Artist();
        
        model.addAttribute("artist", a);

        UUID artistID = UUID.fromString(request.getParameter("id"));

        map.put("thisArtist", artistDAO.getArtistById(artistID));

        return ("editArtist");
    }
    
    

}
