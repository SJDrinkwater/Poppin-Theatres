package com.example.application.poppintheatres;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_BIRTHDAY = "birthday";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME + " TEXT NOT NULL, "
                    + COLUMN_PASSWORD + " TEXT NOT NULL, "
                    + COLUMN_EMAIL + " TEXT, "
                    + COLUMN_PHONE + " TEXT, "
                    + COLUMN_ADDRESS + " TEXT, "
                    + COLUMN_BIRTHDAY + " TEXT"
                    + ")";

    private static final String TABLE_PERFORMANCES = "performances";
    private static final String COLUMN_PERFORMANCES_ID = "performance_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";

    private static final String CREATE_TABLE_PERFORMANCES =
            "CREATE TABLE " + TABLE_PERFORMANCES + "("
                    + COLUMN_PERFORMANCES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_LOCATION + " TEXT"
                    + ")";

    private static final String TABLE_SEATS = "seats";
    private static final String COLUMN_SEAT_ID = "seat_id";
    private static final String COLUMN_SEAT_NAME = "seat_name";
    private static final String COLUMN_SEAT_AVAILABLE = "seat_available";
    private static final String COLUMN_SEAT_PRICE = "seat_price";

    private static final String CREATE_TABLE_SEATS =
            "CREATE TABLE " + TABLE_SEATS + "("
                    + COLUMN_SEAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PERFORMANCES_ID + " INTEGER,"
                    + COLUMN_SEAT_NAME + " TEXT,"
                    + COLUMN_SEAT_AVAILABLE + " INTEGER,"
                    + COLUMN_SEAT_PRICE + " INTEGER,"
                    + "FOREIGN KEY (" + COLUMN_PERFORMANCES_ID + ") REFERENCES " + TABLE_PERFORMANCES + "(" + COLUMN_PERFORMANCES_ID + ")"
                    + ")";

    private static final String TABLE_ACCESSIBILITY = "accessibility";
    private static final String COLUMN_ACCESSIBILITY_ID = "accessibility_id";
    private static final String COLUMN_WHEELCHAIR_ACCESSIBLE = "wheelchair";
    private static final String COLUMN_STAIRS = "stairs";
    private static final String COLUMN_FLASHING_LIGHTS = "flashing_lights";
    private static final String CREATE_TABLE_ACCESSIBILITY =
            "CREATE TABLE " + TABLE_ACCESSIBILITY + "("
                    + COLUMN_ACCESSIBILITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_PERFORMANCES_ID + " INTEGER NOT NULL, "
                    + COLUMN_WHEELCHAIR_ACCESSIBLE + " INTEGER NOT NULL, "
                    + COLUMN_STAIRS + " INTEGER NOT NULL, "
                    + COLUMN_FLASHING_LIGHTS + " INTEGER NOT NULL, "
                    + "FOREIGN KEY (" + COLUMN_PERFORMANCES_ID + ") REFERENCES " + TABLE_PERFORMANCES + "(" + COLUMN_PERFORMANCES_ID + ")"
                    + ")";

    private static final String TABLE_BOOKINGS = "bookings";
    private static final String COLUMN_BOOKING_ID = "booking_id";
    private static final String COLUMN_BOOKING_QUANTITY = "quantity";
    private static final String COLUMN_BOOKING_NOTES = "notes";
    private static final String COLUMN_CONFIRMATION = "confirmationCode";

    private static final String CREATE_BOOKINGS_TABLE =
            "CREATE TABLE " + TABLE_BOOKINGS + " ("
                    + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_ID + " INTEGER, "
                    + COLUMN_PERFORMANCES_ID + " INTEGER, "
                    + COLUMN_SEAT_ID + " INTEGER, "
                    + COLUMN_BOOKING_NOTES + " TEXT, "
                    + COLUMN_CONFIRMATION + " TEXT, "
                    + COLUMN_BOOKING_QUANTITY + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PERFORMANCES);
        db.execSQL(CREATE_TABLE_SEATS);
        db.execSQL(CREATE_TABLE_ACCESSIBILITY);
        db.execSQL(CREATE_BOOKINGS_TABLE);

        String user = "INSERT INTO " + TABLE_USERS + " (" +
                COLUMN_USERNAME + ", " +
                COLUMN_PASSWORD + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_BIRTHDAY + ") VALUES ('" +
                "sam.drinkwater', " +
                "'Burgers1234!', " +
                "'sjdrinkwater@outlook.com', " +
                "'07703187284', " +
                "'8 Hawthorn Street', " +
                "'03/05/2000');";

        db.execSQL(user);

        ////////////////////////////////////////////////////////////////////////////////////////////

        String MerchantPerformance = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "The Merchant of Venice by William Shakespeare', " +
                "'20:00 Sunday April 23 2023', " +
                "'Merchant Adventurers Hall, York')";

        db.execSQL(MerchantPerformance);

        String MerchantSeats = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "1', " +
                "'Seated', " +
                "'17', " +
                "'8')";

        db.execSQL(MerchantSeats);

        String MerchantAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "1', " +
                "'1', " +
                "'1', " +
                "'0', " +
                "'0')";

        db.execSQL(MerchantAccessibility);

