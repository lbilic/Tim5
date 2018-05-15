package ftn.isamrs.tim5.util;
import ftn.isamrs.tim5.dto.*;
import ftn.isamrs.tim5.model.*;
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

    public static Account convertAccountCreateDTOToAccount(AccountCreateDTO accountCreateDTO)
    {
        return mapper.map(accountCreateDTO, Account.class);
    }

    public static Props convertPropsDTOtoProps(PropsCreateDTO dto) {
        return mapper.map(dto, Props.class);
    }

    public static Performance convertPerformanceCreateDTOtoPerformance(PerformanceCreateDTO dto){ return mapper.map(dto, Performance.class);}
}
