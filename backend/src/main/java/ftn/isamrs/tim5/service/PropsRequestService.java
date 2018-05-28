package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.PropRequest;

import java.util.List;

public interface PropsRequestService {

    PropRequest saveRequest(PropRequest request);

    List<PropRequest> getAllByAdminId(Long id);

    PropRequest findRequestById(Long id);

}
