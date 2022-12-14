package es.upm.dit.apsv.traceconsumer1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import es.upm.dit.apsv.traceconsumer1.Repository.TraceRepository;
import es.upm.dit.apsv.traceconsumer1.Repository.TransportationOrderRepository;
import es.upm.dit.apsv.traceconsumer1.model.Trace;
import es.upm.dit.apsv.traceconsumer1.model.TransportationOrder;

import org.springframework.context.annotation.Bean;
import java.util.function.Consumer;


import org.springframework.core.env.Environment;

import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.RestTemplate;


       

@SpringBootApplication
public class TraceManagerApplication {

	public static final Logger log = LoggerFactory.getLogger(TraceManagerApplication.class);

	//@Autowired
	//private TransportationOrderRepository torderRepository;

	public static void main(String[] args) {
		SpringApplication.run(TraceManagerApplication.class, args);
	}


@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private TraceRepository traceRepository;

	@Autowired
	private TransportationOrderRepository torderRepository;

	//@Autowired
	//private  Environment env;

	@Override
	public void run(String... args) throws Exception {

		Trace t = new Trace();
		t.setTraceId("MATRICULA");
        t.setTruck("MATRICULA"+ System.currentTimeMillis());
        t.setLat(0.0);
        t.setLng(0);
        t.setLastSeen(44);
        traceRepository.save(t);

	}

	@Bean("consumer")
	public Consumer<Trace> checkTrace() {
			return t -> {
					t.setTraceId(t.getTruck() + t.getLastSeen());
					traceRepository.save(t);
				  // String uri = env.getProperty("orders.server");
					//RestTemplate restTemplate = new RestTemplate();
					TransportationOrder result = null;
					result = torderRepository.findByTruck(t.getTruck());
					//try {                        
					 // result = restTemplate.getForObject(uri
					   //+ t.getTruck(), TransportationOrder.class);
					//} catch (HttpClientErrorException.NotFound ex)   {
							//result = null;
					//}
					if (result != null && result.getSt() == 0) {
							result.setLastDate(t.getLastSeen());
							result.setLastLat(t.getLat());
							result.setLastLong(t.getLng());
							if (result.distanceToDestination() < 10)
									result.setSt(1);
							torderRepository.save(result);
							log.info("Order updated: "+ result);
					}
			};
	}
}
}

