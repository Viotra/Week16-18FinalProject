package fighter.information.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fighter.information.controller.model.FighterData;
import fighter.information.controller.model.GymData;
import fighter.information.controller.model.StyleData;
import fighter.information.service.InformationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fighter_information")
@Slf4j
public class InformationController {

	@Autowired
	public InformationService informationService;

	// create fighter
	@PostMapping("/gym/{gymId}/fighter")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FighterData saveFighter(@RequestBody FighterData fighterData, @PathVariable Long gymId) {
		log.info("Creating fighter {}", fighterData);

		return informationService.saveFighter(fighterData, gymId);
	}

	// read fighter
	@GetMapping("/fighter")
	public List<FighterData> retrieveAllFighters() {
		log.info("Getting a list of all fighters.");
		return informationService.retrieveAllFighters();
	}

	@GetMapping("/fighter/{id}")
	public FighterData retrieveFighterById(@PathVariable Long id) {
		log.info("Finding fighter {}", id);
		return informationService.retrieveFighterById(id);
	}

	// update fighter
	@PutMapping("gym/{gymId}/fighter/{id}")
	public FighterData updateFighter(@PathVariable Long id, @RequestBody FighterData fighterData,
			@PathVariable Long gymId) {
		informationService.findFighterById(id);
		fighterData.setFighterId(id);
		log.info("Applying updates to Fighter {}", id);
		return informationService.saveFighter(fighterData, gymId);
	}

	// delete fighter
	@DeleteMapping("/fighter/{id}")
	public Map<String, String> deleteFighterById(@PathVariable Long id) {
		log.info("Deleting Fighter {}", id);

		informationService.deleteFighterById(id);

		// log.info("Fighter {} was successfully removed.", id);
		return Map.of("message", "Fighter " + id + " was successfully removed.");
	}

	// read Style
	@GetMapping("/style")
	public List<StyleData> retrieveAllStyles() {
		log.info("Loading all styles");
		return informationService.retrieveAllStyles();
	}

	// update join table
	@PostMapping("/fighter/{fighterId}/style/{styleId}")
	public Map<String, String> joinFighterAndStyle(@PathVariable Long fighterId, @PathVariable Long styleId) {
		informationService.joinFighterAndStyle(fighterId, styleId);
		return Map.of("message", "Fighter " + fighterId + " and style " + styleId + " have been successfully joined.");
	}

	// create Gym
	@PostMapping("/gym")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GymData saveGym(@RequestBody GymData gymData) {
		log.info("Creating fighter {}", gymData);

		return informationService.saveGym(gymData);
	}

	// read Gym
	@GetMapping("/gym")
	public List<GymData> retrieveAllGyms() {
		log.info("Loading gyms.");
		return informationService.retrieveAllGyms();
	}

	@GetMapping("/gym/{id}")
	public GymData retrieveGymById(@PathVariable Long id) {
		return informationService.retrieveGymById(id);
	}

	// update Gym
	@PutMapping("/gym/{id}")
	public GymData updateGym(@PathVariable Long id, @RequestBody GymData gymData) {
		informationService.findGymById(id);
		gymData.setGymId(id);
		return informationService.saveGym(gymData);
	}

	// delete Gym
	@DeleteMapping("/gym/{id}")
	public Map<String, String> deleteGymById(@PathVariable Long id) {
		log.info("Removing Gym from database.");

		informationService.deleteGymById(id);

		return Map.of("message", "Successfully removed Gym with ID: " + id);
	}

}
