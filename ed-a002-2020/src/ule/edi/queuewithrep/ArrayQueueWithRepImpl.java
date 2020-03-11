package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {

	// atributos

	private final int capacityDefault = 10;
	
	private int capacity;

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

		int count;
		int current;
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
		this.capacity = capacityDefault;
	}

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data = new ElemQueueWithRep[capacity];
		count = 0;
		this.capacity = capacity;
	}

	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		ElemQueueWithRep<T>[] nuevo = (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			nuevo[i] = this.data[i];
		}
		this.data = nuevo;
		this.count++;
		this.capacity = this.capacity * 2;
	}

	@Override
	public void add(T element, int times) {
		for (int i = 0; i < this.count; i++) {
			if (this.data[i].elem.equals(element)) {
				this.data[i].num += times;
				return;
			}
		}
		
		//TODO Como comprobar que es necesario expandir la capacidad
		if (this.count == this.capacity)
			this.expandCapacity();

		this.data[count] = new ElemQueueWithRep<T>(element, times);
		this.count++;
	}

	@Override
	public void add(T element) {
		
		System.out.println("Se va a añadir " +element.toString());
		System.out.println("El contador de elementos es " +count);

		for (int i = 0; i < this.count; i++) {
			if (this.data[i].elem.equals(element)) {
				this.data[i].num++;
				return;
			}
		}

		if (this.count == this.data.length)
			this.expandCapacity();

		this.data[count] = new ElemQueueWithRep<T>(element, 1);
		this.count++;
	}

	@Override
	public void remove(T element, int times) {
		if (this.isEmpty() || !this.contains(element))
			throw new NoSuchElementException();
		
		count(element);
		int pos = this.find(element);
		if (this.data[pos].num > times) {
			this.data[pos].num -= times;
		} else if (this.data[pos].num == times) {
			if (count > 1) {
				this.data[pos] = this.data[count - 1];
				this.data[count - 1] = null;
			} else {
				this.data[pos] = null;
			}
			this.count--;
		} else {
			throw new IllegalArgumentException();
		}
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
		this.count = 0;
	}

	@Override
	public boolean contains(T element) {
		for (int i = 0; i < count; i++) {
			if (data[i].elem.equals(element))
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
		if (this.contains(element)) 
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
		for (int i = 0; i < this.count; i++) {
			for (int j = 0; j < this.data[i].num; j++) {
				buffer.append(this.data[i].elem.toString()).append(" ");
			}
		}

		buffer.append(")");

		return buffer.toString();
	}

	private int find(T element) {
		int pos = -1;
		for (int i = 0; i < this.count; i++) {
			if (this.data[i].elem.equals(element)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

}
