package br.com.caio303.designpatterns.models;

import java.util.Map;

import br.com.caio303.designpatterns.abstracts.Achievement;

public class Badge extends Achievement {
	public Badge(String name) {
		super(name);
	}

	@Override
	public boolean tratarAdicao(Map<String, Achievement> mapUsuariosAchievements){
		return adicionouSeNaoExiste(mapUsuariosAchievements);
	}
	
	public static Badge valueOf(Achievement a) {
		return (Badge) a;
	}
}