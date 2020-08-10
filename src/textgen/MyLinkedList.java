package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = null;
		tail = null;
		this.size = 0;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	
	//마지막에 노드를 추가하는 메소드
	public boolean add(E element ) 
	{
		//노드 생성
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> temp;
		//리스트의 노드가 없는 경우
		if(size==0) {
			//해드로 새 노드를 지정
			head = newNode;
			tail = newNode;
			size++;
			if(head.next == null) {
				tail = head;
			}
			
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			size++;
			
		}
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	//해당 인덱스의 데이터 리턴, 인덱스가 잘못되었으면 예외 말생
	public E get(int index) 
	{
		E e = null;
		LLNode<E> temp = head;
		if(size <= index || index < 0 || size==0) {
			throw new IndexOutOfBoundsException();
		} else {
			for(int i = 0; i < index; i++) {
				temp = temp.next;
			}
			e = temp.data;
		}
		
		return e;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	//인덱스 위치에 요소 삽입  전의 요소는 앞으로 만다
	public void add(int index, E element ) 
	{
		//노드 생성
		LLNode<E> newNode = new LLNode<E>(element);
		
		//요소가 null인 경우, 인덱스가 잘못됐을 경우 예외 발생
		if(element == null) {
			throw new NullPointerException();
		} else if (index == 0) {
			//맨 앞에 넣는 경우
			LLNode<E> temp = head;
			head = newNode;
			size++;
			if(head.next == null) {
				tail = head;
			} else {
				newNode.next = temp;
				temp.prev = newNode;
			}
			
		} else if(size < index || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (size == index) {
			//마지막에 대입
			add(element);
		} else {
			LLNode<E> temp = head;
			LLNode<E> nextTemp;
			for(int i = 0; i < index-1; i++) {
				temp = temp.next;
			}
			//뒤로 밀릴 노드
			nextTemp = temp.next;
			newNode.prev = temp;
			newNode.next = nextTemp;
			temp.next = newNode;
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{	
		E dataRem = null;
		//예외 발생
		if(size==0||size<=index||index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		//head 제거
		else if(index==0) {
			LLNode<E> temp = head;
			head = temp.next;
			dataRem = temp.data;
			temp = null;
			size--;
		} else {
			LLNode<E> temp = head;
			LLNode<E> nextTemp;
			for(int i = 0; i < index-1; i++) {
				temp = temp.next;
			}
			//제거될 데이터
			nextTemp = temp.next;
			dataRem = nextTemp.data;
			//맨 마지막 요소 제거
			if(index+1 == size) {
				tail = temp;
				nextTemp = null;
				size--;
			} else {
				temp.next = nextTemp.next;
				nextTemp.next.prev = temp;
				nextTemp = null;
				size--;
			}
			
		}
		return dataRem;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		//반환할 원래 요소
		E old = null;
		//요소가 null인 경우, 인덱스가 잘못됐을 경우 예외 발생
		if(element == null) {
			throw new NullPointerException();
		} else if(size < index || index < 0||size==0) {
			throw new IndexOutOfBoundsException();
		} else {
			LLNode<E> temp = head;
			for(int i = 0; i < index; i++) {
				temp = temp.next;
			}
			old = temp.data;
			temp.data = element;
		}
		
		
		return old;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
