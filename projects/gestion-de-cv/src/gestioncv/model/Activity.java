package gestioncv.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "l'année est requise")
	@Size(min = 4, max = 4, message = "l'année doit contenir exactement 4 chiffres")
	@Column(nullable = false, length = 4)
	private int year;

	@Size(min = 2, max = 255, message = "le titre doit contenir entre 2 et 255 caractères")
	@NotNull(message = "le titre est requis")
	@Column(nullable = false, length = 255)
	private String title;

	@Size(min = 2, max = 10_000, message = "la description doit contenir entre 2 et 10 000 caractères")
	@Column(length = 10_000)
	private String description;

	@Max(value = 255, message = "l'adresse web ne doit pas contenir plus de 255 caractères")
	// TODO: valider l'adresse web
	@Column(length = 255)
	private String webAddress;

	@Enumerated(EnumType.STRING)
	private Nature nature;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ownerId")
	private Person owner;
}
