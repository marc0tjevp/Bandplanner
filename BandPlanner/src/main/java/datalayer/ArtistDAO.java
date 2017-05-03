package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Artist;

/**
 *
 * @author maxim
 */
public class ArtistDAO implements IArtistDAO {

    private static ArtistDAO instance;

    private ArtistDAO() {

    }

    public static ArtistDAO getInstance() {
        if (instance == null) {
            instance = new ArtistDAO();
        }
        return instance;
    }

    @Override
    public void createArtist(Artist a) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "INSERT INTO `artist` (`artist_id`,`a_name`,`description`) "
                    + "VALUES (?, ?, ?)");
            statement.setString(1, a.getArtistId().toString());
            statement.setString(2, a.getName());
            statement.setString(3, a.getDescription());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Artist getArtistById(UUID id) {
        Artist a = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `a_name`, `description` "
                    + "FROM `artist` WHERE `artist_id` = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String artistname = resultSet.getString("a_name");
                String description = resultSet.getString("description");

                a = new Artist(artistname, description, id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return a;
    }

    @Override
    public Artist getArtistByName(String n) {
        Artist a = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `artist_id`, `description`"
                    + " FROM `artist` WHERE `a_name` = ?");
            statement.setString(1, n);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("artist_id"));
                String description = resultSet.getString("description");

                a = new Artist(n, description, id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return a;
    }

    @Override
    public ArrayList<Artist> getAllArtists() {
        ArrayList<Artist> artists = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `artist_id`, `a_name`, `description` FROM `artist`");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("artist_id"));
                String artistname = resultSet.getString("a_name");
                String description = resultSet.getString("description");

                Artist a = new Artist(artistname, description, id);
                artists.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return artists;
    }

    @Override
    public void updateArtist(Artist a) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE `artist` SET `a_name` = ?, "
                    + "`description` = ? WHERE `artist_id` = ?");
            statement.setString(1, a.getName());
            statement.setString(2, a.getDescription());
            statement.setString(3, a.getArtistId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void deleteArtist(Artist a) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM `artist` WHERE `artist_id` = ?");
            statement.setString(1, a.getArtistId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

}
