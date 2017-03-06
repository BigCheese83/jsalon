package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.model.to.ServiceTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ServiceFacade {
    List<ServiceTO> getServicesByPostId(Long postId);
}
