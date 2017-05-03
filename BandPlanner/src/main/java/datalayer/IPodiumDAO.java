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
public interface IPodiumDAO {

    public void createPodium(Podium p);

    public Podium getPodiumById(UUID id);

    public Podium getPodiumByName(String n);

    public ArrayList<Podium> getAllPodia();

    public void updatePodium(Podium p);

    public void deletePodium(Podium p);

}
