package datalayerinterfaces;

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
