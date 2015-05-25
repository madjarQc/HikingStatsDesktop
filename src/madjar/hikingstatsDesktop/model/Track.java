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
     * Construct single track from start to finish time with a list of point
     * with all complete parametre (no getX()==0)
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
        setAvgSp(calculateAvgSp(this.getDistance(), this.getDuration()));
        setAvgMovSp(calculateAvgMvSp());
        setMinAltitude(this.calculateMinAltitude());
        setMaxAltitude(this.calculateMaxAltitude());
        setGain(this.calculateGain());
        setPitch(this.calculatePitch(getMinAltitude(), getMaxAltitude()));

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

        return distanceInMeter;

    }

    @Override
    public double calculateDuration() {

        double durationInSec;

        double intervalInMillis = getFinishTime().getMillis() - getStartTime().getMillis();
        durationInSec = TimeUnit.MILLISECONDS.toSeconds((long) intervalInMillis);

        return durationInSec;
    }

    @Override
    public double calculateAvgMvSp() {

        double pAvgMvSp = 0;

        return pAvgMvSp;

    }

    @Override
    public double calculateMinAltitude() {

        double pMinAltitude = listOfPoints.get(0).getZ();
        double currentPoint = 0;

        for (AlexPoints3D point : listOfPoints) {

            currentPoint = point.getZ();
            if (currentPoint < pMinAltitude) {

                pMinAltitude = currentPoint;

            }

        }

        return pMinAltitude;

    }

    @Override
    public double calculateMaxAltitude() {

        double pMinAltitude = listOfPoints.get(0).getZ();
        double currentPoint = 0;

        for (AlexPoints3D point : listOfPoints) {

            currentPoint = point.getZ();
            if (currentPoint > pMinAltitude) {

                pMinAltitude = currentPoint;

            }

        }

        return pMinAltitude;

    }

    /**
     * Gain : vertical interval
     *
     * @return double, the sum of positive gain in the listOfPoint
     */
    @Override
    public double calculateGain() {

        double pGain = 0;

        for (int i = 0; i < listOfPoints.size() - 1; i++) {

            double a = listOfPoints.get(i).getZ();
            double b = listOfPoints.get(i + 1).getZ();

            //positive gain ?
            if (b - a > 0) {

                pGain += b - a;
            }
        }

        return pGain;

    }

}
