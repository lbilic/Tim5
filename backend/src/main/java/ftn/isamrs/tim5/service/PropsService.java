package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Props;

import java.util.List;

public interface PropsService {

    Props saveProps(PropsCreateDTO props, Account account);
    List<Props> findAllByCineterId(Long id);
    Props findPropById(Long id);
    Props saveProp(Props props);
    Boolean deleteProp(Long id);
    Props sellProp(Long id);
    Props saveSoldProps(PropsCreateDTO props, Account account, Long id);
    List<Props> findMyProps(Long id);
    List<Props> findBoughtProps(Long id);

}
