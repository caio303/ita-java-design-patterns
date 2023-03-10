package br.com.caio303.designpatterns.services.impl;

import br.com.caio303.designpatterns.factories.AchievementStorageFactory;
import br.com.caio303.designpatterns.models.Badge;
import br.com.caio303.designpatterns.models.Points;
import br.com.caio303.designpatterns.services.ForumService;

public class ForumServiceGamification implements ForumService {
	
	@Override
	public void addTopic(String user, String topic) {
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("CREATION", 5));
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("I CAN TALK"));
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("PARTICIPATION", 3));
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("LET ME ADD"));
	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("CREATION", 1));
	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("PARTICIPATION", 1));
	}
}