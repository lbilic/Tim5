package ftn.isamrs.tim5;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.service.ShowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowTest {

    @MockBean
    private ShowRepository showrepository;

    @Autowired
    private ShowService showService;

    @Before
    public void setUp()
    {
        Show show = new Show(20L, "AA", "FSA", true, new ArrayList<>());

        given(
                this.showrepository.findById(20L)
        ).willReturn(
                Optional.of(show)
        );

        given(
                this.showrepository.save(show)
        ).willReturn(
                show
        );
    }


    @Test
    public void testUpdateShow()
    {
        ShowCreateDTO showCreateDTO = new ShowCreateDTO(20L, "FSA", "FSA", true);
        Show s = this.showService.updateShow(showCreateDTO);
        assertEquals("FSA", s.getName());
        assertEquals("FSA", s.getDescription());
    }
}
