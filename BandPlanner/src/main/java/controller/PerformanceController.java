package controller;

import datalayer.ArtistDAO;
import datalayer.PerformanceDAO;
import datalayer.PodiumDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import model.Artist;
import model.Performance;
import model.Podium;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maxim
 */
@Controller("PerformanceC")
public class PerformanceController {

    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();
    ArtistDAO artistDAO = ArtistDAO.getInstance();
    PodiumDAO podiumDAO = PodiumDAO.getInstance();

    // Add Performance
    @RequestMapping(value = "/addPerformance", method = RequestMethod.POST)
    public ModelAndView addPerformance(HttpServletRequest request) throws ParseException {

        String artistParam = request.getParameter("artist");
        String podiumParam = request.getParameter("podium");
        String startTimeParam = request.getParameter("starttime");
        String endTimeParam = request.getParameter("endtime");

        Artist artist = artistDAO.getArtistById(UUID.fromString(artistParam));
        Podium podium = podiumDAO.getPodiumById(UUID.fromString(podiumParam));

        DateFormat formatStartTime = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        DateFormat formatEndTime = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        Date startTime = formatStartTime.parse(startTimeParam);
        Date endTime = formatEndTime.parse(endTimeParam);

        Performance p = new Performance(podium, artist, startTime, endTime);

        performanceDAO.createPerformance(p);

        return new ModelAndView("redirect:/");
    }
    
    // Update Performance
    @RequestMapping(value = "/updatePerformance", method = RequestMethod.POST)
    public ModelAndView updatePerformance(HttpServletRequest request) throws ParseException {
        
        String id = request.getParameter("performanceId");
        String artistParam = request.getParameter("artist");
        String podiumParam = request.getParameter("podium");
        String startTimeParam = request.getParameter("starttime");
        String endTimeParam = request.getParameter("endtime");
        
        Artist artist = artistDAO.getArtistById(UUID.fromString(artistParam));
        Podium podium = podiumDAO.getPodiumById(UUID.fromString(podiumParam));

        DateFormat formatStartTime = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        DateFormat formatEndTime = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        
        Date starttime = formatStartTime.parse(startTimeParam);
        Date endtime = formatEndTime.parse(endTimeParam);
        
        Performance p = performanceDAO.getPerformanceById(UUID.fromString(id));
        
        p.setArtist(artist);
        p.setPodium(podium);
        p.setStarttime(starttime);
        p.setEndtime(endtime);
        
        performanceDAO.updatePerformance(p);
        
        return new ModelAndView("redirect:/");
    }

    // Delete Performance
    @RequestMapping(value = "/deletePerformance", method = RequestMethod.GET)
    public ModelAndView deletePerformance(HttpServletRequest request) {

        UUID performanceID = UUID.fromString(request.getParameter("id"));
        performanceDAO.deletePerformance(performanceDAO.getPerformanceById(performanceID));

        return new ModelAndView("redirect:/");
    }

    // Get Performance Info
    @RequestMapping(value = "/viewPerformance", method = RequestMethod.GET)
    public String viewPerformance(HttpServletRequest request, ModelMap map) {

        UUID performanceID = UUID.fromString(request.getParameter("id"));

        map.put("thisPerformance", performanceDAO.getPerformanceById(performanceID));

        return ("viewPerformance");
    }

    // Get Next Performance Info
    @RequestMapping(value = "/getNextPerformance", method = RequestMethod.GET)
    public String getNextPerformance(HttpServletRequest request, ModelMap map) {

        String performanceID = request.getParameter("id");

        Performance p = performanceDAO.getNextPerformance(performanceID);

        map.put("thisPerformance", p);

        return ("viewPerformance");
    }

    // Get Previous Performance Info
    @RequestMapping(value = "/getPreviousPerformance", method = RequestMethod.GET)
    public String getPreviousPerformance(HttpServletRequest request, ModelMap map) {

        String performanceID = request.getParameter("id");

        Performance p = performanceDAO.getPreviousPerformance(performanceID);

        map.put("thisPerformance", p);

        return ("viewPerformance");
    }
}
