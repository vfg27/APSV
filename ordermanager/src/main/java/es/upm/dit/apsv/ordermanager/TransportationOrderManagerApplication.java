package es.upm.dit.apsv.ordermanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import es.upm.dit.apsv.ordermanager.Repository.TransportationOrderRepository;
import es.upm.dit.apsv.ordermanager.model.TransportationOrder;

@SpringBootApplication
public class TransportationOrderManagerApplication {

	public static final Logger log = LoggerFactory.getLogger(TransportationOrderManagerApplication.class);

	//@Autowired
	//private TransportationOrderRepository torderRepository;

	public static void main(String[] args) {
		SpringApplication.run(TransportationOrderManagerApplication.class, args);
	}


@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private TransportationOrderRepository torderRepository;

	@Override
	public void run(String... args) throws Exception {

		TransportationOrder t = new TransportationOrder();
		t.setToid("MATRICULA");
		t.setTruck("MATRICULA"+ System.currentTimeMillis());
		t.setOriginDate(100000000);
		t.setDstDate(t.getOriginDate() + (1000*60*12));
		t.setOriginLat(0.0);
		t.setOriginLong(0);
		t.setDstLat(44);
		t.setDstLong(88);
		t.setSt(0);
		torderRepository.save(t);

	}
}

}
