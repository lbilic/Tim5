package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.repository.TheaterAdminRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TheaterAdminRepository theaterAdminRepository;

    @Autowired
    private CineterRepository cineterRepository;

    @Override
    @Transactional
    public CineterAdmin saveTheaterAdmin(CineterAdminCreateDTO dto) {

        CineterCreateDTO cdto = dto.getCineter();

        Cineter cineter = cineterRepository.findByAddressAndCityAndName(cdto.getAddress(), cdto.getCity(), cdto.getName());
        if(cineter == null) try {
            throw new Exception("FASFIJOASFIOJAS");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CineterAdmin admin = ConvertDTOToModel.convertCineterAdminDTOtoCineterAdmin(dto);
        admin.setCineter(cineter);

        cineter.getCineterAdmin().add(admin);
        cineterRepository.save(cineter);
        return theaterAdminRepository.save(admin);
    }

    @Override
    public CineterAdmin saveTheaterAdmin(CineterAdmin admin) {
        return theaterAdminRepository.save(admin);
    }

    @Override
    public List<CineterAdmin> findFanZoneAdmins(Long id) {
        return theaterAdminRepository.findFanZoneAdmins(id);
    }
}
