/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.ArtistDAO;
import datalayer.PerformanceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author maxim
 */
@Controller
public class DefaultController {

    ArtistDAO artistDAO = ArtistDAO.getInstance();
    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(ModelMap map) {

        map.put("getAllArtists", artistDAO.getAllArtists());
        map.put("getAllPerformances", performanceDAO.getAllPerformances());

        return "index";
    }

}
