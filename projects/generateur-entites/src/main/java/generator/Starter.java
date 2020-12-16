package generator;

import java.util.ArrayList;

import generator.model.Person;
import generator.services.RandomPersonFactory;
import generator.services.SqlFileWriter;

public class Starter {

	public static void main(String[] args) throws Exception {
		int PERSON_COUNT = 100_000;
		int MAX_INSERT_SIZE = 5_000;
		int MAX_INSERT_QUERIES_PER_FILE = 100;

		ArrayList<Person> persons;
		SqlFileWriter sqlFileWriter = new SqlFileWriter();
		RandomPersonFactory personFactory = new RandomPersonFactory();
		personFactory.init();

		int jobCount = (int) Math.ceil(PERSON_COUNT / MAX_INSERT_SIZE);
		int insertQueriesInCurrentFile = 0;
		int currentFileNumber = 0;

		// Pour chaque requête d'insertion de personnes
		for (int j = 0; j < jobCount; j++) {
			persons = new ArrayList<Person>(MAX_INSERT_SIZE);
			int startIndex = j * MAX_INSERT_SIZE;
			int endIndex = startIndex + MAX_INSERT_SIZE;

			// Pour chaque personne à créer
			for (int i = startIndex; i < endIndex; i++) {
				persons.add(personFactory.create());
			}

			if (insertQueriesInCurrentFile == MAX_INSERT_QUERIES_PER_FILE) {
				currentFileNumber++;
				insertQueriesInCurrentFile = 0;
			}
			insertQueriesInCurrentFile++;

			String fileName = "insertQueries-" + currentFileNumber + ".sql";
			sqlFileWriter.printAllInsertQueries(persons, fileName);
			System.out.println("Starter : "
					+ insertQueriesInCurrentFile + " insert queries written in file "
					+ fileName);
		}
	}
}
