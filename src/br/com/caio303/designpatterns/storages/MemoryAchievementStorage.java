package br.com.caio303.designpatterns.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		if(adicionou)
			notifyObservers(user,a);
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return mapUsersAchievements.get(user).values().stream().collect(Collectors.toList());
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		return mapUsersAchievements.get(user).get(achievementName);
	}
	
	private void notifyObservers(String user, Achievement a) {
		if(achievementObservers != null && achievementObservers.size() > 0)
			achievementObservers.forEach(aObs -> aObs.achievementUpdate(user, a));
	}

	public void addObserver(AchievementObserver newObserver) {
		if(achievementObservers == null)
			achievementObservers = new ArrayList<>();
		achievementObservers.add(newObserver);
	}
	
//	private void addObserver(Achievement a) {
//		if(a instanceof AchievementObserver)
//			addObserver((AchievementObserver) a);
//	}
}