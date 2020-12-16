package generator.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int year;
	private String title;
	private String description;
	private String webAddress;
	private Nature nature;
	private Person owner;
}
