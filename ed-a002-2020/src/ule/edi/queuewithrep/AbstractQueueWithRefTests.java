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
	public void testCountWrong() {
		assertEquals(S1.count("H1Z1"), 0);
	}
	
	@Test
	public void testAddRedundant() {
		S2.add("ABC");
		assertEquals(S2.count("ABC"), 6);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testIterator() {
		Iterator<String> iterator = S2.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), "ABC");
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
		iterator.next();
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
	
	@Test (expected = EmptyCollectionException.class)
	public void testRemoveFirstVoidQueue() throws EmptyCollectionException {
		S1.remove();
	}
	
	@Test
	public void testRemove() {
		assertEquals(S2.size(), 20);
		S2.remove("ABC", 5);
		assertEquals(S2.size(), 15);
	}
	
	@Test
	public void testRemoveLessTimes() {
		assertEquals(S2.count("ABC"), 5);
		S2.remove("ABC", 2);
		assertEquals(S2.count("ABC"), 3);
	}
	
	@Test
	public void testRemoveUnique() {
		assertTrue(S1.isEmpty());
		S1.add("ABC", 3);
		assertFalse(S1.isEmpty());
		S1.remove("ABC", 3);
		assertTrue(S1.isEmpty());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveVoidQueue() {
		S1.remove("H1Z1", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveWrongTimes() {
		S2.remove("ABC", 150000);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveWithOutElementQueue() {
		S2.remove("H1Z1", 1);
	}
	
	

}
