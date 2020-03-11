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

		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public T next() {
			return null;
		}

	}
	////// FIN ITERATOR

	public LinkedQueueWithRepImpl() {
		this.count = 0;
	}

	/////////////
	@Override
	public void add(T element) {
		if (this.isEmpty()) {
			this.front = new QueueWithRepNode<>(element, 1);
			this.count++;
			return;
		}
		
		/* Se recorre la lista buscando el elemento */
		QueueWithRepNode<T> aux = this.front;
		while(aux != null) {
			if(aux.elem.equals(element)) {
				aux.num++;
				return;
			}
			aux = aux.next;
		}		
		
		/* Si no se encuentra se añade al final */
		aux = new QueueWithRepNode<>(element, 1);
		this.count++;
	}

	@Override
	public void add(T element, int times) {
		if (this.isEmpty()) {
			this.front = new QueueWithRepNode<>(element, times);
			this.count++;
			return;
		}
		
		/* Se recorre la lista buscando el elemento */
		QueueWithRepNode<T> aux = this.front;
		while(aux != null) {
			if(aux.elem.equals(element)) {
				aux.num += times;
				return;
			}
			aux = aux.next;
		}		
		
		/* Si no se encuentra se añade al final */
		aux = new QueueWithRepNode<>(element, times);
		this.count++;
	}

	@Override
	public void remove(T element, int times) {
		// todo

	}

	@Override
	public int remove() throws EmptyCollectionException {
		if(this.isEmpty())
			throw new EmptyCollectionException("LinkedQueueWithRep");
		
		int apariciones = this.front.num;
		if(this.front.next == null) {
			this.front = null;
		} else {
			QueueWithRepNode<T> nuevoFront = this.front.next;
			this.front = nuevoFront;	
		}
		return apariciones;
	}
	
	@Override
	public boolean contains(T element) {
		QueueWithRepNode<T> aux = this.front;
		while(aux != null) {
			if(aux.elem.equals(element)) {
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
		while(aux != null) {
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
		if (this.contains(element)) {
			QueueWithRepNode<T> aux = this.front;
			for (int i = 0; i < this.find(element)-1; i++) {
				aux = aux.next;
			}
			return aux.num;
		}
		return 0;

	}

	@Override
	public Iterator<T> iterator() {
		return null;
		// TODO
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola.
		// Ejemplo: (A, A, A, B )

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
