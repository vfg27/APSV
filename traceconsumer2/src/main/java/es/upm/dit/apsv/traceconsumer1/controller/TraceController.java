package es.upm.dit.apsv.traceconsumer1.controller;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.apsv.traceconsumer1.Repository.TraceRepository;
import es.upm.dit.apsv.traceconsumer1.model.Trace;

@RestController
public class TraceController {
    private final TraceRepository repository;

    public static final Logger log = LoggerFactory.getLogger(TraceController.class);


    public TraceController(TraceRepository repository) {
      this.repository = repository;
    }
  
    @GetMapping("/traces")
    List<Trace> all() {
      return (List<Trace>) repository.findAll();
    }
  
    /*
    @GetMapping("/orders/truck/{id}")
    TransportationOrder getActiveTruckOrder(@PathVariable String id) {
      log.info("Truck:" + id);
      return repository.findByTruckAndSt(id, 0);
    }
*/

    @PostMapping("/traces")
    Trace newTrace(@RequestBody Trace newTrace) {
      return repository.save(newTrace);
    }
    
   @GetMapping("/traces/{id}")
   Trace one(@PathVariable String id){
    return repository.findById(id).orElseThrow();
   }
  
   @PutMapping("/traces/{id}")
   Trace replaceTraze(@RequestBody Trace newTrace, @PathVariable String id) {
     return repository.findById(id).map(Trace -> {
       Trace.setTraceId(newTrace.getTraceId());
       Trace.setTruck(newTrace.getTruck());
       Trace.setLastSeen(newTrace.getLastSeen());
       Trace.setLat(newTrace.getLat());
       Trace.setLng(newTrace.getLng());
       return repository.save(Trace);
     }).orElseGet(() -> {
       newTrace.setTraceId(id);
       return repository.save(newTrace);
     });
   }
 
 

    @DeleteMapping("/traces/{id}")
    void deleteTrace(@PathVariable String id) {
      repository.deleteById(id);
    }    
}
