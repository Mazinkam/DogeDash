package com.alicode.game.dogedash.sql;

import java.util.List;

import com.badlogic.gdx.sql.SQLiteGdxException;

public interface GameDatabaseInterface
{
	public void create();
	public void dropTheBase() throws SQLiteGdxException;
	public void addLevelHighscore(Level level, String tableName);
	public Level getLevelHighscore(int id, String tableName);
	public List<Level> getLevelList(String tableName);
	public void updateLevelHighscore(Level level, String tableName);
	public void deleteLevelHighscore(Level level, String tableName);
	public void addCostume(Costume costume, String tableName);
	public Costume getCostume(int id, String tableName);
	public List<Costume> getCostumeList(String tableName);
	public void updateCostume(Costume costume, String tableName);
	public void deleteCostume(Costume costume, String tableName);
	public void addSettings(Settings settings);
	public Settings getSettings(int id);
	public List<Settings> getSettingsList();
	public void updateSettings(Settings settings);
	public void deleteSettings(Settings settings);
	public void addMisc(Misc misc);
	public Misc getMisc(int id);
	public List<Misc> getMiscList();
	public void updateMisc(Misc misc);
	public void deleteMisc(Misc misc);
	public void dispose();
	
}