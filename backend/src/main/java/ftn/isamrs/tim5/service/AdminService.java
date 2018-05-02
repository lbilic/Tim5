package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.model.CineterAdmin;

public interface AdminService {

    CineterAdmin saveTheaterAdmin(CineterAdminCreateDTO admin);
}
