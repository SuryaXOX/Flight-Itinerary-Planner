
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class Tests {
	Backend Backend = new Backend();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
			// return;
			DataWrangler.CreateMap();
		} catch (Exception e) {
			System.out.println("ERROR:  SOMETHING WENT WRONG WHEN CREATING ORIGINAL GRAPH");
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("ALL TESTS COMPLETE");
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Checks 3 normal scenarios for finding shortest route between airports
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteAirports() {
		assertTrue(Backend.getShortestRouteAirports(DataWrangler.airportList.get(0), DataWrangler.airportList.get(1))
				.equals("Washington North Dakota"));
		assertTrue(Backend.getShortestRouteAirports(DataWrangler.airportList.get(0), DataWrangler.airportList.get(2))
				.equals("Washington New York"));
		assertTrue(Backend.getShortestRouteAirports(DataWrangler.airportList.get(1), DataWrangler.airportList.get(2))
				.equals("North Dakota Washington New York"));
	}

	/**
	 * Checks finding shortest route between an airport and itself
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteAirportsSingular() {
		assertTrue(Backend.getShortestRouteAirports(DataWrangler.airportList.get(0), DataWrangler.airportList.get(0))
				.equals("Washington"));
	}

	/**
	 * Checks 2 scenarios for finding shortest route between airports with invalid
	 * inputs
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteAirportsInvalid() {
		try {
			Backend.getShortestRouteAirports(new Airport("Not"), DataWrangler.airportList.get(1));
			fail("No error sent");
		} catch (Exception e2) {
		}
		try {
			Backend.getShortestRouteAirports(DataWrangler.airportList.get(0), new Airport("Not"));
			fail("No error sent");
		} catch (Exception e2) {
		}
	}

	/**
	 * Checks 3 normal scenarios for finding shortest route cost
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteCost() {
		assertTrue(Backend.getShortestRouteCost(DataWrangler.airportList.get(0), DataWrangler.airportList.get(1)) == 5);
		assertTrue(
				Backend.getShortestRouteCost(DataWrangler.airportList.get(0), DataWrangler.airportList.get(2)) == 12);
		assertTrue(Backend.getShortestRouteCost(DataWrangler.airportList.get(8), DataWrangler.airportList.get(7)) == 1);
	}

	/**
	 * Checks finding shortest route cost between an airport and itself
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteCostSingular() {
		assertTrue(Backend.getShortestRouteCost(DataWrangler.airportList.get(0), DataWrangler.airportList.get(0)) == 0);
	}

	/**
	 * Checks 2 scenarios for finding shortest route cost between airports with
	 * invalid inputs
	 */
	@org.junit.jupiter.api.Test
	void testGetShortestRouteCostInvalid() {
		try {
			Backend.getShortestRouteCost(new Airport("Not"), DataWrangler.airportList.get(1));
			fail("No error sent");
		} catch (Exception e2) {
		}
		try {
			Backend.getShortestRouteCost(new Airport("Not"), new Airport("Hmm"));
			fail("No error sent");
		} catch (Exception e2) {
		}
	}

}
