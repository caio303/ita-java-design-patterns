package br.com.caio303.designpatterns.storages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caio303.designpatterns.abstracts.Achievement;
import br.com.caio303.designpatterns.interfaces.AchievementObserver;
import br.com.caio303.designpatterns.interfaces.AchievementStorage;

public class MemoryAchievementStorage implements AchievementStorage {

	private Map<String, Map<String,Achievement>> mapUsersAchievements = new HashMap<>();
	
	private List<AchievementObserver> achievementObservers;
	
	@Override
	public void addAchievement(String user, Achievement a) {
		mapUsersAchievements.putIfAbsent(user, new HashMap<>());
		
		boolean adicionou = a.tratarAdicao(mapUsersAchievements.get(user));
		if(adicionou) {
			notifyObservers(user,a);
			addObserver(a);
		}
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return (List<Achievement>) mapUsersAchievements.get(user).values();
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		return mapUsersAchievements.get(user).get(achievementName);
	}
	
	private void notifyObservers(String user, Achievement a) {
		achievementObservers.forEach(aObs -> aObs.achievementUpdate(user, a));
	}
	
	private void addObserver(Achievement a) {
		if(a instanceof AchievementObserver)
			addObserver((AchievementObserver) a);
	}
	
	private void addObserver(AchievementObserver newObserver) {
		achievementObservers.add(newObserver);
	}
}