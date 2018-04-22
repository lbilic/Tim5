package ftn.isamrs.tim5.util;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import org.modelmapper.ModelMapper;

public class ConvertDTOToModel {
    public ConvertDTOToModel(){}

    public static Cineter convertLogDTOToLog(CineterCreateDTO logDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(logDTO, Cineter.class);
    }
}
