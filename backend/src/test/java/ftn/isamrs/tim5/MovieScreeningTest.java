package ftn.isamrs.tim5;

import ftn.isamrs.tim5.dto.HallCreateDTO;
import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.HallRepository;
import ftn.isamrs.tim5.repository.MovieScreeningRepository;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.service.MovieScreeningService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieScreeningTest {


    @MockBean
    private HallRepository hallRepository;

    @MockBean
    private MovieScreeningRepository movieScreeningRepository;

    @MockBean
    private ShowRepository showRepository;

    @MockBean
    private MovieScreeningService movieScreeningService;

    MovieScreeningCreateDTO dto = new MovieScreeningCreateDTO(new Date(), 200, "2D",
            new HallCreateDTO(20L, 20, 20, 20));

    @Before
    public void setUp() {//String name, String address, String city, List<Props> props, boolean isTheater, double score
        Cineter cineter = new Cineter(10L, "aa", "n", "a", new ArrayList<>(), true, 4);
        Hall hall = new Hall(20L, 10, 10, 10, cineter);
        Show show = new Show(200L, "A", "A", true, new ArrayList<>());

        MovieScreening movieScreening = new MovieScreening(new Date(), 200, "2D", hall);


        given(
                this.hallRepository.findById(hall.getId())
        ).willReturn(
                Optional.of(hall)
        );

        given(
                this.showRepository.findById(show.getId())
        ).willReturn(
                Optional.of(show)
        );

        given(
                this.movieScreeningRepository.findById(movieScreening.getId())
        ).willReturn(
                Optional.of(movieScreening)
        );

        given(this.movieScreeningService.saveMovieScreening(dto, 200L, "22:44")).willReturn(
                movieScreening
        );
    }


    @Test
    public void testSaveMovieScreening() {

        MovieScreening movieScreening = this.movieScreeningService.saveMovieScreening(dto, 200L, "22:44");

        assertEquals(movieScreening.getHall(), this.hallRepository.findById(20L).get());
        assertEquals(movieScreening.getId(), null);
        assertEquals(movieScreening.getType(), "2D");
    }
}
