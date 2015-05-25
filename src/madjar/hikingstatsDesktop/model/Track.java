package madjar.hikingstatsDesktop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

/**
 * Created by Alex on 20-05-15.
 */
public class Track extends Travel {

    private List<AlexPoints3D> listOfPoints = new ArrayList<>();

    /**
     * Construct single track from start to finish time with a list of point with all complete parametre (no getX()==0)
     *
     * @param startTime
     * @param finishTime
     * @param listOfPoints
     */
    public Track(DateTime startTime, DateTime finishTime, List listOfPoints) {
        setStartTime(startTime);
        setFinishTime(finishTime);
        this.listOfPoints = listOfPoints;
        setDistance(this.calculateDistance());
        setDuration(this.calculateDuration());
    }

    /**
     * TODO Construct a track from its name in the DB and all the points in it.
     * Start and finish time will be the one of the track.
     *
     * @param name, the name of the Track to find in DB
     */
    public Track(String name) {

        //SQL query to get the start and finish time of the track
        setName(name);
        this.listOfPoints = getListOfPoints(name);

    }

    /**
     * To get the list of point from name
     *
     * @param name
     * @return
     */
    public List getListOfPoints(String name) {

        //SQL Query from name to get a list of point
        return listOfPoints;
    }

    public void setListOfPoints(List listOfPoints) {
        this.listOfPoints = listOfPoints;
    }

    @Override
    public double calculateDistance() {

        double distanceInMeter = 0;

        for (int i = 0; i < listOfPoints.size() - 1; i++) {

            distanceInMeter += listOfPoints.get(i).distance(listOfPoints.get(i + 1));

        }

        distanceInMeter = distanceInMeter * 10;

        return distanceInMeter;

    }

    @Override
    public double calculateDuration() {

        double durationInSec;

        double intervalInMillis = getFinishTime().getMillis() - getStartTime().getMillis();
        durationInSec = TimeUnit.MILLISECONDS.toSeconds((long) intervalInMillis);

        return durationInSec;
    }

    public double calculateAvgMvSP(List listOfPoints) {

        return 1;
    }

}