////////////////////////////////////////////////////////////////////////////////////////////

        String HamletPerformances = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "Hamlet by William Shakespeare', " +
                "'18:00 19 May 2023', " +
                "'Cliffords Tower, York');";

        db.execSQL(HamletPerformances);

        String HamletSeatsStanding = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "2', " +
                "'Seated', " +
                "'8', " +
                "'8');";

        db.execSQL(HamletSeatsStanding);

        String HamletSeatsSeated = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "2', " +
                "'Standing', " +
                "'12', " +
                "'7');";

        db.execSQL(HamletSeatsSeated);

        String HamletAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "2', " +
                "'2', " +
                "'0', " +
                "'1', " +
                "'1')";

        db.execSQL(HamletAccessibility);

////////////////////////////////////////////////////////////////////////////////////////////

        String Midsummer = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "A Midsummer Night''s Dream by William Shakespeare', " +
                "'19:00 Saturday 3 June 2023', " +
                "'Dean''s Park, York');";

        db.execSQL(Midsummer);

        String MidsummerSeatsOnStage = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "3', " +
                "'On Stage', " +
                "'11', " +
                "'4');";

        db.execSQL(MidsummerSeatsOnStage);

        String MidsummerSeatsGrass = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "3', " +
                "'Grass', " +
                "'29', " +
                "'4');";

        db.execSQL(MidsummerSeatsGrass);

        String MidsummerAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "3', " +
                "'3', " +
                "'1', " +
                "'0', " +
                "'1')";

        db.execSQL(MidsummerAccessibility);


////////////////////////////////////////////////////////////////////////////////////////////


        String Oedipus = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "Oedipus the King by Sophocles', " +
                "'20:00 Fri 28 July', " +
                "'St Marys Abbey, Museum Gardens, York');";

        db.execSQL(Oedipus);

        String OedipusSeatsSeated = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "4', " +
                "'Seated', " +
                "'5', " +
                "'9');";

        db.execSQL(OedipusSeatsSeated);

        String OedipusSeatsGrass = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "4', " +
                "'Standing', " +
                "'12', " +
                "'7');";

        db.execSQL(OedipusSeatsGrass);

        String OedipusAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "4', " +
                "'4', " +
                "'1', " +
                "'0', " +
                "'0')";

        db.execSQL(OedipusAccessibility);


