package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Cineter;

import java.util.List;

public interface CineterService {

    Cineter save(CineterCreateDTO dto);

    List<Cineter> findAll();

    void delete(CineterCreateDTO cineter);

    Cineter findById(Long id);

    Cineter updateCineter(CineterCreateDTO dto);

    Cineter save(Cineter cineter);
}
