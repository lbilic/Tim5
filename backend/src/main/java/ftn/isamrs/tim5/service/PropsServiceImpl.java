package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.repository.PropsRepository;
import ftn.isamrs.tim5.model.Props;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropsServiceImpl implements PropsService{

    @Autowired
    private PropsRepository propsRepository;

    @Override
    public Props saveProps(PropsCreateDTO dto){
        Props props = ConvertDTOToModel.convertPropsDTOtoProps(dto);
        System.out.println(props);
        return propsRepository.save(props);
    }
}
