package person;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private Date dateOfBirth;
	private String password;

}
