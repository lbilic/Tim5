package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PerformanceCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.repository.AccountRepository;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.repository.PerformanceRepository;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;


    @Autowired
    private ShowRepository showRepository;

    @Override
    @Transactional
    public Performance savePerformance(PerformanceCreateDTO dto, Long id) {
        System.out.println(dto);
        Performance performance = ConvertDTOToModel.convertPerformanceCreateDTOtoPerformance(dto);

        performance.setShow(showRepository.findById(id).get());

        /*ShowCreateDTO showDTO = dto.getShow();

        Show show = showRepository.findByNameAndDescription(showDTO.getName(), showDTO.getDescription());
        if(show == null) try {
            throw new Exception("Null- show");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //performance.setShow(show);
        //show.getPerformances().add(performance);

        //showRepository.save(show);
        return performanceRepository.save(performance);
    }
}
