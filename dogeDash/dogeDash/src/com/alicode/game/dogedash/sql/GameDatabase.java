package com.alicode.game.dogedash.sql;

import java.util.ArrayList;
import java.util.List;

import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class GameDatabase {

	private Database dbHandler;

	private static final String DATABASE_NAME = "dogeGameDB";

	private static final int DATABASE_VERSION = 1;

	// tablet
	private static final String TABLE_LVL1 = "levelDay";
	private static final String TABLE_LVL2 = "levelNight";
	private static final String TABLE_COSTUME_BACK = "backTable";
	private static final String TABLE_COSTUME_NOSE = "noseTable";
	private static final String TABLE_COSTUME_HEAD = "headTable";
	private static final String TABLE_COSTUME_EYES = "eyesTable";
	private static final String TABLE_SETTINGS = "settingsTable";
	private static final String TABLE_MISC = "miscTable";

	// Level
	private static final String KEY_ID = "id";
	private static final String KEY_SCORE = "highScore";
	private static final String KEY_STYLE = "stylePoints";
	private static final String KEY_TIME = "timeAlive";
	private static final String KEY_CAUGHTPUPPIES = "caughtPuppyNum";
	private static final String KEY_MISSEDPUPPIES = "missedPuppyNum";
	private static final String KEY_PUPPYPOINTS = "puppyPoints";

	public static List<Level> dayLevelList;

	// Costume
	private static final String KEY_ITEM_OWNED = "isOwned";
	private static final String KEY_ITEM_PRICE = "itemPrice";

	// Currency
	private static final String KEY_CURRENCY = "dogeCoins";

	// Settings
	private static final String KEY_SETTINGS_SOUND = "soundSettings";
	private static final String KEY_SETTINGS_MUISC = "musicSettings";
	private static final String KEY_SETTINGS_VIBRATION = "vibrationSettings";

	private static String CREATE_LEVELONE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LVL1 + "(" + KEY_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY,"
			+ KEY_SCORE + " INTEGER," + KEY_STYLE + " INTEGER," + KEY_TIME + " INTEGER," + KEY_CAUGHTPUPPIES + " INTEGER," + KEY_MISSEDPUPPIES
			+ " INTEGER," + KEY_PUPPYPOINTS + " INTEGER" + ");";

	private static String CREATE_LEVELTWO_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LVL2 + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCORE
			+ " INTEGER," + KEY_STYLE + " INTEGER," + KEY_TIME + " INTEGER," + KEY_CAUGHTPUPPIES + " INTEGER," + KEY_MISSEDPUPPIES + " INTEGER,"
			+ KEY_PUPPYPOINTS + " INTEGER" + ");";

	// COSTUMES
	private static String CREATE_COSTUME_BACK_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COSTUME_BACK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_ITEM_OWNED + " INTEGER," + KEY_ITEM_PRICE + " INTEGER" + ");";

	private static String CREATE_COSTUME_EYES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COSTUME_EYES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_ITEM_OWNED + " INTEGER," + KEY_ITEM_PRICE + " INTEGER" + ");";

	private static String CREATE_COSTUME_NOSE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COSTUME_NOSE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_ITEM_OWNED + " INTEGER," + KEY_ITEM_PRICE + " INTEGER" + ");";

	private static String CREATE_COSTUME_HEAD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COSTUME_HEAD + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_ITEM_OWNED + " INTEGER," + KEY_ITEM_PRICE + " INTEGER" + ");";

	// SETTINGS
	private static String CREATE_SETTINGS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SETTINGS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_SETTINGS_SOUND + " INTEGER," + KEY_SETTINGS_MUISC + " INTEGER," + KEY_SETTINGS_VIBRATION + " INTEGER" + ");";

	// MISC
	private static String CREATE_MISC_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MISC + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CURRENCY
			+ " INTEGER " + ");";

	// Database creation sql statement
	private static final String DATABASE_CREATE = CREATE_LEVELONE_TABLE + CREATE_LEVELTWO_TABLE + CREATE_COSTUME_BACK_TABLE
			+ CREATE_COSTUME_EYES_TABLE + CREATE_COSTUME_HEAD_TABLE + CREATE_COSTUME_NOSE_TABLE + CREATE_SETTINGS_TABLE + CREATE_MISC_TABLE;

	public GameDatabase() {
		Gdx.app.log(DogeDashCore.LOG, "DB initalizing");
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);

		dbHandler.setupDatabase();
		try {
			dbHandler.openOrCreateDatabase();
		//	dropTheBase();
			dbHandler.execSQL(DATABASE_CREATE);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		initDb();
		Gdx.app.log(DogeDashCore.LOG, "DB initalized");

	}

	private void initDb() {

		Gdx.app.log(DogeDashCore.LOG, "DAY LEVEL LIST---------------------------------------");

		dayLevelList = getLevelList("levelDay");
		Gdx.app.log(DogeDashCore.LOG, "size: " + dayLevelList.size());

		
		if (dayLevelList.isEmpty()) {
			addLevelHighscore(new Level(1, 1, 1, 1, 1, 1, 1), "levelDay");
			addLevelHighscore(new Level(2, 2, 2, 2, 2, 2, 2), "levelDay");
			addLevelHighscore(new Level(3, 3, 3, 3, 3, 3, 3), "levelDay");
		} else {
			for (Level obj : dayLevelList) {

				String log = "Id: " + obj.getId() + " ,highscore: " + obj.getHighScore() + " ,lvl style: " + obj.getStylePoints() + ", lvl time: "
						+ obj.getTimeAlive() + ", lvl caughtpups: " + obj.getCaughtPuppyNum() + ", lvl missedpups: " + obj.getMissedPuppyNum()
						+ ", lvl pup points: " + obj.getPuppyPoints() + " table size: " + dayLevelList.size();

				Gdx.app.log(DogeDashCore.LOG, log);

			}
		}
		Gdx.app.log(DogeDashCore.LOG, "" + getLevelHighscore(1, "levelDay").missedPuppyNum);
		// updateLevelHighscore(new Level(1, 1, 1, 1, 1, 1, 1), "levelDay");
		// updateLevelHighscore(new Level(2, 2, 2, 2, 2, 2, 2), "levelDay");
		// updateLevelHighscore(new Level(3, 3, 3, 3, 3, 3, 3), "levelDay");
		// deleteLevelHighscore(new Level(1), "levelDay");
		// deleteLevelHighscore(new Level(2), "levelDay");
		// Gdx.app.log(DogeDashCore.LOG, "" + getLevelHighscore(2,
		// "levelDay").caughtPuppyNum);

		// Log.d("Reading: ",
		// "NIGHT LEVEL LIST---------------------------------------");
		// if (nightLevelList.isEmpty()) {
		// db.addLevelHighscore(new Level(1, 0, 0, 0, 0, 0, 0), "levelNight");
		// db.addLevelHighscore(new Level(2, 0, 0, 0, 0, 0, 0), "levelNight");
		// db.addLevelHighscore(new Level(3, 0, 0, 0, 0, 0, 0), "levelNight");
		// }
		//
		// for (Level obj : nightLevelList) {
		//
		// String log = "Id: " + obj.getId() + " ,highscore: " +
		// obj.getHighScore() + " ,lvl style: " + obj.getStylePoints() +
		// ", lvl time: "
		// + obj.getTimeAlive() + ", lvl caughtpups: " + obj.getCaughtPuppyNum()
		// + ", lvl missedpups: " + obj.getMissedPuppyNum()
		// + ", lvl pup points: " + obj.getPuppyPoints() + " table size: " +
		// nightLevelList.size();
		//
		// Log.d("NIGHT_LVL_LIST: ", log);
		//
		// }
		// Log.d("Reading: ",
		// "BACK COSTUME LIST---------------------------------------");
		// if (backCostumeList.isEmpty()) {
		// db.addCostume(new Costume(1, 1, 100), "backTable");
		// db.addCostume(new Costume(2, 1, 100), "backTable");
		// }
		//
		// for (Costume obj : backCostumeList) {
		//
		// String log = "Id: " + obj.getId() + " , owned: " + obj.getIsOwned() +
		// " , price: " + obj.getItemPrice();
		//
		// Log.d("BACK_COSTUME_LVL_LIST: ", log);
		//
		// }
		// Log.d("Reading: ",
		// "NOSE COSTUME LIST---------------------------------------");
		// if (noseCostumeList.isEmpty()) {
		// db.addCostume(new Costume(1, 1, 20), "noseTable");
		// db.addCostume(new Costume(2, 1, 20), "noseTable");
		// }
		//
		// for (Costume obj : noseCostumeList) {
		//
		// String log = "Id: " + obj.getId() + " , owned: " + obj.getIsOwned() +
		// " , price: " + obj.getItemPrice();
		//
		// Log.d("NOSE_COSTUME_LVL_LIST: ", log);
		//
		// }
		// Log.d("Reading: ",
		// "HEAD COSTUME LIST---------------------------------------");
		// if (headCostumeList.isEmpty()) {
		// db.addCostume(new Costume(1, 1, 20), "headTable");
		// db.addCostume(new Costume(2, 1, 20), "headTable");
		// db.addCostume(new Costume(3, 1, 20), "headTable");
		// db.addCostume(new Costume(4, 1, 20), "headTable");
		// db.addCostume(new Costume(5, 1, 20), "headTable");
		// }
		//
		// for (Costume obj : headCostumeList) {
		//
		// String log = "Id: " + obj.getId() + " , owned: " + obj.getIsOwned() +
		// " , price: " + obj.getItemPrice();
		//
		// Log.d("HEAD_COSTUME_LVL_LIST: ", log);
		//
		// }
		// Log.d("Reading: ",
		// "EYES COSTUME LIST---------------------------------------");
		// if (eyesCostumeList.isEmpty()) {
		// db.addCostume(new Costume(1, 1, 20), "eyesTable");
		// db.addCostume(new Costume(2, 1, 20), "eyesTable");
		// db.addCostume(new Costume(3, 1, 20), "eyesTable");
		// db.addCostume(new Costume(4, 1, 20), "eyesTable");
		// }
		//
		// for (Costume obj : eyesCostumeList) {
		//
		// String log = "Id: " + obj.getId() + " , owned: " + obj.getIsOwned() +
		// " , price: " + obj.getItemPrice();
		//
		// Log.d("EYES_COSTUME_LVL_LIST: ", log);
		//
		// }
		// Log.d("Reading: ",
		// "SETTINGS LIST---------------------------------------");
		// if (settingsList.isEmpty()) {
		// db.addSettings(new Settings(1, 1, 0, 1));
		// }
		//
		// for (Settings obj : settingsList) {
		//
		// String log = "Id: " + obj.getId() + " , sound: " +
		// obj.getSoundSettings() + " , music: " + obj.getMusicSettings() +
		// " , vibrate: "
		// + obj.getVibrationSettings();
		//
		// Log.d("SETTINGS_LIST: ", log);
		//
		// }
		//
		// Log.d("Reading: ",
		// "MISC LIST---------------------------------------");
		// if (miscList.isEmpty()) {
		// db.addMisc(new Misc(1, 1000));
		// }
		//
		// for (Misc obj : miscList) {
		//
		// String log = "Id: " + obj.getId() + " , owned: " +
		// obj.getDogeCoins();
		//
		// Log.d("MISC_LIST: ", log);
		//
		// }
	}

	public void dropTheBase() throws SQLiteGdxException {
		// "DROP TABLE IF EXISTS " + TABLE_LVL1 + ";" +
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_LVL1 + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_LVL2 + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_COSTUME_BACK + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_COSTUME_EYES + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_COSTUME_HEAD + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_COSTUME_NOSE + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_MISC + ";");
		dbHandler.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS + ";");
	}

	// ----------------------------------------------------------------LVL DB
	public void addLevelHighscore(Level level, String tableName) {
		try {
			dbHandler.execSQL("INSERT INTO " + tableName + "  VALUES (" + level.getId() + "," + level.getHighScore() + "," + level.getStylePoints()
					+ "," + level.getTimeAlive() + "," + level.getCaughtPuppyNum() + "," + level.getMissedPuppyNum() + "," + level.getPuppyPoints()
					+ ")");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		Gdx.app.log(DogeDashCore.LOG, "wrote into " + tableName);
	}

	public Level getLevelHighscore(int id, String tableName) {
		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT " + KEY_ID + "," + KEY_SCORE + "," + KEY_STYLE + "," + KEY_TIME + "," + KEY_CAUGHTPUPPIES + ","
					+ KEY_MISSEDPUPPIES + "," + KEY_PUPPYPOINTS + " FROM " + tableName + " WHERE " + KEY_ID + "=" + "'" + id + "'");

		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		cursor.next();

		Level level = new Level(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
				Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
				Integer.parseInt(cursor.getString(6)));

		return level;
	}

	public List<Level> getLevelList(String tableName) {
		List<Level> levelList = new ArrayList<Level>();
		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT  * FROM " + tableName);

		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		while (cursor.next()) {
			Level level = new Level();
			level.setID(Integer.parseInt(cursor.getString(0)));
			level.setHighScore(Integer.parseInt(cursor.getString(1)));
			level.setStylePoints(Integer.parseInt(cursor.getString(2)));
			level.setTimeAlive(Integer.parseInt(cursor.getString(3)));
			level.setCaughtPuppyNum(Integer.parseInt(cursor.getString(4)));
			level.setMissedPuppyNum(Integer.parseInt(cursor.getString(5)));
			level.setPuppyPoints(Integer.parseInt(cursor.getString(6)));

			levelList.add(level);
		}

		return levelList;
	}

	public void updateLevelHighscore(Level level, String tableName) {
		DatabaseCursor cursor = null;
		String id = " " + KEY_ID + "=" + level.getId();
		String score = " " + KEY_SCORE + "=" + level.getHighScore() + ", ";
		String style = " " + KEY_STYLE + "=" + level.getStylePoints() + ", ";
		String time = " " + KEY_TIME + "=" + level.getTimeAlive() + ", ";
		String caughtPups = " " + KEY_CAUGHTPUPPIES + "=" + level.getCaughtPuppyNum() + ", ";
		String missedPups = " " + KEY_MISSEDPUPPIES + "=" + level.getMissedPuppyNum() + ", ";
		String pupPoints = " " + KEY_PUPPYPOINTS + "=" + level.getPuppyPoints();

		Gdx.app.log(DogeDashCore.LOG, "Trying to update " + id + tableName + score + style + time + caughtPups + missedPups + pupPoints);

		try {
			dbHandler.execSQL("UPDATE " + tableName + " SET" + score + style + time + caughtPups + missedPups + pupPoints + " WHERE" + id);
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public void deleteLevelHighscore(Level level, String tableName) {
		String id = " " + KEY_ID + "=" + level.getId();

		try {
			dbHandler.execSQL("DELETE FROM " + tableName + " WHERE" + id);
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ----------------------------------------------------------------LVL DB
	// ----------------------------------------------------------------LVL MISC
	public void addMiscItem(Misc misc) {
		try {
			dbHandler.execSQL("INSERT INTO " + TABLE_MISC + "  VALUES (" + misc.getId() + "," + misc.getDogeCoins() + ")");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		Gdx.app.log(DogeDashCore.LOG, "wrote into " + TABLE_MISC);
	}

	public Misc getMiscItem(int id) {
		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT " + KEY_ID + "," + KEY_CURRENCY + "FROM" + TABLE_MISC);

		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		while (cursor.next()) {
			Gdx.app.log(
					"FromDb",
					String.valueOf(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " "
							+ cursor.getString(5) + " " + cursor.getString(6)));
		}

		Misc misc = new Misc(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)));

		return misc;
	}

	public List<Misc> getMiscList(String tableName) {
		List<Misc> miscList = new ArrayList<Misc>();
		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT  * FROM " + tableName);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		do {
			Misc misc = new Misc();
			misc.setId(Integer.parseInt(cursor.getString(0)));
			misc.setDogeCoins(Integer.parseInt(cursor.getString(1)));
			miscList.add(misc);
		} while (cursor.next());

		return miscList;
	}

	public void updateMiscItem(Misc misc) {
		DatabaseCursor cursor = null;
		String id = " " + KEY_ID + "=" + misc.getId();
		String doge = " " + KEY_SCORE + "=" + misc.getDogeCoins();

		Gdx.app.log(DogeDashCore.LOG, "Trying to update " + id + TABLE_MISC + doge);

		try {
			dbHandler.execSQL("UPDATE " + TABLE_MISC + " SET" + doge + " WHERE" + id);
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public void deleteMiscItem(Misc misc) {
		String id = " " + KEY_ID + "=" + misc.getId();

		try {
			dbHandler.execSQL("DELETE FROM " + TABLE_MISC + " WHERE" + id);
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ----------------------------------------------------------------LVL MISC
	public void dispose() {
		try {
			dbHandler.closeDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		dbHandler = null;
		Gdx.app.log(DogeDashCore.LOG, "dispose DB");
	}
}