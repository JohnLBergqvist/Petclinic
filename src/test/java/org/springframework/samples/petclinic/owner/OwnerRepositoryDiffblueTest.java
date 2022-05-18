package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { OwnerRepository.class })
@EnableAutoConfiguration
@EntityScan(basePackages = { "org.springframework.samples.petclinic.owner" })
@DataJpaTest
class OwnerRepositoryDiffblueTest {

	@Autowired
	private OwnerRepository ownerRepository;

	/**
	 * Method under test: {@link OwnerRepository#findPetTypes()}
	 */
	@Test
	void testFindPetTypes() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");

		Owner owner1 = new Owner();
		owner1.setAddress("42 Main St");
		owner1.setCity("Oxford");
		owner1.setFirstName("Jane");
		owner1.setLastName("Doe");
		owner1.setTelephone("4105551212");
		this.ownerRepository.save(owner);
		this.ownerRepository.save(owner1);

		// Act and Assert
		assertEquals(6, this.ownerRepository.findPetTypes().size());
	}

	/**
	 * Method under test: {@link OwnerRepository#findByLastName(String, Pageable)}
	 */
	@Test
	void testFindByLastName() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");

		Owner owner1 = new Owner();
		owner1.setAddress("42 Main St");
		owner1.setCity("Oxford");
		owner1.setFirstName("Jane");
		owner1.setLastName("Doe");
		owner1.setTelephone("4105551212");
		this.ownerRepository.save(owner);
		this.ownerRepository.save(owner1);

		// Act and Assert
		assertTrue(this.ownerRepository.findByLastName("foo", Pageable.unpaged()).toList().isEmpty());
	}

	/**
	 * Method under test: {@link OwnerRepository#findById(Integer)}
	 */
	@Test
	void testFindById() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		this.ownerRepository.save(owner);

		Owner owner1 = new Owner();
		owner1.setAddress("42 Main St");
		owner1.setCity("Oxford");
		owner1.setFirstName("Jane");
		owner1.setLastName("Doe");
		owner1.setTelephone("4105551212");
		this.ownerRepository.save(owner1);

		Owner owner2 = new Owner();
		owner2.setAddress("42 Main St");
		owner2.setCity("Oxford");
		owner2.setFirstName("Jane");
		owner2.setLastName("Doe");
		owner2.setTelephone("4105551212");
		this.ownerRepository.save(owner2);

		// Act and Assert
		assertSame(owner2, this.ownerRepository.findById(owner2.getId()));
	}

	/**
	 * Method under test: {@link OwnerRepository#save(Owner)}
	 */
	@Test
	void testSave() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");

		Owner owner1 = new Owner();
		owner1.setAddress("42 Main St");
		owner1.setCity("Oxford");
		owner1.setFirstName("Jane");
		owner1.setLastName("Doe");
		owner1.setTelephone("4105551212");

		Owner owner2 = new Owner();
		owner2.setAddress("42 Main St");
		owner2.setCity("Oxford");
		owner2.setFirstName("Jane");
		owner2.setLastName("Doe");
		owner2.setTelephone("4105551212");
		this.ownerRepository.save(owner);
		this.ownerRepository.save(owner1);
		this.ownerRepository.save(owner2);

		// Act
		this.ownerRepository.save(owner2);

		// Assert
		List<PetType> findPetTypesResult = this.ownerRepository.findPetTypes();
		assertEquals(6, findPetTypesResult.size());
		PetType getResult = findPetTypesResult.get(4);
		assertFalse(getResult.isNew());
		PetType getResult1 = findPetTypesResult.get(2);
		assertFalse(getResult1.isNew());
		assertEquals("dog", getResult1.getName());
		assertEquals(2, getResult1.getId().intValue());
		assertEquals("lizard", getResult.getName());
		assertEquals(3, getResult.getId().intValue());
		PetType getResult2 = findPetTypesResult.get(3);
		assertEquals("hamster", getResult2.getName());
		assertEquals(6, getResult2.getId().intValue());
		PetType getResult3 = findPetTypesResult.get(1);
		assertEquals("cat", getResult3.getName());
		assertEquals(1, getResult3.getId().intValue());
		PetType getResult4 = findPetTypesResult.get(5);
		assertEquals("snake", getResult4.getName());
		assertEquals(4, getResult4.getId().intValue());
		PetType getResult5 = findPetTypesResult.get(0);
		assertEquals("bird", getResult5.getName());
		assertEquals(5, getResult5.getId().intValue());
		assertFalse(getResult4.isNew());
		assertFalse(getResult5.isNew());
		assertFalse(getResult2.isNew());
		assertFalse(getResult3.isNew());
	}

	/**
	 * Method under test: {@link OwnerRepository#findAll(Pageable)}
	 */
	@Test
	void testFindAll() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");

		Owner owner1 = new Owner();
		owner1.setAddress("42 Main St");
		owner1.setCity("Oxford");
		owner1.setFirstName("Jane");
		owner1.setLastName("Doe");
		owner1.setTelephone("4105551212");
		this.ownerRepository.save(owner);
		this.ownerRepository.save(owner1);

		// Act and Assert
		assertEquals(12, this.ownerRepository.findAll(Pageable.unpaged()).toList().size());
	}

}
