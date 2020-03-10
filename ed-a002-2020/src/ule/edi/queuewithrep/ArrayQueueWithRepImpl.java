package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		
	
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
					
		}

		@Override
		public boolean hasNext() {
			
		
		}

		@Override
		public T next() {
		
		}
		
		

	}
	////// FIN ITERATOR
	
	
    // Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =   new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}
	
	
	 @SuppressWarnings("unchecked")
	 private void expandCapacity() {
		
			ElemQueueWithRep<T>[] nuevo= (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];
			
			// todo
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
				//todo
			}

			@Override
			public int remove() throws EmptyCollectionException {
				//todo
				
			}

			@Override
			public void clear() {
				// TODO 
				
			}
			

			@Override
			public boolean contains(T element) {
				// TODO 
			

			}

			@Override
			public boolean isEmpty() {
				// TODO 
				
			}

			@Override
			public long size() {
				// TODO 
				
			}

			@Override
			public int count(T element) {
				// TODO 
				
			}

			@Override
			public Iterator<T> iterator() {
				// TODO 
				
			}
			
			@Override
			public String toString() {
				
				final StringBuffer buffer = new StringBuffer();
				
				buffer.append("(");

				// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
				
				buffer.append(")");
				
				return buffer.toString();
			}

	
}
