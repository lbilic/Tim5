package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Show;

import java.util.List;

public interface ShowService {

    Show save(ShowCreateDTO showCreateDTO, Account user);

    void delete(ShowCreateDTO dto, Account user);

    public List<Show> findAll();
}
