package br.com.caio303.designpatterns.interfaces;

import br.com.caio303.designpatterns.abstracts.Achievement;

public interface AchievementObserver {
	void achievementUpdate(String user, Achievement a);
}