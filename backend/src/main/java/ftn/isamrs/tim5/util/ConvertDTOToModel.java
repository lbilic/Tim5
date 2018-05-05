package ftn.isamrs.tim5.util;
import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.Props;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.CineterAdmin;
import org.modelmapper.ModelMapper;

public class ConvertDTOToModel {
    public ConvertDTOToModel(){}

    static ModelMapper mapper = new ModelMapper();

    public static Cineter convertCineterCreateToCineter(CineterCreateDTO cineter){
        return mapper.map(cineter, Cineter.class);
    }

    public static Show convertShowCreateToShow(ShowCreateDTO showCreate){
        return mapper.map(showCreate, Show.class);
    }

    public static CineterAdmin convertCineterAdminDTOtoCineterAdmin(CineterAdminCreateDTO dto)
    {
        return mapper.map(dto, CineterAdmin.class);
    }

    public static Props convertPropsDTOtoProps(PropsCreateDTO dto) {
        return mapper.map(dto, Props.class);
    }
}
