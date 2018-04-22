package ftn.isamrs.tim5.util;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.Show;
import org.modelmapper.ModelMapper;

public class ConvertDTOToModel {
    public ConvertDTOToModel(){}

    public static Cineter convertCineterCreateToCineter(CineterCreateDTO cineter){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cineter, Cineter.class);
    }

    public static Show convertShowCreateToShow(ShowCreateDTO showCreate){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(showCreate, Show.class);
    }
}
