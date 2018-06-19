package ftn.isamrs.tim5;

import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.BoughtProps;
import ftn.isamrs.tim5.repository.AccountRepository;
import ftn.isamrs.tim5.repository.BoughtPropsRepository;
import ftn.isamrs.tim5.service.BoughtPropsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BoughtPropsTest {

    @Autowired
    BoughtPropsService boughtPropsService;

    @MockBean
    BoughtPropsRepository boughtPropsRepository;

    @MockBean
    AccountRepository accountRepository;

    @Test
    public void testSaveBoughtProps(){

        BoughtProps boughtProps = new BoughtProps((long) 10, "pera", "pera", 1);
        Account account = new Account("pera", "pera");

        account.setBoughtProps(new ArrayList<>());

        boughtPropsService.saveBoughtProp(boughtProps, account);

        assertEquals(1, account.getBoughtProps().size());
        assertEquals(boughtProps.getAccount(), account);
        assertEquals(account.getBoughtProps().get(0), boughtProps);
    }

}
