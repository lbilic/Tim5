package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.repository.AccountRepository;
import ftn.isamrs.tim5.repository.CineterRepository;
import ftn.isamrs.tim5.repository.PropsRepository;
import ftn.isamrs.tim5.model.Props;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PropsServiceImpl implements PropsService{

    @Autowired
    private PropsRepository propsRepository;

    @Autowired
    private CineterRepository cineterRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Props saveProps(PropsCreateDTO dto, Account account){
        Props props = ConvertDTOToModel.convertPropsDTOtoProps(dto);
        CineterAdmin admin = (CineterAdmin)account;
        props.setCineter(admin.getCineter());
        props.setAccount(admin);
        admin.getCineter().getProps().add(props);
        props = propsRepository.save(props);
        accountRepository.save(admin);
        cineterRepository.save(admin.getCineter());
        return props;
    }


    @Override
    @Transactional
    public Props saveProp(Props prop){
        prop = propsRepository.save(prop);
        return prop;
    }

    @Override
    public List<Props> findAllByCineterId(Long id) {
        return propsRepository.findAllByCineterId(id);
    }

    @Override
    public Props findPropById(Long id) {return propsRepository.findPropById(id);}

    @Override
    @Transactional
    public Boolean deleteProp(Long id) {
        Props props = propsRepository.findPropById(id);
        propsRepository.delete(props);
        return true;
    }



    @Override
    @Transactional
    public Props sellProp(Long id) {
        Props prop = findPropById(id);
        prop.setAmount(prop.getAmount() - 1);
        return propsRepository.save(prop);

    }

    @Override
    public Props saveSoldProps(PropsCreateDTO props, Account account, Long id) {
        Props prop = ConvertDTOToModel.convertPropsDTOtoProps(props);
        prop.setAccount(account);
        Cineter cineter = new Cineter();
        //Cineter cineter = cineterRepository.findById(id);
        prop.setCineter(cineter);
        cineter.getProps().add(prop);

        //dodati da brise zahteve jer je ovde prihvatio props na prodaju, pa treba obrisati zahteve kod svih admina
        cineterRepository.save(cineter);

        return prop;

    }

    @Override
    public List<Props> findMyProps(Long id) {
        return propsRepository.findMyProps(id);
    }

    @Override
    public List<Props> findBoughtProps(Long id) {
        return propsRepository.findBoughtProps(id);
    }


}
