package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.PropRequest;
import ftn.isamrs.tim5.repository.PropsRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.PropertyPermission;

@Service
public class PropsRequestServiceImpl implements PropsRequestService {

    @Autowired
    private PropsRequestRepository propsRequestRepository;

    @Override
    public PropRequest findRequestById(Long id) {
        return propsRequestRepository.findRequestById(id);
    }

    @Override
    public void deleteRequest(PropRequest request) {
        propsRequestRepository.delete(request);
    }

    @Override
    public List<PropRequest> getAllByAdminId(Long id) {
        return propsRequestRepository.findAllByAdminId(id);
    }

    @Override
    @Transactional
    public PropRequest saveRequest(PropRequest request) {
        return propsRequestRepository.save(request);
    }

    @Override
    public List<PropRequest> getAll() { return propsRequestRepository.getAll(); }


}
