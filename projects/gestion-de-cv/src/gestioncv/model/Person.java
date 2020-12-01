package gestioncv.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, length = 255)
	private String lastName;

	@Column(nullable = false, length = 255)
	private String firstName;

	@Column(length = 255)
	private String website;

	@Column(nullable = false, length = 255, unique = true)
	private String email;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false, length = 255)
	private String password;

	@OneToMany(cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REMOVE
	}, fetch = FetchType.LAZY, mappedBy = "owner")
	private Collection<Activity> cv;
}