///////////////////////////////////////////////////////////////////////////////////////////


        String Tempest = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "The Tempest by William Shakespeare', " +
                "'14:00 Saturday 19 August', " +
                "'Milleneum Bridge, York');";

        db.execSQL(Tempest);

        String TempestSeatsBoatA = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "5', " +
                "'Boat A', " +
                "'6', " +
                "'9');";

        db.execSQL(TempestSeatsBoatA);

        String TempestSeatsBoatB = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "5', " +
                "'Boat B', " +
                "'4', " +
                "'9');";

        db.execSQL(TempestSeatsBoatB);

        String TempestSeatsRiverbank = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "5', " +
                "'Riverbank', " +
                "'10', " +
                "'7');";

        db.execSQL(TempestSeatsRiverbank);

        String TempestAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "5', " +
                "'5', " +
                "'1', " +
                "'0', " +
                "'0')";

        db.execSQL(TempestAccessibility);

///////////////////////////////////////////////////////////////////////////////////////////


        String Antigone = "INSERT INTO " + TABLE_PERFORMANCES + " (" +
                COLUMN_TITLE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_LOCATION + ") VALUES ('" +
                "Antigone by Sophocles', " +
                "'21:00 Thursday 20 September', " +
                "'Crypt, York Minster, York');";

        db.execSQL(Antigone);

        String AntigoneSeatsInnerCircle = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "6', " +
                "'Inner Circle', " +
                "'5', " +
                "'16');";

        db.execSQL(AntigoneSeatsInnerCircle);

        String AntigoneSeatsOuterCircle = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "6', " +
                "'Outer Circle', " +
                "'8', " +
                "'13');";

        db.execSQL(AntigoneSeatsOuterCircle);

        String AntigoneSeatsStanding = "INSERT INTO " + TABLE_SEATS + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_SEAT_NAME + ", " +
                COLUMN_SEAT_AVAILABLE + ", " +
                COLUMN_SEAT_PRICE + ") VALUES ('" +
                "6', " +
                "'Standing', " +
                "'10', " +
                "'10');";

        db.execSQL(AntigoneSeatsStanding);

        String AntigoneAccessibility = "INSERT INTO " + TABLE_ACCESSIBILITY + " (" +
                COLUMN_PERFORMANCES_ID + ", " +
                COLUMN_ACCESSIBILITY_ID + ", " +
                COLUMN_WHEELCHAIR_ACCESSIBLE + ", " +
                COLUMN_STAIRS + ", " +
                COLUMN_FLASHING_LIGHTS + ") VALUES ('" +
                "6', " +
                "'6', " +
                "'0', " +
                "'1', " +
                "'0')";

        db.execSQL(AntigoneAccessibility);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFORMANCES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESSIBILITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    //add data to user table when a user registers
    public long addUser(String username, String password, String email, String phone, String address, String birthday) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_BIRTHDAY, birthday);
        return db.insert(TABLE_USERS, null, contentValues);
    }

    //checks if user account has the same name when registering
    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }


    // compare username with password, if verified grab the users id
    public int verifyUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =? AND " + COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
            cursor.close();
            db.close();
            return userId;
        } else {
            return -1;
        }
    }

    public List<PerformanceData> getAllPerformances() {
        List<PerformanceData> performanceList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        // Select all performances and join with seats and accessibility tables
        String SELECT_ALL = "SELECT p.*, SUM(s." + COLUMN_SEAT_AVAILABLE + ") as total_seats, MIN(s."
                + COLUMN_SEAT_PRICE + ") as min_price, a." + COLUMN_WHEELCHAIR_ACCESSIBLE + ", a."
                + COLUMN_STAIRS + ", a." + COLUMN_FLASHING_LIGHTS + " FROM " + TABLE_PERFORMANCES
                + " p LEFT JOIN " + TABLE_SEATS + " s ON p." + COLUMN_PERFORMANCES_ID + " = s."
                + COLUMN_PERFORMANCES_ID + " LEFT JOIN " + TABLE_ACCESSIBILITY + " a ON p."
                + COLUMN_PERFORMANCES_ID + " = a." + COLUMN_PERFORMANCES_ID + " GROUP BY p."
                + COLUMN_PERFORMANCES_ID;

        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") PerformanceRecords performance = new PerformanceRecords(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
                );

                @SuppressLint("Range") SeatRecords seat = new SeatRecords(
                        -1,
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                        "seat_name",
                        cursor.getInt(cursor.getColumnIndex("total_seats")),
                        cursor.getString(cursor.getColumnIndex("min_price"))
                );

                @SuppressLint("Range") AccessibilityRecords accessibility = new AccessibilityRecords(
                        -1,
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_WHEELCHAIR_ACCESSIBLE)) != 0,
                        cursor.getInt(cursor.getColumnIndex(COLUMN_STAIRS)) != 0,
                        cursor.getInt(cursor.getColumnIndex(COLUMN_FLASHING_LIGHTS)) != 0
                );

                performanceList.add(new PerformanceData(performance, seat, accessibility));
            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();

        return performanceList;
    }

    @SuppressLint("Range")
    public void updatePerformanceSeatsAvailable(int performanceId, int seatTypeId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();

        // Get the current seats available for the specified seat type
        String[] projection = {COLUMN_SEAT_AVAILABLE};
        String selection = COLUMN_PERFORMANCES_ID + "=? AND " + COLUMN_SEAT_ID + "=?";
        String[] selectionArgs = {String.valueOf(performanceId), String.valueOf(seatTypeId)};
        Cursor cursor = db.query(TABLE_SEATS, projection, selection, selectionArgs, null, null, null);
        int currentSeatsAvailable = 0;
        try {
            if (cursor.moveToFirst()) {
                currentSeatsAvailable = cursor.getInt(cursor.getColumnIndex(COLUMN_SEAT_AVAILABLE));
            }
        }  finally {
            cursor.close();
        }

        // Update the seats available for the specified seat type
        ContentValues values = new ContentValues();
        values.put(COLUMN_SEAT_AVAILABLE, Math.max(currentSeatsAvailable - quantity, 0)); // ensure the result is at least 0
        String whereClause = COLUMN_PERFORMANCES_ID + "=? AND " + COLUMN_SEAT_ID + "=?";
        String[] whereArgs = {String.valueOf(performanceId), String.valueOf(seatTypeId)};
        int rowsUpdated = db.update(TABLE_SEATS, values, whereClause, whereArgs);


        db.close();
    }

    public List<SeatRecords> getSeatsForPerformance(int performanceId) {
        List<SeatRecords> seatRecordsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {COLUMN_SEAT_ID, COLUMN_PERFORMANCES_ID, COLUMN_SEAT_NAME, COLUMN_SEAT_AVAILABLE, COLUMN_SEAT_PRICE};
        String selection = COLUMN_PERFORMANCES_ID + "=?";
        String[] selectionArgs = {String.valueOf(performanceId)};
        Cursor cursor = db.query(TABLE_SEATS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_SEAT_ID));
                @SuppressLint("Range") String seatName = cursor.getString(cursor.getColumnIndex(COLUMN_SEAT_NAME));
                @SuppressLint("Range") int seatsAvailable = cursor.getInt(cursor.getColumnIndex(COLUMN_SEAT_AVAILABLE));
                @SuppressLint("Range") String seatPrice = cursor.getString(cursor.getColumnIndex(COLUMN_SEAT_PRICE));

                SeatRecords seatRecord = new SeatRecords(id, performanceId, seatName, seatsAvailable, seatPrice);
                seatRecordsList.add(seatRecord);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return seatRecordsList;
    }

    public void addBooking(int userId, int performanceId, int seatTypeId, String notes, int quantity, String confirmationCode) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_PERFORMANCES_ID, performanceId);
        values.put(COLUMN_SEAT_ID, seatTypeId);
        values.put(COLUMN_BOOKING_NOTES, notes);
        values.put(COLUMN_BOOKING_QUANTITY, quantity);
        values.put(COLUMN_CONFIRMATION, confirmationCode);

        long newRowId = db.insert(TABLE_BOOKINGS, null, values);
        if (newRowId == -1) {
            Log.e(TAG, "Error inserting new booking");
        }

        db.close();
    }


    @SuppressLint("Range")
    public List<BookingRecords> getUserBookings(int userId) {
        List<BookingRecords> bookings = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_USER_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(TABLE_BOOKINGS, null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Create a new Booking object and populate it with data from the cursor
                // Add the Booking object to the bookings list
                // You may need to adjust this code based on your Booking class and database schema
                BookingRecords booking = new BookingRecords();
                booking.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_BOOKING_ID)));
                booking.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                booking.setPerformanceId(cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)));
                booking.setSeatTypeId(cursor.getInt(cursor.getColumnIndex(COLUMN_SEAT_ID)));
                booking.setNotes(cursor.getString(cursor.getColumnIndex(COLUMN_BOOKING_NOTES)));
                booking.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_BOOKING_QUANTITY)));
                booking.setConfirmationCode(cursor.getString(cursor.getColumnIndex(COLUMN_CONFIRMATION)));
                bookings.add(booking);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookings;
    }
    public PerformanceData getPerformance(int performanceId) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Your query to fetch the performance data
        String SELECT_PERFORMANCE = "SELECT p.*, SUM(s." + COLUMN_SEAT_AVAILABLE + ") as total_seats, MIN(s."
                + COLUMN_SEAT_PRICE + ") as min_price, a." + COLUMN_WHEELCHAIR_ACCESSIBLE
                + ", a." + COLUMN_STAIRS + ", a." + COLUMN_FLASHING_LIGHTS + " FROM "
                + TABLE_PERFORMANCES + " p LEFT JOIN " + TABLE_SEATS + " s ON p."
                + COLUMN_PERFORMANCES_ID + " = s." + COLUMN_PERFORMANCES_ID + " LEFT JOIN "
                + TABLE_ACCESSIBILITY + " a ON p." + COLUMN_PERFORMANCES_ID + " = a."
                + COLUMN_PERFORMANCES_ID + " WHERE p." + COLUMN_PERFORMANCES_ID + " = ? GROUP BY p."
                + COLUMN_PERFORMANCES_ID;

        Cursor cursor = db.rawQuery(SELECT_PERFORMANCE, new String[]{String.valueOf(performanceId)});

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") PerformanceRecords performance = new PerformanceRecords(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
            );

            @SuppressLint("Range") SeatRecords seat = new SeatRecords(
                    -1,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                    "",
                    cursor.getInt(cursor.getColumnIndex("total_seats")),
                    cursor.getString(cursor.getColumnIndex("min_price"))
            );

            @SuppressLint("Range") AccessibilityRecords accessibility = new AccessibilityRecords(
                    -1,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PERFORMANCES_ID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_WHEELCHAIR_ACCESSIBLE)) != 0,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_STAIRS)) != 0,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_FLASHING_LIGHTS)) != 0
            );

            cursor.close();
            PerformanceData performanceData = new PerformanceData(performance, seat, accessibility);
            return performanceData;
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }
    public void updateSeatsAfterCancellation(int performanceId, int seatTypeId, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        String UPDATE_SEATS = "UPDATE " + TABLE_SEATS + " SET " + COLUMN_SEAT_AVAILABLE + " = " + COLUMN_SEAT_AVAILABLE + " + ?" + " WHERE " + COLUMN_PERFORMANCES_ID + " = ? AND " + COLUMN_SEAT_ID + " = ?";
        SQLiteStatement stmt = db.compileStatement(UPDATE_SEATS);
        stmt.bindLong(1, quantity);
        stmt.bindLong(2, performanceId);
        stmt.bindLong(3, seatTypeId);
        stmt.executeUpdateDelete();

        db.close();
    }
    public void deleteBooking(int bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_BOOKING_ID + "=?";
        String[] whereArgs = {String.valueOf(bookingId)};

        int deletedRows = db.delete(TABLE_BOOKINGS, whereClause, whereArgs);

        db.close();
    }
}











