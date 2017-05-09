/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datalayer.PerformanceDAO;
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
public class PerformanceController {

    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();

    // Delete Performance
    @RequestMapping(value = "/deletePerformance", method = RequestMethod.GET)
    public ModelAndView deletePerformance(HttpServletRequest request) {

        UUID performanceID = UUID.fromString(request.getParameter("id"));
        performanceDAO.deletePerformance(performanceDAO.getPerformanceById(performanceID));

        return new ModelAndView("redirect:/");
    }
}
