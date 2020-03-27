package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	
	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() {
		S2.remove("ABC", 0);
	}
	
	/* TESTS CREADOS POR EL ALUMNO */
	
	@Test
	public void testClear() {
		S2.clear();
		assertEquals(S2.size(), 0);
	}
	
	@Test
	public void testContainsWrong() {
		assertFalse(S1.contains("H1Z1"));
	}
	
	@Test
	public void testAddFirst() {
		assertTrue(S1.isEmpty());
		S1.add("ABC", 3);
		assertEquals(S1.size(), 3);
		S1.add("ABC");
		assertEquals(S1.size(), 4);
	}

	@Test
	public void testAddSecond() {
		assertTrue(S1.isEmpty());
		S1.add("ABC", 3);
		S1.add("HHH");
		assertEquals(S1.size(), 4);
		assertEquals(S1.count("HHH"), 1);
	}

	@Test
	public void testAddSecondMultiple() {
		assertTrue(S1.isEmpty());
		S1.add("ABC", 3);
		S1.add("HHH");
		S1.add("HHH", 10);
		assertEquals(S1.size(), 14);
		assertEquals(S1.count("HHH"), 11);
	}

	@Test
	public void testAddThird() {
		assertEquals(S2.size(), 20);
		assertEquals(S2.count("XYZ"), 10);
		S2.add("XYZ", 2);
		assertEquals(S2.count("XYZ"), 12);
		assertEquals(S2.size(), 22);
	}

	@Test
	public void testAddAnte() {
		S2.add("HHH");
		assertEquals(S2.size(), 21);
		assertEquals(S2.count("XYZ"), 10);
		S2.add("XYZ", 2);
		assertEquals(S2.count("XYZ"), 12);
		assertEquals(S2.size(), 23);
	}

	@Test
	public void testAddRedundant() {
		S2.add("ABC");
		assertEquals(S2.count("ABC"), 6);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddIllegalTimes() {
		S1.add("DOOM", -1);
	}

	@Test
	public void testRemove() {
		assertEquals(S2.size(), 20);
		S2.remove("123", 3);
		assertEquals(S2.size(), 17);
	}

	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		assertEquals(S2.remove(), 5);
	}
	
	@Test
	public void testRemoveFirstUnique() throws EmptyCollectionException {
		S1.add("H1Z1");
		assertEquals(S1.remove(), 1);
	}
	
	@Test
	public void testRemoveThird() {
		assertEquals(S2.size(), 20);
		assertEquals(S2.count("XYZ"), 10);
		S2.remove("XYZ", 9);
		assertEquals(S2.count("XYZ"), 1);
		assertEquals(S2.size(), 11);
	}
	
	@Test
	public void testRemoveLessTimes() {
		assertEquals(S2.count("ABC"), 5);
		S2.remove("ABC", 2);
		assertEquals(S2.count("ABC"), 3);
	}

	@Test (expected = EmptyCollectionException.class)
	public void testRemoveFirstVoidQueue() throws EmptyCollectionException {
		S1.remove();
	}

	@Test
	public void testRemoveThirdLessTimes() {
		assertEquals(S2.size(), 20);
		assertEquals(S2.count("XYZ"), 10);
		S2.remove("XYZ", 8);
		assertEquals(S2.count("XYZ"), 2);
		assertEquals(S2.size(), 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveThirdMoreTimes() {
		assertEquals(S2.size(), 20);
		assertEquals(S2.count("XYZ"), 10);
		S2.remove("XYZ", 11);
	}

	@Test (expected = NoSuchElementException.class)
	public void testRemoveVoidQueue() {
		S1.remove("H1Z1", 1);
	}

	@Test (expected = NoSuchElementException.class)
	public void testRemoveWithOutElementQueue() {
		S2.remove("H1Z1", 1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveWrongTimes() {
		S2.remove("ABC", 150000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveNegativeTimes() {
		S2.remove("ABC", -15);
	}

	@Test
	public void testCountWrong() {
		assertEquals(S1.count("H1Z1"), 0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNPE() {
		String str = null;
		S1.add(str);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddTimesNPE() {
		String str = null;
		S1.add(str, 10);
	}
	
	@Test (expected = NullPointerException.class)
	public void testRemoveTimesNPE() {
		String str = null;
		S1.remove(str, 10);
	}
	
	
	@Test (expected = NullPointerException.class)
	public void testCountNPE() {
		String str = null;
		S1.count(str);
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainstNPE() {
		String str = null;
		S1.contains(str);
	}

	@Test (expected = NoSuchElementException.class)
	public void testIterator() {
		S1.add("PUBG", 1);
		S1.add("CSGO", 3);
		Iterator<String> iterator = S1.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), "PUBG");
		assertEquals(iterator.next(), "CSGO");
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
		iterator.next();
	}

}
