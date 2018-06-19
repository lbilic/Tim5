package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.model.PSBase;
import ftn.isamrs.tim5.repository.HallRepository;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isamrs.tim5.repository.MovieScreeningRepository;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private HallRepository hallRepository;


    @Override
    public MovieScreening saveMovieScreening(MovieScreeningCreateDTO dto, Long id, String time) {


        MovieScreening movieScreening =
                ConvertDTOToModel.convertMovieScreeningCreateDTOtoMovieScreening(dto);

        movieScreening.setHall(hallRepository.getOne(dto.getHall()));

        DateFormat sdf = new SimpleDateFormat("hh:mm");

        try {
            Date t=sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(t);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dto.getDate());
            calendar2.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));

            movieScreening.setDate(calendar2.getTime());
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }


        movieScreening.setShow(showRepository.findById(id).get());
        movieScreening = movieScreeningRepository.save(movieScreening);

        return movieScreening;
    }

    public List<MovieScreening> findAllByShowId(Long id){
        List <MovieScreening> movieScreenings  = movieScreeningRepository.findByShowId(id);

        return movieScreenings;
    }

}
