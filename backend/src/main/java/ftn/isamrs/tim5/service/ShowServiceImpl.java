package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Override
    public void deleteShow(ShowCreateDTO dto) {
        Optional<Show> show = showRepository.findById(dto.getId());
        if(!show.isPresent()) return;
        showRepository.delete(show.get());
    }

    @Override
    public Show updateShow(ShowCreateDTO dto){
        Optional<Show> s = this.showRepository.findById(dto.getId());

        if(!s.isPresent()) return null;

        Show show = s.get();

        show.setName(dto.getName());
        show.setDescription(dto.getDescription());
        show.setIsMovie(dto.getIsMovie());

        show = showRepository.save(show);

        return show;
    }

    @Override
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Show findById(Long id) {
        Optional<Show> show = this.showRepository.findById(id);

        return show.orElse(null);

    }
}
