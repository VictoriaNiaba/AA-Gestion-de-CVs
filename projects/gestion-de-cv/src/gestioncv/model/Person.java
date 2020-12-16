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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Size(min = 2, max = 255, message = "le nom doit contenir entre 2 et 255 caractères")
	@NotNull(message = "le nom est requis")
	@Column(nullable = false, length = 255)
	private String lastName;

	@Size(min = 2, max = 255, message = "le prénom doit contenir entre 2 et 255 caractères")
	@NotNull(message = "le prénom est requis")
	@Column(nullable = false, length = 255)
	private String firstName;

	@Max(value = 255, message = "le site web ne doit pas contenir plus de 255 caractères")
	@Column(length = 255)
	// TODO: valider le site web
	private String website;

	@NotNull(message = "l'email est requis")
	@Email(message = "merci de saisir un email valide")
	@Column(nullable = false, length = 255, unique = true)
	private String email;

	@NotNull(message = "la date de naissance est requise")
	@Column(nullable = false)
	private LocalDate dateOfBirth;

	// TODO: valider le mot de passe lorsque le hachage aura été défini
	@Column(nullable = false, length = 102)
	private String password;

	@OneToMany(cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REMOVE
	}, fetch = FetchType.LAZY, mappedBy = "owner")
	private Collection<Activity> cv;
}
