package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author maxim
 */
public class Performance {

    private UUID performanceId;
    private Date starttime;
    private Date endtime;
    private Artist artist;
    private Podium podium;

    public Performance(Podium podium, Artist artist, Date starttime, Date endtime) {
        this.podium = podium;
        this.artist = artist;
        this.starttime = starttime;
        this.endtime = endtime;
        performanceId = UUID.randomUUID();
    }

    public Performance(UUID id, Podium podium, Artist artist, Date starttime, Date endtime) {
        this.podium = podium;
        this.artist = artist;
        this.starttime = starttime;
        this.endtime = endtime;
        performanceId = id;
    }

    public Performance(UUID id, Date starttime, Date endtime) {
        this.starttime = starttime;
        this.endtime = endtime;
        performanceId = id;
    }

    public Performance() {
    }

    public UUID getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(UUID performanceId) {
        this.performanceId = performanceId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Podium getPodium() {
        return podium;
    }

    public void setPodium(Podium podium) {
        this.podium = podium;
    }
    
    //Convert Java util date starttime to sql Timestamp
    public Timestamp getStartTimestamp(){
        return new Timestamp(starttime.getTime());
    }
    
    //Convert Java util date enddtime to sql Timestamp
    public Timestamp getEndTimestamp(){
        return new Timestamp(endtime.getTime());
    }

}
