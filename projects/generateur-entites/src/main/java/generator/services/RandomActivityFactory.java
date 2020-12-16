package generator.services;

import java.time.LocalDate;

import org.apache.commons.lang3.RandomUtils;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import generator.model.Activity;
import generator.model.Nature;
import generator.model.Person;

public class RandomActivityFactory {

	private Lorem lorem = LoremIpsum.getInstance();
	private static int availableId = 1;

	private final static int MIN_AGE = 16;

	public Activity create(Person owner) {
		Activity activity = new Activity();

		activity.setId(createId());
		activity.setOwner(owner);
		activity.setYear(createYear(owner));
		activity.setTitle(createTitle());
		activity.setDescription(createDescription());
		activity.setWebAddress(createWebAddress(activity.getTitle()));
		activity.setNature(createNature());

		return activity;
	}

	private int createId() {
		return availableId++;
	}

	public int createYear(Person owner) {
		int minYear = owner.getDateOfBirth().getYear() + MIN_AGE;
		int maxYear = LocalDate.now().getYear();

		return RandomUtils.nextInt(minYear, maxYear - 1);
	}

	public String createTitle() {
		return lorem.getTitle(2, 4);
	}

	public String createDescription() {
		return lorem.getHtmlParagraphs(2, 3);
	}

	private String createWebAddress(String title) {
		String escapedTitle = title.replace(' ', '-').toLowerCase();
		return "https://www." + escapedTitle + ".com";
	}

	private Nature createNature() {
		Nature[] natures = Nature.values();
		int randomIndex = RandomUtils.nextInt(0, natures.length);
		return natures[randomIndex];
	}
}
