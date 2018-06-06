package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.model.CineterAdmin;

import java.util.List;

public interface AdminService {

    CineterAdmin saveTheaterAdmin(CineterAdminCreateDTO admin);

    List<CineterAdmin> findFanZoneAdmins(Long id);

    CineterAdmin saveTheaterAdmin(CineterAdmin admin);
}
