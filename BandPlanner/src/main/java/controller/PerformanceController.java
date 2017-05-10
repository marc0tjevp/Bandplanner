/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maxim
 */

@Controller ("PerformanceC")
public class PerformanceController {

    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();
    ArtistDAO artistDAO = ArtistDAO.getInstance();
    PodiumDAO podiumDAO = PodiumDAO.getInstance();

    // Add Performance
    @RequestMapping(value = "/addPerformance", method = RequestMethod.POST)
    public ModelAndView addPerformance(HttpServletRequest request) throws ParseException {
        String artistParam = request.getParameter("artistID");
        String podiumParam = request.getParameter("podiumID");
        String startTimeParam = request.getParameter("starttime");
        String endTimeParam = request.getParameter("endtime");
        
        Artist artist = artistDAO.getArtistById(UUID.fromString(artistParam));
        Podium podium = podiumDAO.getPodiumById(UUID.fromString(podiumParam));
        
        DateFormat formatStartTime =  new SimpleDateFormat("");
        DateFormat formatEndTime = new SimpleDateFormat("");
        
        Date startTime = formatStartTime.parse(startTimeParam);
        Date endTime = formatEndTime.parse(endTimeParam);
         
        Performance p = new Performance(podium, artist, startTime, endTime);
        
        performanceDAO.createPerformance(p);
        
        return new ModelAndView("redirect:/");
    }
    
    // Delete Performance
    @RequestMapping(value = "/deletePerformance", method = RequestMethod.GET)
    public ModelAndView deletePerformance(HttpServletRequest request) {

        UUID performanceID = UUID.fromString(request.getParameter("id"));
        performanceDAO.deletePerformance(performanceDAO.getPerformanceById(performanceID));

        return new ModelAndView("redirect:/");
    }
}
