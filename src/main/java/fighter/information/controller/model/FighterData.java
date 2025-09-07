package fighter.information.controller.model;

import java.util.HashSet;
import java.util.Set;

import fighter.information.entity.Fighter;
import fighter.information.entity.Style;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FighterData {
	private Long fighterId;
	private String firstName;
	private String lastName;
	private double height;
	private double weight;
	private double reach;
	private int age;
	private int wins;
	private int losses;
	private int draws;
	private String gym;
	private Set<String> styles = new HashSet<>();
	
	public FighterData(Fighter dbFighter) {
		this.fighterId = dbFighter.getFighterId();
		this.firstName = dbFighter.getFirstName();
		this.lastName = dbFighter.getLastName();
		this.height = dbFighter.getHeight();
		this.weight = dbFighter.getWeight();
		this.reach = dbFighter.getReach();
		this.age = dbFighter.getAge();
		this.wins = dbFighter.getWins();
		this.losses = dbFighter.getLosses();
		this.draws = dbFighter.getDraws();
		this.gym = dbFighter.getGym().toString();
		
		
		for(Style style : dbFighter.getStyles()) { 
			styles.add(style.getName());
		}
		 
	}
	
	public FighterData(Long fighterId, String firstName, String lastName, double height,
			double weight, double reach, int age, int wins, int losses,	int draws) {
		
		this.fighterId = fighterId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.reach = reach;
		this.age = age;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
	}
	
	public Fighter toFighter() {
		Fighter fighter = new Fighter();
		
		fighter.setFighterId(fighterId);
		fighter.setFirstName(firstName);
		fighter.setLastName(lastName);
		fighter.setHeight(height);
		fighter.setWeight(weight);
		fighter.setReach(reach);
		fighter.setAge(age);
		fighter.setWins(wins);
		fighter.setLosses(losses);
		fighter.setDraws(draws);
		
//		for(Style style : styles) {
//			fighter.getStyles().add(style);
//		}
		
		return fighter;
	}

}
