package hu.hwsw.airportapp.repository;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import hu.hwsw.airportapp.model.Flight;

public class FlightSpecification implements Specification<Flight> {

	private Flight flight;

	//konstruktor
	public FlightSpecification(Flight flight) {
		this.flight = flight;
	}

	//predicate: a  Criteria API-hoz kapcsolódik, amit JPQL helyett tudunk használni
	//https://www.baeldung.com/spring-data-criteria-queries
	//Criteria API offers a programmatic way to create typed queries, which helps us avoid syntax errors.
	//
	
	@Override
	public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Predicate predicate = QueryByExamplePredicateBuilder.getPredicate(root, cb, 
				Example.of(flight,
						ExampleMatcher.matching()
							.withIgnoreCase() //nagybetűt - kisbetűt ignoráljuk
							.withIgnoreNullValues() //NULL értékeket ignoráljuk
							//ha a capacity nem null vagy nagyobb mint 0, akkor a v értéket átvesszük 
							.withTransformer("capacity", v -> v.isEmpty() || (int)v.get() <=0 ? Optional.empty() : v)  
							//averageDelay-nél is ugyenez
							.withTransformer("averageDelay", v -> v.isEmpty() || (double)v.get() <=0 ? Optional.empty() : v)
							//a flightTime értéket most nem vesszük át, csak a következő lépésben
							.withIgnorePaths("flightTime")
			)
		);
		
		int flightTime = flight.getFlightTime();
		
		//ha több mint 0 a repülési idő, akkor az előzőleg létrehozott lekérdezésből 
		//kiszedjük a reülési időt, ami 1 órával kevesebb vagy több lehet
		if(flightTime > 0)
			predicate = cb.and(predicate, cb.between(root.get("flightTime"), flightTime-1, flightTime+1));
		
		return predicate;
	}

}
