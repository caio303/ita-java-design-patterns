package br.com.caio303.designpatterns.interfaces;

import java.util.List;

import br.com.caio303.designpatterns.abstracts.Achievement;

public interface AchievementStorage {

	void addAchievement(String user, Achievement a);
	
	List<Achievement> getAchievements(String user);
	
	Achievement getAchievement(String user, String achievementName);
}
