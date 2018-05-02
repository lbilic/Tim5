package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CineterService {

    Cineter save(CineterCreateDTO dto);

    List<Cineter> findAll();
}
