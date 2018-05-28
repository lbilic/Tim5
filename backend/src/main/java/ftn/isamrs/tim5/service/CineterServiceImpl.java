package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void delete(CineterCreateDTO dto) {
        Optional<Cineter> cineter = cineterRepository.findById(dto.getId());
        if(!cineter.isPresent()) return;
        cineterRepository.delete(cineter.get());
    }

    @Override
    public Cineter findById(Long id) {
        Optional<Cineter> cineter = this.cineterRepository.findById(id);

        return cineter.orElse(null);

    }

    @Override
    public Cineter updateCineter(CineterCreateDTO dto) {
        Optional<Cineter> cin = this.cineterRepository.findById(dto.getId());

        if(!cin.isPresent()) return null;

        Cineter cineter = cin.get();

        cineter.setAddress(dto.getAddress());
        cineter.setCity(dto.getCity());
        cineter.setName(dto.getName());
        cineter.setTheater(dto.isTheater());


        cineter = cineterRepository.save(cineter);

        return cineter;
    }

    @Override
    public Cineter save(Cineter cineter) {
      return  cineterRepository.save(cineter);
    }
}
