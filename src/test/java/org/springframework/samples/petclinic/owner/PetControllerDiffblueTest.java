package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { PetController.class })
@ExtendWith(SpringExtension.class)
class PetControllerDiffblueTest {

	@MockBean
	private OwnerRepository ownerRepository;

	@Autowired
	private PetController petController;

	/**
	 * Method under test: {@link PetController#populatePetTypes()}
	 */
	@Test
	void testPopulatePetTypes() {
		// Arrange
		ArrayList<PetType> petTypeList = new ArrayList<>();
		when(this.ownerRepository.findPetTypes()).thenReturn(petTypeList);

		// Act
		Collection<PetType> actualPopulatePetTypesResult = this.petController.populatePetTypes();

		// Assert
		assertSame(petTypeList, actualPopulatePetTypesResult);
		assertTrue(actualPopulatePetTypesResult.isEmpty());
		verify(this.ownerRepository).findPetTypes();
	}

	/**
	 * Method under test: {@link PetController#findOwner(int)}
	 */
	@Test
	void testFindOwner() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);

		// Act and Assert
		assertSame(owner, this.petController.findOwner(123));
		verify(this.ownerRepository).findById((Integer) any());
	}

	/**
	 * Method under test: {@link PetController#findPet(int, Integer)}
	 */
	@Test
	void testFindPet() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);

		// Act and Assert
		assertNull(this.petController.findPet(123, 123));
		verify(this.ownerRepository).findById((Integer) any());
	}

	/**
	 * Method under test: {@link PetController#findPet(int, Integer)}
	 */
	@Test
	void testFindPet2() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);

		// Act and Assert
		assertTrue(this.petController.findPet(123, null).getVisits().isEmpty());
	}

	/**
	 * Method under test:
	 * {@link PetController#initCreationForm(Owner, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testInitCreationForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 123);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#initCreationForm(Owner, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testInitCreationForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 123);
		getResult.contentType("https://example.org/example");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(getResult)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#initUpdateForm(Owner, int, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testInitUpdateForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit",
				123, 123);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#initUpdateForm(Owner, int, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testInitUpdateForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit", 123,
				123);
		getResult.contentType("https://example.org/example");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(getResult)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, org.springframework.validation.BindingResult, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testProcessCreationForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, org.springframework.validation.BindingResult, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testProcessCreationForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123);
		postResult.contentType("https://example.org/example");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(postResult)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, org.springframework.validation.BindingResult, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testProcessCreationForm3() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123)
				.param("name", "Bella");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.petController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(3))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
				.andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, org.springframework.validation.BindingResult, Owner, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testProcessUpdateForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/*/edit",
				123);

		// Act
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.petController).build()
				.perform(requestBuilder);

		// Assert
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
	}

	/**
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, org.springframework.validation.BindingResult, Owner, org.springframework.ui.ModelMap)}
	 */
	@Test
	void testProcessUpdateForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/*/edit", 123);
		postResult.contentType("https://example.org/example");

		// Act
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.petController).build()
				.perform(postResult);

		// Assert
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
