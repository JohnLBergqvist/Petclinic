package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PetDiffblueTest {

	/**
	 * Method under test: {@link Pet#addVisit(Visit)}
	 */
	@Test
	void testAddVisit() {
		// Arrange
		Pet pet = new Pet();

		Visit visit = new Visit();
		visit.setDate(LocalDate.ofEpochDay(1L));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		// Act
		pet.addVisit(visit);

		// Assert
		assertEquals(1, pet.getVisits().size());
	}

	/**
	 * Methods under test:
	 *
	 * <ul>
	 * <li>default or parameterless constructor of {@link Pet}
	 * <li>{@link Pet#setBirthday(LocalDate)}
	 * <li>{@link Pet#setType(PetType)}
	 * </ul>
	 */
	@Test
	void testConstructor() {
		// Arrange and Act
		Pet actualPet = new Pet();
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualPet.setBirthday(ofEpochDayResult);
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");
		actualPet.setType(petType);

		// Assert
		assertSame(ofEpochDayResult, actualPet.getBirthday());
		assertSame(petType, actualPet.getType());
	}

}
