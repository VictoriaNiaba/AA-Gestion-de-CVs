package generator.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.text.TextStringBuilder;

import generator.model.Activity;
import generator.model.Person;

public class SqlFileWriter {

	public void printAllInsertQueries(ArrayList<Person> persons, String fileName) throws IOException {
		String personInsertQueries = buildPersonInsertQueries(persons);
		String activityInsertQueries = buildActivityInsertQueries(persons);

		FileOutputStream out = new FileOutputStream(new File(fileName), true);
		PrintWriter printWriter = new PrintWriter(out);

		printWriter.print(personInsertQueries);
		printWriter.print(activityInsertQueries);

		printWriter.close();
	}

	private String buildPersonInsertQueries(ArrayList<Person> persons) {
		TextStringBuilder builder = new TextStringBuilder();

		builder.appendln("INSERT INTO Person")
				.appendln("(id, lastName, firstName, website, email, dateOfBirth, password)")
				.appendln("VALUES");

		for (Person person : persons) {
			builder.appendln("('%d', '%s', '%s', '%s', '%s', '%s', '%s'),",
					person.getId(),
					escapeSingleQuotes(person.getLastName()),
					escapeSingleQuotes(person.getFirstName()),
					escapeSingleQuotes(person.getWebsite()),
					escapeSingleQuotes(person.getEmail()),
					person.getDateOfBirth(),
					person.getPassword());
		}

		int lastCommaIndex = builder.lastIndexOf(',');
		builder.replace(lastCommaIndex, lastCommaIndex + 1, ";");

		return builder.build();
	}

	private String buildActivityInsertQueries(ArrayList<Person> persons) {
		TextStringBuilder builder = new TextStringBuilder();

		builder.appendln("INSERT INTO Activity")
				.appendln("(id, year, title, description, webAddress, nature, ownerId)")
				.appendln("VALUES");

		for (Person person : persons) {
			for (Activity activity : person.getCv()) {
				builder.appendln("('%d', '%d', '%s', '%s', '%s', '%s', '%d'),",
						activity.getId(),
						activity.getYear(),
						activity.getTitle(),
						activity.getDescription(),
						activity.getWebAddress(),
						activity.getNature(),
						activity.getOwner().getId());
			}
		}

		int lastCommaIndex = builder.lastIndexOf(',');
		builder.replace(lastCommaIndex, lastCommaIndex + 1, ";");

		return builder.build();
	}

	private String escapeSingleQuotes(String text) {
		return text.replace("'", "''");
	}
}
