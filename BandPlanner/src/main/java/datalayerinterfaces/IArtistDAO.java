package datalayerinterfaces;

import java.util.ArrayList;
import java.util.UUID;
import model.Artist;

/**
 *
 * @author maxim
 */
public interface IArtistDAO {

    public void createArtist(Artist a);

    public Artist getArtistById(UUID id);

    public Artist getArtistByName(String n);

    public ArrayList<Artist> getAllArtists();

    public void updateArtist(Artist a);

    public void deleteArtist(Artist a);

}
