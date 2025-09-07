package fighter.information.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fighter.information.controller.model.FighterData;
import fighter.information.controller.model.GymData;
import fighter.information.controller.model.StyleData;
import fighter.information.dao.FighterDao;
import fighter.information.dao.GymDao;
import fighter.information.dao.StyleDao;
import fighter.information.entity.Fighter;
import fighter.information.entity.Gym;
import fighter.information.entity.Style;

@Service
public class InformationService {

	@Autowired
	public FighterDao fighterDao;
	
	@Autowired
	public GymDao gymDao;
	
	@Autowired
	public StyleDao styleDao;
	
	@Transactional(readOnly = false)
	public FighterData saveFighter(FighterData fighterData, Long gymId) {
		Fighter fighter = fighterData.toFighter();
		
		Set<Style> styles = styleDao.findAllByNameIn(fighterData.getStyles());
		
		for (Style style : styles) {
		//	System.out.println(style.toString());
			fighter.getStyles().add(style);
		}
		
		Gym gym = gymDao.findById(gymId).orElseThrow(() -> new NoSuchElementException("Could not find gym with ID: " + gymId));
//		Gym gym = gymDao.findGymByGymName(fighterData.getGym());
//		
//		if (Objects.isNull(gym)) {
//			throw new NoSuchElementException("Gym " + fighterData.getGym() + 
//					" is not in the gym database. Please add gym to database to proceed.");
//		}
		//System.out.println(fighter.getStyles().toString() + " is in fighter");
		fighter.setGym(gym);
		
		Fighter dbFighter = fighterDao.save(fighter);
		
		return new FighterData(dbFighter);
	}

	@Transactional(readOnly = true)
	public List<FighterData> retrieveAllFighters() {
		return fighterDao.findAll().stream().map(FighterData::new).toList();
	}

	public FighterData retrieveFighterById(Long id) {
		Fighter fighter = findFighterById(id);
		return new FighterData(fighter);
	}

	public Fighter findFighterById(Long id) {
		return fighterDao.findById(id).orElseThrow(
				() -> new NoSuchElementException("Fighter " + id + " not found."));
		
	}

	@Transactional(readOnly = false)
	public void deleteFighterById(Long id) {
		Fighter fighter = findFighterById(id);
		fighterDao.delete(fighter);
	}

	public List<StyleData> retrieveAllStyles() {
		return styleDao.findAll().stream().map(StyleData::new).toList();
	}

	@Transactional(readOnly = false)
	public void deleteGymById(Long id) {
		Gym gym = findGymById(id);
		gymDao.delete(gym);
	}

	public Gym findGymById(Long id) {
		return gymDao.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Gym " + id + " was not found."));
	}

	public List<GymData> retrieveAllGyms() {
		return gymDao.findAll().stream().map(GymData::new).toList();
	}
	
	
	public GymData retrieveGymById(Long id) {
		Gym gym = findGymById(id);
		return new GymData(gym);
	}

	@Transactional(readOnly = false)
	public GymData saveGym(GymData gymData) {
		Gym gym = gymData.toGym();
		
		gym.setStyle(styleDao.findStyleByName(gymData.getStyle()));
		
		Gym dbGym = gymDao.save(gym);
		
		return new GymData(dbGym);
	}

	@Transactional(readOnly = false)
	public void joinFighterAndStyle(Long fighterId, Long styleId) {
		Fighter fighter = findFighterById(fighterId);
		Style style = findStyleById(styleId);
		
		fighter.getStyles().add(style);
		style.getFighters().add(fighter);
	}

	private Style findStyleById(Long styleId) {
		return styleDao.findById(styleId).orElseThrow(() -> new NoSuchElementException("Could not find"
				+ "style with ID: " + styleId));
	}

}
