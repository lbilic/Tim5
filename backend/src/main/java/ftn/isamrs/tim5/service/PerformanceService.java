package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PerformanceCreateDTO;
import ftn.isamrs.tim5.model.Performance;
import ftn.isamrs.tim5.model.Account;

import java.util.List;

public interface PerformanceService {

    Performance savePerformance(PerformanceCreateDTO dto, Long id, String time);
    List<Performance> findAllByShowId(Long id);
    List<Performance> findAll();

}
