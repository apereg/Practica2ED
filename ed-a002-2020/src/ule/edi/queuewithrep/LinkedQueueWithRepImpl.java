package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {

	// Atributos
	private QueueWithRepNode<T> front;

	int count;

	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;

		public QueueWithRepNode(T elem, int num) {
			this.elem = elem;
			this.num = num;
		}

	}

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {

		QueueWithRepNode<T> nodo;
		int actualSize;

		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			this.nodo = nodo;
			this.actualSize = -1;
		}

		@Override
		public boolean hasNext() {
			return this.nodo != null;
		}

		@Override
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			if (this.actualSize == -1) {
				this.actualSize = this.nodo.num;
			}
			T elemReturn = this.nodo.elem;
			this.actualSize--;
			if (this.actualSize == 0) {
				this.nodo = this.nodo.next;
				this.actualSize = -1;
			}
			return elemReturn;
		}

	}
	////// FIN ITERATOR

	public LinkedQueueWithRepImpl() {
		this.count = 0;
	}

	/////////////
	@Override
	public void add(T element) {
		if(element == null)
			throw new NullPointerException();	
		if (this.isEmpty()) {
			this.front = new QueueWithRepNode<>(element, 1);
			this.count++;
			return;
		}
		
		if (this.front.next == null) {
			if (this.front.elem.equals(element)) {
				this.front.num++;
			} else {
				this.front.next = new QueueWithRepNode<>(element, 1);
				this.count++;
			}
			return;
		}
		
		/* Se recorre la lista buscando el elemento */
		QueueWithRepNode<T> aux = this.front;
		while (aux.next != null) {
			if (aux.elem.equals(element)) {
				aux.num++;
				return;
			}
			aux = aux.next;
		}

		/* Si no se encuentra se añade al final */
		aux.next = new QueueWithRepNode<>(element, 1);

		this.count++;
	}

	@Override
	public void add(T element, int times) {
		if(element == null)
			throw new NullPointerException();	
		if (times <= 0) 
			throw new IllegalArgumentException();
		if (this.isEmpty()) {
			this.front = new QueueWithRepNode<>(element, times);
			this.count++;
			return;
		}	

		if (this.front.next == null) {
			if (this.front.elem.equals(element)) {
				this.front.num += times;
			} else {
				this.front.next = new QueueWithRepNode<>(element, times);
				this.count++;
			}
			return;
		}
		
		/* Se recorre la lista buscando el elemento */
		QueueWithRepNode<T> aux = this.front;
		while (aux.next != null) {
			if (aux.elem.equals(element)) {
				aux.num += times;
				return;
			}
			aux = aux.next;
		}
		
		/* Se comprueba si es el ultimo */
		if (aux.elem.equals(element)) {
			aux.num += times;
			return;
		}
		
		/* Si no se encuentra se añade al final */
		aux.next = new QueueWithRepNode<>(element, times);
		this.count++;
	}

	@Override
	public void remove(T element, int times) {
		if(element == null)
			throw new NullPointerException();
		if (this.isEmpty() || !this.contains(element))
			throw new NoSuchElementException("LinkedQueueWithRep");
		if(times < 0)
			throw new IllegalArgumentException();

		if (this.front.elem.equals(element)) {
			if (times >= this.front.num)
				throw new IllegalArgumentException();
			else
				this.front.num -= times;
			return;
		}

		QueueWithRepNode<T> aux = this.front;
		while (aux.next != null) {
			if (aux.next.elem.equals(element)) {
				if (times >= aux.next.num)
					throw new IllegalArgumentException();
				else
					aux.next.num -= times;
				break;
			}
			aux = aux.next;
		}
	}

	@Override
	public int remove() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("LinkedQueueWithRep");

		int apariciones = this.front.num;
		if (this.front.next == null) {
			this.front = null;
		} else {
			QueueWithRepNode<T> nuevoFront = this.front.next;
			this.front = nuevoFront;
		}
		this.count--;
		return apariciones;
	}

	@Override
	public boolean contains(T element) {
		if(element == null)
			throw new NullPointerException();
		
		QueueWithRepNode<T> aux = this.front;
		while (aux != null) {
			if (aux.elem.equals(element)) {
				return true;
			}
			aux = aux.next;
		}
		return false;
	}

	@Override
	public long size() {
		long size = 0;
		QueueWithRepNode<T> aux = this.front;
		while (aux != null) {
			size += aux.num;
			aux = aux.next;
		}
		return size;

	}

	@Override
	public boolean isEmpty() {
		return this.count == 0;
	}

	@Override
	public void clear() {
		this.front = null;
		this.count = 0;
	}

	@Override
	public int count(T element) {
		if(element == null)
			throw new NullPointerException();
		
		if (this.contains(element)) {
			QueueWithRepNode<T> aux = this.front;
			for (int i = 0; i <= this.find(element) - 1; i++) {
				aux = aux.next;
			}
			return aux.num;
		}
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueWithRepIterator<T>(this.front);
	}

	@Override
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("(");

		QueueWithRepNode<T> aux = this.front;
		while (aux != null) {
			int apariciones = aux.num;
			while (apariciones > 0) {
				buffer.append(aux.elem.toString()).append(" ");
				apariciones--;
			}
			aux = aux.next;
		}

		buffer.append(")");
		return buffer.toString();
	}

	private int find(T element) {
		int pos = -1;
		QueueWithRepNode<T> aux = this.front;
		for (int i = 0; i < this.count; i++) {
			if (aux.elem.equals(element)) {
				pos = i;
				break;
			}
			aux = aux.next;
		}
		return pos;
	}

}
