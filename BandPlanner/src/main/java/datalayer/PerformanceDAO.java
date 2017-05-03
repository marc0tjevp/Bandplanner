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
import java.sql.Date;
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
    
    private PerformanceDAO(){
        
    }
    
    public static PerformanceDAO getInstance(){
        if(instance == null){
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
            statement.setDate(2, p.getStarttime());
            statement.setDate(3, p.getEndtime());
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
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` FROM `performance`"
                    + " LEFT JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id`"
                    + " LEFT JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id`"
                    + " WHERE `performance_id` = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = resultSet.getDate("startdate");
                Date enddate = resultSet.getDate("enddate");

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
    public ArrayList<Performance> getPerformancesByPodium(Podium p) {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` FROM `podium` "
                    + "LEFT JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "LEFT JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "WHERE `podium`.`podium_id` = ?");
            statement.setString(1, p.getPodiumId().toString());
            ResultSet resultSet = statement.executeQuery();

            //TODO
            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = resultSet.getDate("startdate");
                Date enddate = resultSet.getDate("enddate");

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
    public ArrayList<Performance> getPerformancesByDay(Date d) {
        ArrayList<Performance> performances = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` FROM `podium` "
                    + "LEFT JOIN `performance` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "LEFT JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "WHERE `start_time` = ?");
            statement.setDate(1, d);
            ResultSet resultSet = statement.executeQuery();

            //TODO
            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = resultSet.getDate("startdate");
                Date enddate = resultSet.getDate("enddate");

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
            statement.setDate(1, p.getStarttime());
            statement.setDate(2, p.getEndtime());
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

    @Override
    public Performance getPreviousPerformance(String id) {
        Performance p = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `podium`.`p_name` FROM `performance`"
                    + " LEFT JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id`"
                    + " LEFT JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id`"
                    + " WHERE `end_time` < (SELECT `end_time` FROM `performance` WHERE `performance_id` = ?) ORDER BY `end_time` DESC LIMIT 1;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = resultSet.getDate("startdate");
                Date enddate = resultSet.getDate("enddate");

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
    public Performance getNextPerformance(String id) {
        Performance p = null;
        try {
            Connection conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT `performance_id`, `start_time`, `end_time`, `artist`.`a_name`, `artist`.`description`, `podium`.`p_name` FROM `performance` "
                    + "LEFT JOIN `artist` ON `performance`.`artist`=`artist`.`artist_id` "
                    + "LEFT JOIN `podium` ON `performance`.`podium`=`podium`.`podium_id` "
                    + "WHERE `start_time` > (SELECT `start_time` FROM `performance` WHERE `performance_id` = ?) ORDER BY `start_time` ASC LIMIT 1;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Performance values
                UUID performanceId = UUID.fromString(resultSet.getString("performance_id"));
                Date startdate = resultSet.getDate("startdate");
                Date enddate = resultSet.getDate("enddate");

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
            MysqlDAO.getInstance().closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
