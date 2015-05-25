package madjar.hikingstatsDesktop.database;

import android.provider.BaseColumns;

public final class HikingStatsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public HikingStatsContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class HikingStatsPointsEntries implements BaseColumns {

        public static final String TABLE_NAME = "points_poi";
        public static final String COLUMN_NAME_ENTRY_ID = "poi_id";
        public static final String COLUMN_NAME_TIMESTAMP = "poi_timeStamp";
        public static final String COLUMN_NAME_COORDINATE = "poi_coordinate";

    }

    public static abstract class HikingStatsTracksEntries implements BaseColumns {

        public static final String TABLE_NAME = "track_tra";
        public static final String COLUMN_NAME_ENTRY_ID = "tra_id";
        public static final String COLUMN_NAME_NAME = "tra_name";
        public static final String COLUMN_NAME_STARTDATE = "tra_startDate";
        public static final String COLUMN_NAME_FINISHDATE = "tra_finishDate";

    }
}
