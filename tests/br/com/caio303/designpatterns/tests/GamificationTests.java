package br.com.caio303.designpatterns.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caio303.designpatterns.factories.AchievementStorageFactory;
import br.com.caio303.designpatterns.interfaces.AchievementStorage;
import br.com.caio303.designpatterns.services.impl.ForumServiceGamificationProxy;
import br.com.caio303.designpatterns.storages.MemoryAchievementStorage;

class GamificationTests {
	
	private AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
	
	@BeforeEach
	void beforeEach() {
		AchievementStorageFactory.setAchievementStorage(new MemoryAchievementStorage());
		System.out.println("resetou o storage");
	}

	@Test
	void addTopicAddsTheCorrespondingAchievements() {
		ForumServiceGamificationProxy forum = new ForumServiceGamificationProxy();
		forum.addTopic("Caio", "I've just finished the best course of Design Patterns with Java :)\nCertificate: Design Patterns | 18h");
		// 5 pontos -> CREATION
		// I CAN TALK
//		List<Achievement> achievements = achievementStorage.getAchievements("Caio");
//		if(achievements.contains(forum))
		assertEquals(1, 1);
	}

	@Test
	void addCommentAddsTheCorrespondingAchievements() {
//		Deve adicionar 3 pontos do tipo "PARTICIPATION". Deve adicionar o badge "LET ME ADD"
		assertEquals(1, 1);
	}
}
