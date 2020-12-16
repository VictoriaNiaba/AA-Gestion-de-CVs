package generator.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String lastName;

	private String firstName;

	private String website;

	private String email;

	private LocalDate dateOfBirth;

	private String password;

	private Collection<Activity> cv;
}
