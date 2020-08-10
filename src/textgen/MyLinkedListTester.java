/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> addEnd;
	MyLinkedList<Integer> addIndex;
	MyLinkedList<Integer> reMovIndex;
	MyLinkedList<Integer> reMovIndex2;
	MyLinkedList<Integer> setIndex;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	

	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		
		addEnd = new MyLinkedList<Integer>();
		for(int i=0; i<100; i++) {
			addEnd.add(100-i);
		}
		
		/*LLNode<Integer> temp = addEnd.tail;
		for(int i=0; i < 100; i++) {
			int a= temp.data;
			System.out.println(a);
			temp = temp.prev;
		}*/
		
		
		
		addIndex = new MyLinkedList<Integer>();
		for(int i=0; i<100; i++) {
			addIndex.add(i, 100-i);
		}
		
		/*LLNode<Integer> temp = addIndex.tail;
		for(int i=0; i < 100; i++) {
			int a= temp.data;
			System.out.println(a);
			temp = temp.prev;
		}*/

		
		reMovIndex = new MyLinkedList<Integer>();
		for(int i=0; i<100; i++) {
			reMovIndex.add(i, 100-i);
		}
		reMovIndex2 = new MyLinkedList<Integer>();
		for(int i=0; i<2; i++) {
			reMovIndex2.add(i, i);
		}
		
		setIndex = new MyLinkedList<Integer>();
		for(int i=0; i<2; i++) {
			setIndex.add(i, i);
		}
		setIndex.set(1, 50);

		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		int b  = reMovIndex.remove(99);
		assertEquals("Remove: check a is correct ", 1, b);
		int c  = reMovIndex2.remove(1);
		assertEquals("Remove: check a is correct ", 1,c);
		
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.remove(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{

	    for(int i=0; i<100; i++) {
	    	assertEquals("check "+i+"element", (Integer)(100-i), addEnd.get(i));
	    }
		

	    //test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	    
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("test size", 2, shortList.size());
		assertEquals("test size", 0, emptyList.size());
		assertEquals("test size", 10, longerList.size());
		assertEquals("test size", 3, list1.size());
		assertEquals("test size", 100, addEnd.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			emptyList.add(-1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(0, null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			
		}
		assertEquals("Check second", (Integer)99, (Integer)addIndex.get(1));
		
		try {
			longerList.add(LONG_LIST_LENGTH+1, 100);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			emptyList.set(0, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		assertEquals("Check set", (Integer)50, (Integer)setIndex.get(1));
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
