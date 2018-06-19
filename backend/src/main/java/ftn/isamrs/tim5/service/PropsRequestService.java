package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.PropRequest;
import ftn.isamrs.tim5.model.Props;

import java.util.List;

public interface PropsRequestService {

    PropRequest saveRequest(PropRequest request);

    List<PropRequest> getAllByAdminId(Long id);

    PropRequest findRequestById(Long id);

    void deleteRequest(PropRequest request);

    List<PropRequest> getAll();

}
