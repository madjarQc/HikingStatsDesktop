package madjar.hikingstatsDesktop.model;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Created by Alex on 20-05-15.
 */
public class Travel {

    private ArrayList<Track> trackArray;
    private String name;
    private DateTime startTime;
    private DateTime finishTime;
    private double distance;
    private double duration;
    private double avgSp;
    private double avgMovSp;
    private double gain;
    private double minAltitude;
    private double maxAltitude;

    public Travel() {

    }

    public Travel(DateTime startTime, DateTime finishTime, ArrayList<AlexPoints3D> blockOfPoints) {
        trackArray = new ArrayList<>();
        this.startTime = startTime;
        this.finishTime = finishTime;

        List<AlexPoints3D> pointsForTrack = new ArrayList<>();
        DateTime lowTime = new DateTime();
        DateTime highTime = new DateTime(lowTime.plus(1));

        //go throught all the rows and cut it in multiple tracks
        for (int i = 0; i < blockOfPoints.size(); i++) {

            //start new track if one of the param is 0
            if (blockOfPoints.get(i).getX() == 0) {
                this.trackArray.add(new Track(lowTime, highTime, pointsForTrack));
                pointsForTrack = new ArrayList<>();
                lowTime = new DateTime();
                highTime = new DateTime(lowTime.plus(1));

                //add the current points to the list to send
            } else {

                if (blockOfPoints.get(i).getDate().isBefore(lowTime)) {
                    lowTime = blockOfPoints.get(i).getDate();
                }

                highTime = blockOfPoints.get(i).getDate();
                pointsForTrack.add(blockOfPoints.get(i));

            }

        }

        //add the last track
        this.trackArray.add(new Track(lowTime, highTime, pointsForTrack));
        this.trackArray = this.getTrackArray();

        setDistance(calculateDistance());
        this.distance = this.getDistance();

        setDuration(calculateDuration());
        this.duration = this.getDuration();

        setAvgSp(calculateAvgSp(getDistance(), getDuration()));
        this.avgSp = this.getAvgSp();

        setAvgMovSp(calculateAvgMvSp());
        this.avgMovSp = this.getAvgMovSp();
        this.minAltitude = this.getMinAltitude();
        this.maxAltitude = this.getMaxAltitude();

        setGain(calculateGain(getMinAltitude(), getMaxAltitude()));
        this.gain = this.getGain();
    }

    public ArrayList getTrackArray() {
        return trackArray;
    }

    public void setTrackArray(ArrayList trackArray) {
        this.trackArray = trackArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(DateTime finishTime) {
        this.finishTime = finishTime;
    }

    public double getDistance() {

        return distance;
    }

    public void setDistance(double distance) {

        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getAvgSp() {
        return avgSp;
    }

    public void setAvgSp(double avgSp) {
        this.avgSp = avgSp;
    }

    public double getAvgMovSp() {
        return avgMovSp;
    }

    public void setAvgMovSp(double avgMovSp) {
        this.avgMovSp = avgMovSp;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getMinAltitude() {
        return minAltitude;
    }

    public void setMinAltitude(double minAltitude) {
        this.minAltitude = minAltitude;
    }

    /**
     * TODO
     *
     * @return
     */
    public double getMaxAltitude() {
        return maxAltitude;
    }

    /**
     * TODO
     *
     * @param maxAltitude
     */
    public void setMaxAltitude(double maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public double calculateDistance() {

        double distanceInMeter = 0;
        for (Track trackArray1 : trackArray) {
            distanceInMeter += trackArray1.getDistance();
        }

        return distanceInMeter;

    }

    public double calculateDuration() {

        double durationInSec = 0;
        for (Track trackArray1 : trackArray) {
            durationInSec += trackArray1.getDuration();
        }

        return durationInSec;
    }

    public double calculateAvgSp(double pDistance, double pDuration) {
        System.out.println("calc dist : " + pDistance);
        System.out.println("calc dura : " + pDuration);
        return pDistance / pDuration;
    }

    /**
     * Calculate the average moving speed TODO
     *
     * @return double, average moving speed of the travel
     */
    public double calculateAvgMvSp() {

        return 0;

    }

    public double calculateGain(double pMinAltitude, double pMaxAltitude) {

        return pMaxAltitude - pMinAltitude;

    }

}
