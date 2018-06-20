package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Show;

import java.util.List;

public interface ShowService {

    Show save(ShowCreateDTO showCreateDTO, Account user);

    // check?
    void delete(ShowCreateDTO dto, Account user);

    void deleteShow (ShowCreateDTO dto);

    Show updateShow (ShowCreateDTO dto);

    List<Show> findAll();

    Show findById(Long id);

    List<Show> findByCineterId(Long cineter_id);
}
