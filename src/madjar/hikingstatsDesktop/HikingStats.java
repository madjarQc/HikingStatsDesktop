package madjar.hikingstatsDesktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import madjar.hikingstatsDesktop.model.AlexPoints3D;
import madjar.hikingstatsDesktop.model.Travel;
import madjar.hikingstatsDesktop.parsers.XmlParser;
import madjar.hikingstatsDesktop.parsers.XmlParserIO;
import org.joda.time.DateTime;
import org.xml.sax.SAXException;

public class HikingStats {
    

    public static final String MESSAGE_CSV_CREATE = "CSV FILE CREATED";
    public static final String MESSAGE_CSV_UPDATE = "CSV FILE UPDATED";
    public static final String MESSAGE_CSV_WRITE_ERROR = "WRITTING ERROR IN CSV";        
    public static final String KML_PARENT = "gx:Track";
    public static final String KML_CHILD_TIME = "when";
    public static final String KML_CHILD_COORDINATE = "gx:coord";

    public HikingStats() {

    }

    public static void main(String[] args) {
        
        DateTime date1 = new DateTime(System.currentTimeMillis());
        date1 = date1.minusDays(50);
        
        DateTime date2 = new DateTime();

        try {
            XmlParser parse = new XmlParser("doc.kml");

            Map<String, String> map;
            
            List<AlexPoints3D> points;

            map = parse.getParsedContent();

            //test if sorted
            Set keySet = map.keySet();
            ArrayList<AlexPoints3D> listOfPoints = new ArrayList<>();

            for (Iterator i = keySet.iterator(); i.hasNext();) {

                String key = (String) i.next();
                String value = (String) map.get(key);

                //System.out.println(key + " -> " + value);
                
                DateTime date = new DateTime(key);
                //System.out.println("date " + key);
                
                double x;
                double y;
                double z;
                
                String[] split = value.split(" ");
                
                x = Double.parseDouble(split[0]);
                y = Double.parseDouble(split[1]);
                z = Double.parseDouble(split[2]);
                
                //System.out.println("x - y - z " + x + " - " + y + " - " + z);
                
                AlexPoints3D point = new AlexPoints3D(date, x, y, z);
                listOfPoints.add(point);
                
                
                
            }
            
            //calculate distance with data
            Travel travel = new Travel(date1, date2, listOfPoints);
            System.out.println("distance travel -- " + travel.getDistance());
            System.out.println("duration travel -- " + travel.getDuration());

        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(HikingStats.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
