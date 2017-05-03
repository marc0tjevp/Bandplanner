/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.util.ArrayList;
import java.util.UUID;
import model.Podium;

/**
 *
 * @author maxim
 */
public class PodiumDAO implements IPodiumDAO {

    private static PodiumDAO instance;

    private PodiumDAO() {

    }

    public static PodiumDAO getInstance() {
        if (instance == null) {
            instance = new PodiumDAO();
        }
        return instance;
    }

    @Override
    public void createPodium(Podium p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Podium getPodiumById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Podium getPodiumByName(String n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Podium> getAllPodia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePodium(Podium p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePodium(Podium p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
