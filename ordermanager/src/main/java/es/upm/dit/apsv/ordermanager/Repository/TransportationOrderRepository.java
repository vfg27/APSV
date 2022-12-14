package es.upm.dit.apsv.ordermanager.Repository;

import org.springframework.data.repository.CrudRepository;
import es.upm.dit.apsv.ordermanager.model.TransportationOrder;

public interface TransportationOrderRepository extends CrudRepository<TransportationOrder,String> {
    TransportationOrder findByTruckAndSt(String truck, int st);
}
