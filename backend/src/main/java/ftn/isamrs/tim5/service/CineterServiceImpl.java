package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CineterServiceImpl implements CineterService {

    @Autowired
    private CineterRepository cineterRepository;

    @Override
    @Transactional(rollbackOn=Exception.class)
    public Cineter save(CineterCreateDTO dto) {
        Cineter cineter = ConvertDTOToModel.convertLogDTOToLog(dto);

        cineter = cineterRepository.save(cineter);
        System.out.println(cineter.getId());
        return cineter;
    }
}
