package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Show;
import org.springframework.stereotype.Service;

public interface ShowService {

    Show save(ShowCreateDTO showCreateDTO);
}
