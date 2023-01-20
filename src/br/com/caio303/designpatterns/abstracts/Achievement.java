package br.com.caio303.designpatterns.abstracts;

import java.util.Map;

public abstract class Achievement {

	private String name;

	public Achievement(String name) {
		this.name = name;
	}

	public abstract boolean tratarAdicao(Map<String, Achievement> mapAchievements);
	
	protected boolean adicionouSeNaoExiste(Map<String, Achievement> mapAchievements) {
		// Retorna nulo se adicionou
		return mapAchievements.putIfAbsent(getName(), this) == null;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Achievement))
			return false;
		
		return getName().equals(obj);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}