package controller;

import datalayer.PerformanceDAO;
import datalayer.PodiumDAO;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
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
@Controller("PodiumC")
public class PodiumController {

    PodiumDAO podiumDAO = PodiumDAO.getInstance();

    // Add Podium
    @RequestMapping(value = "/addPodium", method = RequestMethod.POST)
    public ModelAndView addPodium(HttpServletRequest request) {

        String name = request.getParameter("name");

        Podium p = new Podium(name);

        podiumDAO.createPodium(p);

        return new ModelAndView("redirect:/");
    }

    // Update Podium
    @RequestMapping(value = "/updatePodium", method = RequestMethod.POST)
    public ModelAndView updatePodium(HttpServletRequest request) {

        String id = request.getParameter("podiumId");
        String name = request.getParameter("name");

        Podium p = podiumDAO.getPodiumById(UUID.fromString(id));

        p.setName(name);

        podiumDAO.updatePodium(p);

        return new ModelAndView("redirect:/");
    }

    // Delete Podium
    @RequestMapping(value = "/deletePodium", method = RequestMethod.GET)
    public ModelAndView deletePodium(HttpServletRequest request) {

        UUID podiumID = UUID.fromString(request.getParameter("id"));
        podiumDAO.deletePodium(podiumDAO.getPodiumById(podiumID));

        return new ModelAndView("redirect:/");
    }

    // Get Podium Info
    @RequestMapping(value = "/viewPodium", method = RequestMethod.GET)
    public String viewArtist(HttpServletRequest request, ModelMap map) {

        PerformanceDAO performanceDAO = PerformanceDAO.getInstance();

        UUID podiumID = UUID.fromString(request.getParameter("id"));

        map.put("thisPodium", podiumDAO.getPodiumById(podiumID));
        map.put("performancesByPodium", performanceDAO.getPerformancesByPodium(podiumDAO.getPodiumById(podiumID)));

        return ("viewPodium");
    }

}
