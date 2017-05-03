/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

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
