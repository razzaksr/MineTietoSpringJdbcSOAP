package elements.spring.explore;

import elements.spring.explore.config.ServiceEndpoints;
import elements.spring.explore.jdbc.Bankers;
import elements.spring.explore.jdbc.BankersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import soap.services.*;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SampleSpringJdbcSoapApplicationTests {

	@Autowired
	private ServiceEndpoints serviceEndpoints;

	@MockBean
	private BankersService bankersService;

	@Test
	public void testDeleteViaId() {
		// Mock the service method
		when(bankersService.deleteOne(any(Integer.class))).thenReturn("Deleted");

		// Perform the request
		DeleteViaIdRequest request = new DeleteViaIdRequest();
		request.setId(123);
		DeleteViaIdResponse response = serviceEndpoints.deleteViaIdResponse(request);

		// Verify the service method call
		verify(bankersService).deleteOne(123);

		// Assertions
		assertThat(response.getServiceStatus().getStatus()).isEqualTo("Success");
		assertThat(response.getServiceStatus().getMessage()).isEqualTo("Deleted");
	}

	@Test
	public void testGetById() {
		// Mock the service method
		when(bankersService.listOne(any(Integer.class))).thenReturn(Optional.of(new Bankers()));

		// Perform the request
		GetByIdRequest request=new GetByIdRequest();
		request.setId(123);
		GetByIdResponse response = serviceEndpoints.getByIdResponse(request);

		// Assertions
		assertThat(response.getBanker()).isNotNull();
	}

	@Test
	public void testListBankers() {
		// Mock the service method
		when(bankersService.listAll()).thenReturn(Collections.singletonList(new elements.spring.explore.jdbc.Bankers()));

		// Perform the request
		ListBankersResponse response = serviceEndpoints.listBankersResponse(new ListBankersRequest());

		// Assertions
		assertThat(response.getServiceStatus().getStatus()).isEqualTo("SUCCESS");
		assertThat(response.getBankers()).isNotEmpty();
	}

	@Test
	public void testNewOfficial() {
		// Define the request
		NewBankersRequest request = new NewBankersRequest();
		request.setBankers(new soap.services.Bankers());

		// Perform the request
		NewBankersResponse response = serviceEndpoints.newOfficialResponse(request);

		// Assertions
		assertThat(response.getServiceStatus().getStatus()).isEqualTo("Success");
		assertThat(response.getServiceStatus().getMessage()).isEqualTo("Object Inserted");
		assertThat(response.getBankers()).isEqualTo(request.getBankers());
	}

}
