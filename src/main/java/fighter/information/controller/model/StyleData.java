package fighter.information.controller.model;

import java.util.HashSet;
import java.util.Set;

import fighter.information.entity.Fighter;
import fighter.information.entity.Gym;
import fighter.information.entity.Style;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StyleData {
	private Long styleId;
	private String name;
	
	private Set<FighterData> fighters = new HashSet<>();
	
	private Set<GymData> gyms = new HashSet<>();
	
	public StyleData(Style style) {
		this.styleId = style.getStyleId();
		this.name = style.getName();
		
		for(Fighter fighter : style.getFighters()) {
			this.fighters.add(new FighterData(fighter));
		}
		
		for(Gym gym : style.getGyms()) {
			this.gyms.add(new GymData(gym));
		}
	}

	public Style toStyle() {
		Style style = new Style();
		style.setStyleId(styleId);
		style.setName(name);

		for (FighterData fighterData : fighters) {
			style.getFighters().add(fighterData.toFighter());
		}
		
		for (GymData gymData : gyms) {
			style.getGyms().add(gymData.toGym());
		}
		
		return style;
	}

}
