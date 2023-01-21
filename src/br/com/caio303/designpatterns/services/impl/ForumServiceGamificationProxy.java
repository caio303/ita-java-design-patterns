package br.com.caio303.designpatterns.services.impl;

import br.com.caio303.designpatterns.abstracts.Achievement;
import br.com.caio303.designpatterns.factories.AchievementStorageFactory;
import br.com.caio303.designpatterns.interfaces.AchievementObserver;
import br.com.caio303.designpatterns.interfaces.AchievementStorage;
import br.com.caio303.designpatterns.models.Badge;
import br.com.caio303.designpatterns.models.Points;
import br.com.caio303.designpatterns.services.ForumService;

public class ForumServiceGamificationProxy implements ForumService, AchievementObserver {

	private ForumService forumService;
	
	// TODO Sprint 2:
	//		implementar metodos para validar se usuarioExiste(), 
	//		topicoExiste(), e comentarioExiste() para melhor aproveitamento do Proxy

	public ForumServiceGamificationProxy() {
		this.forumService = new ForumServiceGamification();
	}

	@Override
	public void addTopic(String user, String topic) {
		forumService.addTopic(user, topic);
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		forumService.addComment(user, topic, comment);
	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		forumService.likeTopic(user, topic, topicUser);
	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		forumService.likeComment(user, topic, comment, commentUser);
	}

	@Override
	public void achievementUpdate(String user, Achievement a) {
		if(a instanceof Points) {
			Points p = Points.valueOf(a);
			if(p.getName().equals("CREATION") && getAmtOfPoints(user, a.getName()) >= 100)
				getAchievStorage().addAchievement(user, new Badge("INVENTOR"));
			else if(p.getName().equals("PARTICIPATION") && getAmtOfPoints(user, a.getName()) >= 100)
				getAchievStorage().addAchievement(user, new Badge("PART OF THE COMMUNITY"));
		}
	}
	
	private Integer getAmtOfPoints(String user, String pointsAchievName) {
		return Points.valueOf(getAchievStorage().getAchievement(user, pointsAchievName)).getAmountOfPoints();
	}
	
	private AchievementStorage getAchievStorage() {
		return AchievementStorageFactory.getAchievementStorage();
	}

}