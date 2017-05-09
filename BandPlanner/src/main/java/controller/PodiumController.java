/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.PodiumDAO;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maxim
 */

@Controller
public class PodiumController {
    
    PodiumDAO podiumDAO = PodiumDAO.getInstance();

    // Delete Podium
    @RequestMapping(value = "/deletePodium", method = RequestMethod.GET)
    public ModelAndView deletePodium(HttpServletRequest request) {

        UUID podiumID = UUID.fromString(request.getParameter("id"));
        podiumDAO.deletePodium(podiumDAO.getPodiumById(podiumID));

        return new ModelAndView("redirect:/");
    }
    
}
