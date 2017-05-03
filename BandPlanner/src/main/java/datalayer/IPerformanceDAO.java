/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.util.ArrayList;
import java.sql.Date;
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

    public ArrayList<Performance> getPerformancesByDay(Date d); //util date, maybe sql date?

    public void updatePerformance(Performance p);

    public void deletePerformance(Performance p);
    
    public Performance getPreviousPerformance(String id);
    
    public Performance getNextPerformance(String id);

}
