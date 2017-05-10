/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.ArtistDAO;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import model.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maxim
 */
@Controller("ArtistC")
public class ArtistController {

    ArtistDAO artistDAO = ArtistDAO.getInstance();

    // Add Artist
    @RequestMapping(value = "/addArtist", method = RequestMethod.POST)
    public ModelAndView addArtist(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Artist a = new Artist(name, description);

        artistDAO.createArtist(a);

        return new ModelAndView("redirect:/");
    }

    // Delete Artist
    @RequestMapping(value = "/deleteArtist", method = RequestMethod.GET)
    public ModelAndView deleteArtist(HttpServletRequest request) {

        UUID artistID = UUID.fromString(request.getParameter("id"));
        artistDAO.deleteArtist(artistDAO.getArtistById(artistID));

        return new ModelAndView("redirect:/");
    }
    
    // Get Artist Info
    @RequestMapping(value = "/viewArtist", method = RequestMethod.GET)
    public String viewArtist(HttpServletRequest request, ModelMap map) {

        UUID artistID = UUID.fromString(request.getParameter("id"));

        map.put("thisArtist", artistDAO.getArtistById(artistID));

        return ("viewArtist");
    }

}
