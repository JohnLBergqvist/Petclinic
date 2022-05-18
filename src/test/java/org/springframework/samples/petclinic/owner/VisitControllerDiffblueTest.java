package org.springframework.samples.petclinic.owner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
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

@ContextConfiguration(classes = { VisitController.class })
@ExtendWith(SpringExtension.class)
class VisitControllerDiffblueTest {

	@MockBean
	private OwnerRepository ownerRepository;

	@Autowired
	private VisitController visitController;

	/**
	 * Method under test: {@link VisitController#initNewVisitForm(int, java.util.Map)}
	 */
	@Test
	void testInitNewVisitForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/owners/{ownerId}/pets/{petId}/visits/new", "Uri Vars", "Uri Vars", "Uri Vars");

		// Act
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.visitController).build()
				.perform(requestBuilder);

		// Assert
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
	}

	/**
	 * Method under test:
	 * {@link VisitController#processNewVisitForm(Owner, int, Visit, org.springframework.validation.BindingResult)}
	 */
	@Test
	void testProcessNewVisitForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findById((Integer) any())).thenReturn(owner);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/owners/{ownerId}/pets/{petId}/visits/new", "Uri Vars", "Uri Vars", "Uri Vars");

		// Act
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.visitController).build()
				.perform(requestBuilder);

		// Assert
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
