package datalayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import model.Performance;
import model.Podium;

/**
 *
 * @author maxim
 */
public interface IPerformanceDAO {

    public void createPerformance(Performance p);

    public Performance getPerformanceById(UUID id);

    public ArrayList<Performance> getPerformancesByPodium(Podium p);

    public ArrayList<Performance> getPerformancesByDay(Date d);
    
    public ArrayList<Performance> getAllPerformances();

    public void updatePerformance(Performance p);

    public void deletePerformance(Performance p);
    
    public Performance getPreviousPerformance(String id);
    
    public Performance getNextPerformance(String id);

}
