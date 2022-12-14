package es.upm.dit.apsv.traceconsumer1.Repository;

import org.springframework.data.repository.CrudRepository;
import es.upm.dit.apsv.traceconsumer1.model.TransportationOrder;

public interface TransportationOrderRepository extends CrudRepository<TransportationOrder,String> {
    TransportationOrder findByTruckAndSt(String truck, int st);
    TransportationOrder findByTruck(String truck);
}
