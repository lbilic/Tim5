package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.repository.TheaterAdminRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TheaterAdminRepository theaterAdminRepository;

    @Override
    public CineterAdmin saveTheaterAdmin(CineterAdminCreateDTO dto) {
        CineterAdmin admin = ConvertDTOToModel.convertCineterAdminDTOtoCineterAdmin(dto);

        return theaterAdminRepository.save(admin);
    }
}
