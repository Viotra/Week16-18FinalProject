package fighter.information.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import fighter.information.entity.Style;

public interface StyleDao extends JpaRepository<Style, Long> {
	Set<Style> findAllByNameIn(Set<String> styles);
	Style findStyleByName(String style);
}
