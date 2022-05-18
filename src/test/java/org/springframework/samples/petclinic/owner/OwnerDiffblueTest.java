package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class OwnerDiffblueTest {

	/**
	 * Method under test: {@link Owner#addPet(Pet)}
	 */
	@Test
	void testAddPet() {
		// Arrange
		Owner owner = new Owner();

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthday(LocalDate.ofEpochDay(1L));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(petType);

		// Act
		owner.addPet(pet);

		// Assert that nothing has changed
		assertEquals("1970-01-02", pet.getBirthday().toString());
		assertFalse(pet.isNew());
		assertTrue(pet.getVisits().isEmpty());
		assertSame(petType, pet.getType());
		assertEquals("Bella", pet.getName());
		assertTrue(owner.isNew());
		assertTrue(owner.getPets().isEmpty());
	}

	/**
	 * Method under test: {@link Owner#getPet(Integer)}
	 */
	@Test
	void testGetPet() {
		// Arrange, Act and Assert
		assertNull((new Owner()).getPet(1));
		assertNull((new Owner()).getPet("Bella"));
		assertNull((new Owner()).getPet("Bella", true));
	}

	/**
	 * Methods under test:
	 *
	 * <ul>
	 * <li>default or parameterless constructor of {@link Owner}
	 * <li>{@link Owner#setAddress(String)}
	 * <li>{@link Owner#setCity(String)}
	 * <li>{@link Owner#setTelephone(String)}
	 * </ul>
	 */
	@Test
	void testConstructor() {
		// Arrange and Act
		Owner actualOwner = new Owner();
		actualOwner.setAddress("42 Main St");
		actualOwner.setCity("Oxford");
		actualOwner.setTelephone("4105551212");

		// Assert
		assertEquals("42 Main St", actualOwner.getAddress());
		assertEquals("Oxford", actualOwner.getCity());
		assertEquals("4105551212", actualOwner.getTelephone());
	}

}
