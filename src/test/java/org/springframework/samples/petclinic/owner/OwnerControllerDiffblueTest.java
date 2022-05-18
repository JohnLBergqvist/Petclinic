package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { OwnerController.class })
@ExtendWith(SpringExtension.class)
class OwnerControllerDiffblueTest {

	@Autowired
	private OwnerController ownerController;

	@MockBean
	private OwnerRepository ownerRepository;

	/**
	 * Method under test: {@link OwnerController#findOwner(Integer)}
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
		assertSame(owner, this.ownerController.findOwner(123));
		verify(this.ownerRepository).findById((Integer) any());
	}

	/**
	 * Method under test: {@link OwnerController#findOwner(Integer)}
	 */
	@Test
	void testFindOwner2() {
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
		assertTrue(this.ownerController.findOwner(null).getPets().isEmpty());
	}

	/**
	 * Method under test: {@link OwnerController#initCreationForm(java.util.Map)}
	 */
	@Test
	void testInitCreationForm() {
		// Diffblue Cover was unable to write a Spring test,
		// so wrote a non-Spring test instead.
		// Reason: R005 Unable to load class.
		// Class: groovy.lang.GroovyObject
		// Please check that the class is available on your test runtime classpath.
		// See https://diff.blue/R005 to resolve this issue.

		// Arrange
		OwnerController ownerController = new OwnerController(mock(OwnerRepository.class));
		HashMap<String, Object> stringObjectMap = new HashMap<>();

		// Act and Assert
		assertEquals("owners/createOrUpdateOwnerForm", ownerController.initCreationForm(stringObjectMap));
		assertEquals(1, stringObjectMap.size());
		assertTrue(((Owner) stringObjectMap.get("owner")).getPets().isEmpty());
	}

	/**
	 * Method under test: {@link OwnerController#initFindForm(java.util.Map)}
	 */
	@Test
	void testInitFindForm() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/find");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/findOwners"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#initUpdateOwnerForm(int, org.springframework.ui.Model)}
	 */
	@Test
	void testInitUpdateOwnerForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/edit", 123);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processCreationForm(Owner, org.springframework.validation.BindingResult)}
	 */
	@Test
	void testProcessCreationForm() throws Exception {
		// Arrange
		doNothing().when(this.ownerRepository).save((Owner) any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/new")
				.param("address", "42 Main St").param("city", "Oxford").param("telephone", "4105551212")
				.param("firstName", "Jane").param("lastName", "Doe");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/owners/null"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/owners/null"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processCreationForm(Owner, org.springframework.validation.BindingResult)}
	 */
	@Test
	void testProcessCreationForm2() throws Exception {
		// Arrange
		doNothing().when(this.ownerRepository).save((Owner) any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/new").param("address", "")
				.param("city", "Oxford").param("telephone", "4105551212").param("firstName", "Jane")
				.param("lastName", "Doe");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processFindForm(int, Owner, org.springframework.validation.BindingResult, org.springframework.ui.Model)}
	 */
	@Test
	void testProcessFindForm() throws Exception {
		// Arrange
		when(this.ownerRepository.findByLastName((String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<>(new ArrayList<>()));
		MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/owners");
		MockHttpServletRequestBuilder requestBuilder = getResult.param("page", String.valueOf(1));

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/findOwners"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processFindForm(int, Owner, org.springframework.validation.BindingResult, org.springframework.ui.Model)}
	 */
	@Test
	void testProcessFindForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");

		ArrayList<Owner> ownerList = new ArrayList<>();
		ownerList.add(owner);
		PageImpl<Owner> pageImpl = new PageImpl<>(ownerList);
		when(this.ownerRepository.findByLastName((String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(pageImpl);
		MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/owners");
		MockHttpServletRequestBuilder requestBuilder = getResult.param("page", String.valueOf(1));

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/owners/1"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processFindForm(int, Owner, org.springframework.validation.BindingResult, org.springframework.ui.Model)}
	 */
	@Test
	void testProcessFindForm3() throws Exception {
		// Arrange
		when(this.ownerRepository.findByLastName((String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<>(new ArrayList<>()));
		MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/owners");
		MockHttpServletRequestBuilder requestBuilder = getResult.param("page", String.valueOf(1)).param("lastName",
				"Doe");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/findOwners"));
	}

	/**
	 * Method under test:
	 * {@link OwnerController#processUpdateOwnerForm(Owner, org.springframework.validation.BindingResult, int)}
	 */
	@Test
	void testProcessUpdateOwnerForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		doNothing().when(this.ownerRepository).save((Owner) any());
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/edit", 123)
				.param("address", "42 Main St").param("city", "Oxford").param("telephone", "4105551212")
				.param("firstName", "Jane").param("lastName", "Doe");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/owners/{ownerId}"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/owners/123"));
	}

	/**
	 * Method under test: {@link OwnerController#showOwner(int)}
	 */
	@Test
	void testShowOwner() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}", 123);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(this.ownerController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().size(1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
				.andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("owners/ownerDetails"));
	}

}
