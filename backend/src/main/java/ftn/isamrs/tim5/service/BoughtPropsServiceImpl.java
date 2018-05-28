package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.BoughtProps;
import ftn.isamrs.tim5.repository.AccountRepository;
import ftn.isamrs.tim5.repository.BoughtPropsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoughtPropsServiceImpl implements BoughtPropsService {

    @Autowired
    private BoughtPropsRepository boughtPropsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public BoughtProps findBoughtPropById(Long id) {
        return boughtPropsRepository.findBoughtPropById(id);
    }

    @Override
    public List<BoughtProps> findAllByUserId(Long id) {
        return boughtPropsRepository.findAllByUserId(id);
    }

    @Override
    public BoughtProps saveBoughtProp(BoughtProps boughtProps, Account account) {
        BoughtProps props = boughtProps;
        Account acc = account;
        props.setAccount(acc);
        acc.getBoughtProps().add(props);
        props = boughtPropsRepository.save(props);
        accountRepository.save(acc);

        return props;
    }
}
