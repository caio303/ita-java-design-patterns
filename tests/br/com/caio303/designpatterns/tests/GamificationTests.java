package br.com.caio303.designpatterns.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caio303.designpatterns.factories.AchievementStorageFactory;
import br.com.caio303.designpatterns.interfaces.AchievementStorage;
import br.com.caio303.designpatterns.models.Badge;
import br.com.caio303.designpatterns.models.Points;
import br.com.caio303.designpatterns.services.impl.ForumServiceGamificationProxy;
import br.com.caio303.designpatterns.storages.MemoryAchievementStorage;

class GamificationTests {
	
	private static ForumServiceGamificationProxy forum = new ForumServiceGamificationProxy();
	
	@BeforeAll
	static void beforeAll() {
		MemoryAchievementStorage memoryStorage = new MemoryAchievementStorage();
		memoryStorage.addObserver(forum);
		AchievementStorageFactory.setAchievementStorage(memoryStorage);
	}
	
	@BeforeEach
	void beforeEach() {
		MemoryAchievementStorage memoryStorage = new MemoryAchievementStorage();
		memoryStorage.addObserver(forum);
		AchievementStorageFactory.setAchievementStorage(memoryStorage);
	}

	@Test
	void addTopicAddsTheCorrespondingAchievements() {
		forum.addTopic("Caio", "I've just finished the best course of Design Patterns with Java :)\nCertificate: Design Patterns | 18h");

		Points creationAchiev = Points.valueOf(getAchievStorage().getAchievement("Caio","CREATION"));
		Badge iCanTalkAchiev = Badge.valueOf(getAchievStorage().getAchievement("Caio","I CAN TALK"));
		
		assertEquals(creationAchiev.getAmountOfPoints(), Integer.valueOf(5));
		assertNotEquals(iCanTalkAchiev, null);
	}

	@Test
	void addCommentAddsTheCorrespondingAchievements() {
		forum.addComment("Caio", "{'topicId':3}","Thanks to all my parents and supporters!");

		Points participationAchiev = Points.valueOf(getAchievStorage().getAchievement("Caio","PARTICIPATION"));
		Badge letMeAddAchiev = Badge.valueOf(getAchievStorage().getAchievement("Caio","LET ME ADD"));
		
		assertEquals(participationAchiev.getAmountOfPoints(), Integer.valueOf(3));
		assertNotEquals(letMeAddAchiev, null);
	}

	@Test
	void likeTopicAddsTheCorrespondingAchievements() {
		forum.likeTopic("Caio", "{'topicId':5}","Alves");

		Points participationAchiev = Points.valueOf(getAchievStorage().getAchievement("Caio","CREATION"));
		
		assertEquals(participationAchiev.getAmountOfPoints(), Integer.valueOf(1));
	}
	
	@Test
	void likeCommentAddsTheCorrespondingAchievements() {
		forum.likeComment("Alves", "{'topicId':5}","Thanks to all my parents and supporters!","Caio");

		Points participationAchiev = Points.valueOf(getAchievStorage().getAchievement("Alves","PARTICIPATION"));
		
		assertEquals(participationAchiev.getAmountOfPoints(), Integer.valueOf(1));
	}

	@Test
	void achieveInventorBadgeRightAfterGetting100CreationPoints() {
		for(int i=0;i<20;i++) 
			forum.addTopic("Caio", "I've just finished the best course of Design Patterns with Java :)\nCertificate: Design Patterns | 18h");

		Badge inventorAchiev = Badge.valueOf(getAchievStorage().getAchievement("Caio","INVENTOR"));
		assertNotEquals(inventorAchiev, null);
	}

	@Test
	void achievePartOfTheCommunityBadgeRightAfterGetting100ParticipationPoints() {
		for(int i=0;i<37;i++) 
			forum.addComment("Caio", "{'topicId':3}","Thanks to all my parents and supporters!");

		Badge partOfCommunityAchiev = Badge.valueOf(getAchievStorage().getAchievement("Caio","PART OF THE COMMUNITY"));
		assertNotEquals(partOfCommunityAchiev, null);
	}
	
	private static  AchievementStorage getAchievStorage() {
		return AchievementStorageFactory.getAchievementStorage();
	}
}
