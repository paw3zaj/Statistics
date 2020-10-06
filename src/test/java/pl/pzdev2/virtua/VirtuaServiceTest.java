package pl.pzdev2.virtua;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.pzdev2.virtua.interfaces.VirtuaRepository;

class VirtuaServiceTest {

	private List<Virtua> vL1;
	private List<Virtua> vL2;
	private VirtuaRepository virtuaRepository;
	private VirtuaService virtuaService;

    @BeforeEach
    void initializerOrder() {
        vL1 = prepareVirtuaData();
		vL2 = prepareVirtuaData();
		virtuaRepository = mock(VirtuaRepository.class);
		virtuaService = new VirtuaService(virtuaRepository, vL1, vL2);
    }

    @AfterEach
    void cleanUp() {
        vL1.clear();
        vL2.clear();
    }
    
	@Test
	void twoVirtuaListsShouldBeEmpty() {
		//given
		//when
		virtuaService.removeTheSameElements(vL1, vL2);
		
		//then
		assertThat(vL1, is(vL2));
		assertThat(vL1, hasSize(0));
	}
	
	@Test
	void twoVirtuaListsShouldNotBeTheSame() {
		//given
		Virtua v = Virtua.builder().idVirtua(4L).signature("signature3").barcode("barcode3").build();
		vL2.add(v);
		
		//when
		virtuaService.removeTheSameElements(vL1, vL2);
		
		//then
		assertThat(vL1, is(not(vL2)));
		assertThat(vL2, hasSize(1));
		assertThat(vL2, contains(v));
	}
	
	@Test
	void idVirtuaShouldBeSeparatedFromVirtuaObject() {
		//given
		//when
		List<Long> idList = virtuaService.idSeparate(vL2);
		
		//then
		assertThat(idList, hasSize(3));
		assertThat(idList.get(0), equalTo(1L));
		assertThat(idList.get(2), equalTo(3L));
	}
	
	private List<Virtua> prepareVirtuaData() {
		Virtua v1 = Virtua.builder().idVirtua(1L).signature("signature1").barcode("barcode1").build();
		Virtua v2 = Virtua.builder().idVirtua(2L).signature("signature2").barcode("barcode2").build();
		Virtua v3 = Virtua.builder().idVirtua(3L).signature("signature3").barcode("barcode3").build();
		
		List<Virtua> vL = new ArrayList<>();	//Arrays.asList(v1, v2, v3);
		
		vL.add(v1);
		vL.add(v2);
		vL.add(v3);
		
		return vL;
	}

}
