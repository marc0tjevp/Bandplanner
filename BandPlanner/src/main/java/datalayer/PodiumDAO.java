package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "INSERT INTO `podium` (`podium_id`,`name) "
                    + "VALUES (?, ?)");
            statement.setString(1, p.getPodiumId().toString());
            statement.setString(2, p.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Podium getPodiumById(UUID id) {
        Podium p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `name`"
                    + "FROM `podium` WHERE `podium_id` = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");

                p = new Podium(name, id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return p;
    }

    @Override
    public Podium getPodiumByName(String n) {
        Podium p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `podium_id` "
                    + "FROM `podium` WHERE `name` = ?");
            statement.setString(1, n);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("podium_id"));

                p = new Podium(n, id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return p;
    }

    @Override
    public ArrayList<Podium> getAllPodia() {
        ArrayList<Podium> podia = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `podium_id`, `name` FROM `podium`");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("podium_id"));
                String name = resultSet.getString("name");

                Podium p = new Podium(name, id);
                podia.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return podia;
    }

    @Override
    public void updatePodium(Podium p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE `podium` SET `name` = ?, "
                    + " WHERE `podium_id` = ?");
            statement.setString(1, p.getName());
            statement.setString(2, p.getPodiumId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void deletePodium(Podium p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM `podium` WHERE `podium_id` = ?");
            statement.setString(1, p.getPodiumId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

}
