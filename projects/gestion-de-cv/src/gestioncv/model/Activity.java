package gestioncv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, length = 4)
	private int year;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(length = 10_000)
	private String description;

	@Column(length = 255)
	private String webAddress;

	@Enumerated(EnumType.STRING)
	private Nature nature;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ownerId")
	private Person owner;
}
