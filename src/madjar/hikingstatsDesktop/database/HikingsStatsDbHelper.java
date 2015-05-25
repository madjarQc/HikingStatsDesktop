package madjar.hikingstatsDesktop.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Alex
 */
public class HikingsStatsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HikingsStats.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES_POINTS
            = "CREATE TABLE "
            + HikingStatsContract.HikingStatsPointsEntries.TABLE_NAME
            + " ("
            + HikingStatsContract.HikingStatsPointsEntries._ID + " INTEGER PRIMARY KEY,"
            + HikingStatsContract.HikingStatsPointsEntries.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
            + HikingStatsContract.HikingStatsPointsEntries.COLUMN_NAME_TIMESTAMP + TEXT_TYPE + COMMA_SEP
            + HikingStatsContract.HikingStatsPointsEntries.COLUMN_NAME_COORDINATE + TEXT_TYPE + COMMA_SEP
            + " )";

    private static final String SQL_CREATE_ENTRIES_TRACKS
            = "CREATE TABLE "
            + HikingStatsContract.HikingStatsTracksEntries.TABLE_NAME
            + " ("
            + HikingStatsContract.HikingStatsTracksEntries._ID + " INTEGER PRIMARY KEY,"
            + HikingStatsContract.HikingStatsTracksEntries.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
            + HikingStatsContract.HikingStatsTracksEntries.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
            + HikingStatsContract.HikingStatsTracksEntries.COLUMN_NAME_STARTDATE + TEXT_TYPE + COMMA_SEP
            + HikingStatsContract.HikingStatsTracksEntries.COLUMN_NAME_FINISHDATE + TEXT_TYPE + COMMA_SEP
            + " )";

    private static final String SQL_DELETE_ENTRIES_POINTS
            = "DROP TABLE IF EXISTS " + HikingStatsContract.HikingStatsPointsEntries.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_TRACKS
            = "DROP TABLE IF EXISTS " + HikingStatsContract.HikingStatsTracksEntries.TABLE_NAME;

    public HikingsStatsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_POINTS);
        db.execSQL(SQL_CREATE_ENTRIES_TRACKS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
