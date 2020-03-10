package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {

	// atributos

	private final int capacityDefault = 10;

	ElemQueueWithRep<T>[] data;
	private int count;

	// Clase interna

	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;

		public ElemQueueWithRep(T elem, int num) {
			this.elem = elem;
			this.num = num;
		}
	}

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {

		int count, current;
		ElemQueueWithRep<T>[] cola;

		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count) {
			this.count = count;
			this.current = 0;
			this.cola = cola;
		}

		@Override
		public boolean hasNext() {
			return current < count;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
			return cola[current - 1].elem;
		}

	}
	////// FIN ITERATOR

	// Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data = new ElemQueueWithRep[capacityDefault];
		count = 0;
	}

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data = new ElemQueueWithRep[capacity];
		count = 0;
	}

	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		ElemQueueWithRep<T>[] nuevo = (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			nuevo[i] = data[i];
		}
		data = nuevo;
		count++;
	}

	@Override
	public void add(T element, int times) {
		// TODO

	}

	@Override
	public void add(T element) {
		// TODO

	}

	@Override
	public void remove(T element, int times) {
		if (this.isEmpty() || !this.contains(element))
			throw new NoSuchElementException();
		int pos = this.find(element);
		if(this.data[i].num > times)
	}

	@Override
	public int remove() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("ArrayQueueWithRep");
		int apariciones = this.data[0].num;

		if (count > 1) {
			this.data[0] = this.data[count - 1];
			this.data[count - 1] = null;
		} else {
			this.data[0] = null;
		}
		count--;
		return apariciones;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.count; i++) {
			this.data[i] = null;
		}
	}

	@Override
	public boolean contains(T element) {
		for (int i = 0; i < count; i++) {
			if (element.equals(data[i]))
				return true;
		}
		return false;

	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public long size() {
		long num = 0;
		for (int i = 0; i < this.count; i++)
			num += this.data[i].num;
		return num;

	}

	@Override
	public int count(T element) {
		if(this.contains(element))
			return this.data[this.find(element)].num;
		return 0;

	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayQueueWithRepIterator<T>(this.data, this.count);

	}

	@Override
	public String toString() {

		final StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola.
		// Ejemplo: (A, A, A, B )

		buffer.append(")");

		return buffer.toString();
	}
	
	private int find(T element) {
		for (int i = 0; i < this.count; i++) {
			if(this.data[i].elem.equals(element))
				return i;
		}
		return -1;		
	}

}
