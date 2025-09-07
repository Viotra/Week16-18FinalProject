package fighter.information.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Fighter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id", nullable = true)
	private Gym gym;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "fighter_style",
			joinColumns = @JoinColumn(name = "fighter_id"),
			inverseJoinColumns = @JoinColumn(name = "style_id")
			)
	private Set<Style> styles = new HashSet<>();
}
