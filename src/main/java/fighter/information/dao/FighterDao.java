package fighter.information.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fighter.information.entity.Fighter;

public interface FighterDao extends JpaRepository<Fighter, Long> {

}
