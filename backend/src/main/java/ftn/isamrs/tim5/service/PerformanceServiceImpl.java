package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PerformanceCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.repository.PerformanceRepository;
import ftn.isamrs.tim5.repository.HallRepository;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;


    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private HallRepository hallRepository;

    @Override
    @Transactional
    public Performance savePerformance(PerformanceCreateDTO dto, Long id, String time) {


        Performance performance = ConvertDTOToModel.convertPerformanceCreateDTOtoPerformance(dto);

        performance.setHall(hallRepository.getOne(dto.getHall()));
        performance.setShow(showRepository.findById(id).get());

        DateFormat sdf = new SimpleDateFormat("hh:mm");

        try {
            Date t=sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(t);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dto.getDate());
            calendar2.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));

            performance.setDate(calendar2.getTime());
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }
        return performanceRepository.save(performance);
    }

    public List<Performance> findAllByShowId(Long id){
        List <Performance> performances  = performanceRepository.findByShowId(id);

        return performances;
    }

    @Override
    public List<Performance> findAll() {
        return this.performanceRepository.findAll();
    }

}
