package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { PetTypeFormatter.class })
@ExtendWith(SpringExtension.class)
class PetTypeFormatterDiffblueTest {

	@MockBean
	private OwnerRepository ownerRepository;

	@Autowired
	private PetTypeFormatter petTypeFormatter;

	/**
	 * Method under test: {@link PetTypeFormatter#print(PetType, Locale)}
	 */
	@Test
	void testPrint() {
		// Arrange
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		// Act and Assert
		assertEquals("Dog", this.petTypeFormatter.print(petType, Locale.getDefault()));
	}

	/**
	 * Method under test: {@link PetTypeFormatter#print(PetType, Locale)}
	 */
	@Test
	void testPrint2() {
		// Arrange
		PetType petType = mock(PetType.class);
		when(petType.getName()).thenReturn("Bella");
		doNothing().when(petType).setId((Integer) any());
		doNothing().when(petType).setName((String) any());
		petType.setId(1);
		petType.setName("Dog");

		// Act and Assert
		assertEquals("Bella", this.petTypeFormatter.print(petType, Locale.getDefault()));
		verify(petType).getName();
		verify(petType).setId((Integer) any());
		verify(petType).setName((String) any());
	}

	/**
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	void testParse() throws ParseException {
		// Arrange
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());

		// Act and Assert
		assertThrows(ParseException.class, () -> this.petTypeFormatter.parse("Dog", Locale.getDefault()));
		verify(this.ownerRepository).findPetTypes();
	}

	/**
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	void testParse2() throws ParseException {
		// Arrange
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		ArrayList<PetType> petTypeList = new ArrayList<>();
		petTypeList.add(petType);
		when(this.ownerRepository.findPetTypes()).thenReturn(petTypeList);

		// Act and Assert
		assertSame(petType, this.petTypeFormatter.parse("Dog", Locale.getDefault()));
		verify(this.ownerRepository).findPetTypes();
	}

	/**
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	void testParse3() throws ParseException {
		// Arrange
		PetType petType = mock(PetType.class);
		when(petType.getName()).thenReturn("Bella");
		doNothing().when(petType).setId((Integer) any());
		doNothing().when(petType).setName((String) any());
		petType.setId(1);
		petType.setName("Dog");

		ArrayList<PetType> petTypeList = new ArrayList<>();
		petTypeList.add(petType);
		when(this.ownerRepository.findPetTypes()).thenReturn(petTypeList);

		// Act and Assert
		assertThrows(ParseException.class, () -> this.petTypeFormatter.parse("Dog", Locale.getDefault()));
		verify(this.ownerRepository).findPetTypes();
		verify(petType).getName();
		verify(petType).setId((Integer) any());
		verify(petType).setName((String) any());
	}

}
