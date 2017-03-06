package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.dao.ServiceDao;
import ru.bigcheese.jsalon.model.to.ServiceTO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class ServiceFacadeBean implements ServiceFacade {

    @Inject
    private ServiceDao serviceDao;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<ServiceTO> getServicesByPostId(Long postId) {
        return serviceDao.getServicesByPostId(postId).stream().map(ServiceTO::new).collect(toList());
    }
}
