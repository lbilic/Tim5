package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import org.springframework.stereotype.Service;

public interface CineterService {

    Cineter save(CineterCreateDTO dto);
}
