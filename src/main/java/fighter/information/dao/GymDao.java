package fighter.information.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fighter.information.entity.Gym;

public interface GymDao extends JpaRepository<Gym, Long> {
	Gym findGymByGymName(String gym);
}
