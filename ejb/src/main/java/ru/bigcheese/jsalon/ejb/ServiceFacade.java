package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.Service;
import ru.bigcheese.jsalon.model.to.ServiceTO;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface ServiceFacade {
    List<ServiceTO> findAll();
    List<ServiceTO> getServicesByPostId(Long postId);
    Service createService(ServiceTO service) throws FacadeException;
    Service updateService(ServiceTO service) throws FacadeException;
    void deleteService(Long id);
    void deleteServices(Set<Long> ids);
}
