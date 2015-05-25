package madjar.hikingstatsDesktop.model;

import javafx.geometry.Point3D;
import org.joda.time.DateTime;

/**
 *
 * @author Alex
 */
public class AlexPoints3D extends Point3D {

    DateTime date;

    /**
     * Normal constructor
     * @param date
     * @param x
     * @param y
     * @param z 
     */
    public AlexPoints3D(DateTime date, double x, double y, double z) {
        super(x, y, z);
        this.date = date;
    }

    /**
     * Constructor used to mark the start of a track
     *
     * @param date, date of the first point - 1
     */
    public AlexPoints3D(DateTime date) {
        super(0, 0, 0);
        this.date = new DateTime(date.getMillis() - 1);

    }

    public DateTime getDate() {

        return this.date;
    }

}
