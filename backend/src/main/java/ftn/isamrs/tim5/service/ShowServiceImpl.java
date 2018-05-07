package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    ShowRepository showRepository;

    @Override
    public Show save(ShowCreateDTO showCreateDTO, Account user) {

        Show show  = ConvertDTOToModel.convertShowCreateToShow(showCreateDTO);
        show.setCineter(((CineterAdmin)user).getCineter());
        return showRepository.save(show);
    }

    @Override
    public void delete(ShowCreateDTO dto, Account user) {
        showRepository.findByNameAndCineterId(dto.getName(), ((CineterAdmin)user).getCineter().getId());
    }
}
