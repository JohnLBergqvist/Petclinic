package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

class PetValidatorDiffblueTest {

	/**
	 * Method under test:
	 * {@link PetValidator#validate(Object, org.springframework.validation.Errors)}
	 */
	@Test
	void testValidate() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthday(LocalDate.ofEpochDay(1L));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(petType);

		// Act
		petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet"));

		// Assert that nothing has changed
		assertEquals("1970-01-02", pet.getBirthday().toString());
		assertFalse(pet.isNew());
		assertTrue(pet.getVisits().isEmpty());
		assertSame(petType, pet.getType());
		assertEquals("Bella", pet.getName());
	}

	/**
	 * Method under test:
	 * {@link PetValidator#validate(Object, org.springframework.validation.Errors)}
	 */
	@Test
	void testValidate2() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthday(null);
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(petType);
		BindException bindException = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, bindException);

		// Assert
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthday': rejected"
				+ " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthday,required"
				+ ".birthday,required.java.time.LocalDate,required]; arguments []; default message [required]",
				bindException.getLocalizedMessage());
		BindingResult bindingResult = bindException.getBindingResult();
		assertEquals(1, bindingResult.getErrorCount());
		assertSame(bindException.getPropertyEditorRegistry(),
				((BeanPropertyBindingResult) bindingResult).getPropertyAccessor());
	}

	/**
	 * Method under test:
	 * {@link PetValidator#validate(Object, org.springframework.validation.Errors)}
	 */
	@Test
	void testValidate3() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthday(LocalDate.ofEpochDay(1L));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(petType);

		// Act
		petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet"));

		// Assert that nothing has changed
		assertEquals("1970-01-02", pet.getBirthday().toString());
		assertTrue(pet.isNew());
		assertTrue(pet.getVisits().isEmpty());
		assertSame(petType, pet.getType());
		assertEquals("Bella", pet.getName());
	}

	/**
	 * Method under test:
	 * {@link PetValidator#validate(Object, org.springframework.validation.Errors)}
	 */
	@Test
	void testValidate4() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthday(LocalDate.ofEpochDay(1L));
		pet.setId(1);
		pet.setName(null);
		pet.setType(petType);
		BindException bindException = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, bindException);

		// Assert
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
				+ " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
				+ ".java.lang.String,required]; arguments []; default message [required]",
				bindException.getLocalizedMessage());
		BindingResult bindingResult = bindException.getBindingResult();
		assertEquals(1, bindingResult.getErrorCount());
		assertSame(bindException.getPropertyEditorRegistry(),
				((BeanPropertyBindingResult) bindingResult).getPropertyAccessor());
	}

	/**
	 * Method under test: {@link PetValidator#supports(Class)}
	 */
	@Test
	void testSupports() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		// Act and Assert
		assertFalse(petValidator.supports(Object.class));
	}

	/**
	 * Method under test: {@link PetValidator#supports(Class)}
	 */
	@Test
	void testSupports2() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		// Act and Assert
		assertTrue(petValidator.supports(Pet.class));
	}

}
