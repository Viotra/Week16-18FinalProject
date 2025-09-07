package fighter.information.controller.model;

import java.util.HashSet;
import java.util.Set;

import fighter.information.entity.Fighter;
import fighter.information.entity.Gym;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GymData {
	private Long gymId;
	private String gymName;
	private String coach;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	private String style;
	
	private Set<FighterData> fighters = new HashSet<>();
	
	public GymData(Gym gym) {
		this.gymId = gym.getGymId();
		this.gymName = gym.getGymName();
		this.coach = gym.getCoach();
		this.streetAddress = gym.getStreetAddress();
		this.city = gym.getCity();
		this.state = gym.getState();
		this.zip = gym.getZip();
		this.phone = gym.getPhone();
		this.style = gym.getStyle().toString();
		
		for(Fighter fighter : gym.getFighters()) {
			this.fighters.add(new FighterData(fighter));
		}
	}
	
	public Gym toGym() {
		Gym gym = new Gym();
		
		gym.setGymId(gymId);
		gym.setGymName(gymName);
		gym.setCoach(coach);
		gym.setStreetAddress(streetAddress);
		gym.setCity(city);
		gym.setState(state);
		gym.setZip(zip);
		gym.setPhone(phone);
		
		for (FighterData fighterData : fighters) {
			gym.getFighters().add(fighterData.toFighter());
		}
		
		return gym;
	}
}
