/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Artist;
import model.Performance;
import model.Podium;

/**
 *
 * @author maxim
 */
public class PerformanceDAO implements IPerformanceDAO {

    private static PerformanceDAO instance;

    private PerformanceDAO() {

    }

    public static PerformanceDAO getInstance() {
        if (instance == null) {
            instance = new PerformanceDAO();
        }
        return instance;
    }

    @Override
    public void createPerformance(Performance p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "INSERT INTO `performance` (`performance_id`,`start_time`,`end_time`,`artist`,`podium`) "
                    + "VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, p.getPerformanceId().toString());
            statement.setTimestamp(2, p.getStartTimestamp());
            statement.setTimestamp(3, p.getEndTimestamp());
            statement.setString(4, p.getArtist().getArtistId().toString());
            statement.setString(5, p.getPodium().getPodiumId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Performance getPerformanceById(UUID id) {
        Performance p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` , `podium`.`podium_id` FROM `performance`"
                    + " INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id`"
                    + " INNER JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id`"
                    + " WHERE `performance_id` = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                p = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");

                Artist a = new Artist(artistname);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");

                Podium podium = new Podium(podiumname, podiumId);

                p.setArtist(a);
                p.setPodium(podium);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return p;
    }

    @Override
    public ArrayList<Performance> getPerformancesByArtist(Artist a) {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `podium`.`p_name` , `podium`.`podium_id` FROM `podium` "
                    + "INNER JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "WHERE `artist`.`artist_id` = ? ORDER BY `start_time` DESC;");
            statement.setString(1, a.getArtistId().toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                Performance perf = new Performance(performanceId, startdate, enddate);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");
                Podium podium = new Podium(podiumname, podiumId);

                perf.setArtist(a);
                perf.setPodium(podium);
                performances.add(perf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return performances;
    }

    @Override
    public ArrayList<Performance> getPerformancesByPodium(Podium p) {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` , `podium`.`podium_id` FROM `podium` "
                    + "INNER JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "WHERE `podium`.`podium_id` = ? ORDER BY `start_time` DESC;");
            statement.setString(1, p.getPodiumId().toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                Performance perf = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");
                Artist a = new Artist(artistname);

                perf.setArtist(a);
                perf.setPodium(p);
                performances.add(perf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return performances;
    }

    @Override
    public ArrayList<Performance> getPerformancesByDay(Date d) {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` , `podium`.`podium_id` FROM `podium` "
                    + "INNER JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "WHERE `start_time` = ? ORDER BY `start_time` DESC;");
            statement.setTimestamp(1, new java.sql.Timestamp(d.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                Performance perf = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");
                Artist a = new Artist(artistname);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");
                Podium podium = new Podium(podiumname, podiumId);

                perf.setArtist(a);
                perf.setPodium(podium);
                performances.add(perf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return performances;
    }

    @Override
    public void updatePerformance(Performance p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE `performance` SET `start_time` = ?, "
                    + "`end_time` = ?, `artist` = ? , `podium` = ? "
                    + "WHERE `performance_id` = ?");
            statement.setTimestamp(1, p.getStartTimestamp());
            statement.setTimestamp(2, p.getEndTimestamp());
            statement.setString(3, p.getArtist().getArtistId().toString());
            statement.setString(4, p.getPodium().getPodiumId().toString());
            statement.setString(5, p.getPerformanceId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void deletePerformance(Performance p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM `performance` WHERE `performance_id` = ?");
            statement.setString(1, p.getPerformanceId().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    //Get previous performance by getting the first one where the end time is earlier than the current end time
    @Override
    public Performance getPreviousPerformance(String id) {
        Performance p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` , `podium`.`podium_id` FROM `performance`"
                    + " INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id`"
                    + " INNER JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id`"
                    + " WHERE `end_time` < (SELECT `end_time` FROM `performance` WHERE `performance_id` = ?) ORDER BY `end_time` DESC LIMIT 1;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                p = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");

                Artist a = new Artist(artistname);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");

                Podium podium = new Podium(podiumname, podiumId);

                p.setArtist(a);
                p.setPodium(podium);
            }
            if (p != null) {
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return getPerformanceById(UUID.fromString(id));
    }

    //Get the next performance by getting the first one where the start time is larger than the current start time
    @Override
    public Performance getNextPerformance(String id) {
        Performance p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `artist`.`description`, `podium`.`p_name` , `podium`.`podium_id` FROM `performance` "
                    + "INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "INNER JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "WHERE `start_time` > (SELECT `start_time` FROM `performance` WHERE `performance_id` = ?) ORDER BY `start_time` ASC LIMIT 1;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                p = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");

                Artist a = new Artist(artistname);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");

                Podium podium = new Podium(podiumname, podiumId);

                p.setArtist(a);
                p.setPodium(podium);
            }
            if (p != null) {
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return getPerformanceById(UUID.fromString(id));
    }

    //Get all performances ordered by start time
    @Override
    public ArrayList<Performance> getAllPerformances() {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` , `podium`.`podium_id` FROM `podium` "
                    + "INNER JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "INNER JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` ORDER BY `start_time` DESC;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = new Date(resultSet.getTimestamp("start_time").getTime());
                Date enddate = new Date(resultSet.getTimestamp("end_time").getTime());

                Performance perf = new Performance(performanceId, startdate, enddate);

                //Artist values
                String artistname = resultSet.getString("a_name");
                Artist a = new Artist(artistname);

                //Podium values
                UUID podiumId = UUID.fromString(resultSet.getString("podium_id"));
                String podiumname = resultSet.getString("p_name");
                Podium podium = new Podium(podiumname, podiumId);

                perf.setArtist(a);
                perf.setPodium(podium);
                performances.add(perf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return performances;
    }

}
