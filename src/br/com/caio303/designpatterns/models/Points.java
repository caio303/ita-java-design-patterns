package br.com.caio303.designpatterns.models;

import java.util.Map;

import br.com.caio303.designpatterns.abstracts.Achievement;

public class Points extends Achievement {
	private Integer amountOfPoints;

	public Points(String name, Integer amountOfPoints) {
		super(name);
		this.amountOfPoints = amountOfPoints;
	}
	
	@Override
	public boolean tratarAdicao(Map<String, Achievement> mapAchievements) {
		if(adicionouSeNaoExiste(mapAchievements))
			return true;
		
		Points points = Points.valueOf(mapAchievements.get(getName()));
		
		int pointsSum = points.getAmountOfPoints()+this.getAmountOfPoints();
		points.setAmountOfPoints(pointsSum);
		return true;
	}
	
	public static Points valueOf(Achievement a) {
		return (Points) a;
	}
	
	public Integer getAmountOfPoints() {
		return amountOfPoints;
	}
	public void setAmountOfPoints(Integer amountOfPoints) {
		this.amountOfPoints = amountOfPoints;
	}
}