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
	
	//�������� ��带 �߰��ϴ� �޼ҵ�
	public boolean add(E element ) 
	{
		//��� ����
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> temp;
		//����Ʈ�� ��尡 ���� ���
		if(size==0) {
			//�ص�� �� ��带 ����
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
	//�ش� �ε����� ������ ����, �ε����� �߸��Ǿ����� ���� ����
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
	//�ε��� ��ġ�� ��� ����  ���� ��Ҵ� ������ ����
	public void add(int index, E element ) 
	{
		//��� ����
		LLNode<E> newNode = new LLNode<E>(element);
		
		//��Ұ� null�� ���, �ε����� �߸����� ��� ���� �߻�
		if(element == null) {
			throw new NullPointerException();
		} else if (index == 0) {
			//�� �տ� �ִ� ���
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
			//�������� ����
			add(element);
		} else {
			LLNode<E> temp = head;
			LLNode<E> nextTemp;
			for(int i = 0; i < index-1; i++) {
				temp = temp.next;
			}
			//�ڷ� �и� ���
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
		//���� �߻�
		if(size==0||size<=index||index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		//head ����
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
			//���ŵ� ������
			nextTemp = temp.next;
			dataRem = nextTemp.data;
			//�� ������ ��� ����
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
		//��ȯ�� ���� ���
		E old = null;
		//��Ұ� null�� ���, �ε����� �߸����� ��� ���� �߻�
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
