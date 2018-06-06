package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.BoughtPropDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.BoughtProps;

import java.util.List;

public interface BoughtPropsService {

    BoughtProps findBoughtPropById(Long id);
    List<BoughtProps> findAllByUserId(Long id);
    BoughtProps saveBoughtProp(BoughtProps boughtProps, Account account);

}
