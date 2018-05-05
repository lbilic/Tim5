package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CineterServiceImpl implements CineterService {

    @Autowired
    private CineterRepository cineterRepository;

    @Override
    public Cineter save(CineterCreateDTO dto) {

        Cineter cineter = ConvertDTOToModel.convertCineterCreateToCineter(dto);
        cineter = cineterRepository.save(cineter);
        return cineter;
    }

    @Override
    public List<Cineter> findAll() {
        return cineterRepository.findAll();
    }
}
