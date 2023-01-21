package br.com.caio303.designpatterns.factories;

import br.com.caio303.designpatterns.interfaces.AchievementStorage;

public class AchievementStorageFactory {
	private static AchievementStorage achievementStorage;
	
	public static AchievementStorage getAchievementStorage() {
		return achievementStorage;
	}
	public static void setAchievementStorage(AchievementStorage newAchievementStorage) {
		achievementStorage = newAchievementStorage;
	}
}