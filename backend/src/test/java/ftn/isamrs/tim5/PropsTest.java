package ftn.isamrs.tim5;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.repository.AccountRepository;
import ftn.isamrs.tim5.repository.BoughtPropsRepository;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.repository.PropsRepository;
import ftn.isamrs.tim5.service.BoughtPropsService;
import ftn.isamrs.tim5.service.PropsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PropsTest {

    @MockBean
    PropsService propsService;

    @MockBean
    PropsRepository propsRepository;

    @MockBean
    CineterRepository cineterRepository;

    @MockBean
    AccountRepository accountRepository;

    Props props;
    PropsCreateDTO propsCreateDTO;
    Cineter cineter;
    CineterAdmin admin;
    @Before
    public void setUp()
    {
        cineter = new Cineter("Pera", "Pera", "AAA", new ArrayList<>(), false, 5.0f);
        admin = new CineterAdmin("jova", "jova", cineter, false, true);
        this.props = new Props("AAAA", 200f, "opis", cineter, 200, admin, 0);
         propsCreateDTO = new PropsCreateDTO(2L, "pera",
                200f,"opis", 100, 0, new Date(2100-1-1));

        given(
                this.propsService.saveProps(propsCreateDTO, admin)
        ).willReturn(
                props
        );
    }

    @Test
    public void testSaveProps(){

        Props props = propsService.saveProps(propsCreateDTO, admin);
        assertNotNull(props);
        assertEquals(props.getAccount(), this.admin);
        assertEquals(props.getCineter(), cineter);
    }

}
