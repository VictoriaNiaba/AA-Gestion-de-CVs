package generator.services;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.RandomUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import generator.Starter;
import generator.model.Activity;
import generator.model.Person;

public class RandomPersonFactory {

	private static String[] firstNames;
	private static String[] lastNames;
	private static RandomActivityFactory activityFactory;
	private static int availableId = 1;

	private final static String DEFAULT_PASSWORD = "password";
	private final static int AGE_MIN = 18, AGE_MAX = 40;
	private final static int ACTIVITIES_MIN = 2, ACTIVITIES_MAX = 6;

	public RandomPersonFactory() {
		activityFactory = new RandomActivityFactory();
	}

	public void init() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		InputStream is = Starter.class.getClassLoader().getResourceAsStream("firstNames.json");
		firstNames = mapper.readValue(is, String[].class);

		InputStream it = Starter.class.getClassLoader().getResourceAsStream("lastNames.json");
		lastNames = mapper.readValue(it, String[].class);
	}

	public Person create() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Person person = new Person();

		person.setId(createId());
		person.setFirstName(createFirstName());
		person.setLastName(createLastName());
		person.setDateOfBirth(createDateOfBirth());
		person.setEmail(createEmail(person.getFirstName(), person.getLastName()));
		person.setWebsite(createWebsite(person.getFirstName(), person.getLastName()));
		person.setPassword(createPassword());
		person.setCv(createCv(person));

		return person;
	}

	private Collection<Activity> createCv(Person person) {
		ArrayList<Activity> activities = new ArrayList<Activity>();
		int activitiesNumber = RandomUtils.nextInt(
				ACTIVITIES_MIN,
				ACTIVITIES_MAX);

		for (int i = 0; i < activitiesNumber; i++) {
			activities.add(activityFactory.create(person));
		}

		return activities;
	}

	private int createId() {
		return availableId++;
	}

	private LocalDate createDateOfBirth() {
		int currentYear = LocalDate.now().getYear();

		LocalDate dateMin = LocalDate.of(currentYear - AGE_MAX, Month.JANUARY, 1);
		LocalDate dateMax = LocalDate.of(currentYear - AGE_MIN, Month.DECEMBER, 31);
		long dateMinAsEpochDay = dateMin.toEpochDay();
		long dateMaxAsEpochDay = dateMax.toEpochDay();

		long randomEpochDay = RandomUtils.nextLong(
				dateMinAsEpochDay,
				dateMaxAsEpochDay + 1);
		LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
		return randomDate;
	}

	private String createPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return PasswordHash.createHash(DEFAULT_PASSWORD);
	}

	private static String createFirstName() {
		int randomIndex = RandomUtils.nextInt(0, firstNames.length);
		return firstNames[randomIndex];
	}

	private static String createLastName() {
		int randomIndex = RandomUtils.nextInt(0, lastNames.length);
		return lastNames[randomIndex];
	}

	private static String createEmail(String firstName, String lastName) {
		String email = firstName + "." + lastName + "@gmail.com";
		return escapeText(email).toLowerCase();
	}

	private static String createWebsite(String firstName, String lastName) {
		String website = "https://www." + firstName + "-" + lastName + ".com";
		return escapeText(website).toLowerCase();
	}

	private static String escapeText(String text) {
		return text.replace(' ', '-');
	}
}
