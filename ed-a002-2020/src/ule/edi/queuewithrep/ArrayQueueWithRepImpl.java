package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {

	
	/* ATRIBUTOS */
	private static final int capacityDefault = 10;
	ElemQueueWithRep<T>[] data;
	private int count;

	
	/* CLASE INTERNA */
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;

		public ElemQueueWithRep(T elem, int num) {
			this.elem = elem;
			this.num = num;
		}
	}

	
	/* ITERADOR */
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {

		int count;
		int actualSize;
		int current;
		ElemQueueWithRep<T>[] cola;

		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count) {
			this.count = count;
			this.current = -1;
			this.cola = cola;
			this.actualSize = 0;
		}

		@Override
		public boolean hasNext() {
			return this.current < this.count - 1 || this.actualSize != 0;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();

			if (this.actualSize == 0) {
				this.current++;
				this.actualSize = cola[current].num;
			}
			this.actualSize--;
			return this.cola[current].elem;
		}

	}
	/* FIN ITERADOR */
	
	
	/* CONSTRUCTORES */
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
	
	
	/* METODOS REQUERIDOS */
	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		ElemQueueWithRep<T>[] nuevo = (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length * 2];
		for (int i = 0; i < this.count; i++) 
			nuevo[i] = this.data[i];
		this.data = nuevo;
	}

	@Override
	public void add(T element, int times) {
		if(element == null)
			throw new NullPointerException();
		if(times <= 0) 
			throw new IllegalArgumentException();
			
		if (this.contains(element)) {
			this.data[this.find(element)].num+=times;
		} else {
			if (this.count == this.data.length)
				this.expandCapacity();		
			this.data[count] = new ElemQueueWithRep<>(element, times);
			this.count++;
		}
	}

	@Override
	public void add(T element) {
		if(element == null)
			throw new NullPointerException();
			
		if (this.contains(element)) {
			this.data[this.find(element)].num++;
		} else {
			if (this.count == this.data.length)
				this.expandCapacity();		
			this.data[count] = new ElemQueueWithRep<>(element, 1);
			this.count++;
		}
	}

	@Override
	public void remove(T element, int times) {
		if(element == null)
			throw new NullPointerException();
		if (this.isEmpty() || !this.contains(element))
			throw new NoSuchElementException();
		if(times < 0)
			throw new IllegalArgumentException();
		
		int pos = this.find(element);
		if (this.data[pos].num > times)
			this.data[pos].num -= times;
		else
			throw new IllegalArgumentException();
	}

	@Override
	public int remove() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("ArrayQueueWithRep");
		
		int apariciones = this.data[0].num;
		if (this.count > 1) {
			this.data[0] = this.data[count - 1];
			this.data[count - 1] = null;
		} else {
			this.data[0] = null;
		}
		this.count--;
		return apariciones;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.count; i++) 
			this.data[i] = null;
		this.count = 0;
	}

	@Override
	public boolean contains(T element) {	
		if(element == null)
			throw new NullPointerException();
		
		for (int i = 0; i < this.count; i++)
			if (this.data[i].elem.equals(element))
				return true;
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.count == 0;
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
		if(element == null)
			throw new NullPointerException();
		
		if (this.contains(element))
			return this.data[this.find(element)].num;
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayQueueWithRepIterator<>(this.data, this.count);
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("(");
		for (int i = 0; i < this.count; i++) 
			for (int j = 0; j < this.data[i].num; j++) 
				buffer.append(this.data[i].elem.toString()).append(" ");
		buffer.append(")");
		return buffer.toString();
	}
	
	
	/* METODOS PRIVADOS PARA MI USO PERSONAL */
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
