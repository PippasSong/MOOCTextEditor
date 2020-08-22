package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		String lowWord = word.toLowerCase();
		char[] charAry = lowWord.toCharArray();
		TrieNode pointer = root;
		for(int i = 0; i < charAry.length; i++) {
			//�ڿ� ��ġ�ϴ� ���� �ִ� ���
			if(pointer.getValidNextCharacters().contains(charAry[i])){
				pointer = pointer.getChild(charAry[i]);
				//�����߰� �� endsWord ����
				if(i==charAry.length-1) {
					//�̹� �ִ� �ܾ��� ���
					if(pointer.endsWord()) {
						return false;
					}
					pointer.setEndsWord(true);
					size++;
					return true;
				}
			} else {
			//�ڿ� �����ϴ� ���� ���� ���
				pointer.insert(charAry[i]);
				pointer = pointer.getChild(charAry[i]);
				//�����߰� �� endsWord ����
				if(i==charAry.length-1) {
					pointer.setEndsWord(true);
					size++;
					return true;
				}
			}
		}
		return false;
	    
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{

	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		//�ҹ��� ��ȯ
		String lowWord = s.toLowerCase();
		//���ڸ� ���� �迭
		char[] charAry = lowWord.toCharArray();
		TrieNode pointer = root;
	    for(int i=0; i < charAry.length; i++) {
	    	if(pointer.getChild(charAry[i])!=null) {
	    		pointer = pointer.getChild(charAry[i]);
	    	} else {
	    		return false;
	    	}
	    }
	    return pointer.endsWord();
	}
	
	//�ڵ��ϼ� �ܾ���� �޼ҵ�
	private void collect(TrieNode node, String prefix, List<String> allWords) {
		//��尡 null�̸� �޼ҵ� ����
		if(node==null) {
			return;
		}
		
		//leaf ����̸� allwords�� ����
		if(node.endsWord()) {
			allWords.add(prefix);
		}
		Set<Character> sourceSet = node.getValidNextCharacters();
		//Ű������ �� list
		List<Character> keyList = new ArrayList<Character>(sourceSet);
		//�ڽĳ��� �̷��� ����Ʈ
		List<TrieNode> nodeAry = new ArrayList<TrieNode>();
		for(Character c : sourceSet) {
			nodeAry.add(node.getChild(c));
		}
		//����� �ڽĳ�� �� ��ŭ �ݺ� ����
		for(int i = 0; i < keyList.size(); i++) {
			TrieNode childNode = node.getChild(keyList.get(i));
			if(childNode==null) {
				continue;
			}
			//�ڽĳ���� ���ĺ�
			Character childCharacter = keyList.get(i);
			//���ȣ��
			collect(childNode, prefix+childCharacter, allWords);
		}
	}
	
	//�迭�� �޾Ƽ� ª�� �ܾ���� ���� �� numCompletions�� ���ڸ�ŭ�� �迭�� �����ؼ� �տ������� �߶�
	public static List<String> sort(List<String> comList, int numCompletions){
		int number = 0;
		if(comList.size()>=numCompletions) {
			number = numCompletions;
		} else {
			number = comList.size();
		}
		//������ ����Ʈ
		List<String> sortList = comList;
		List<String> emptyList = new ArrayList<String>();
		List<String> comSortList = new ArrayList<String>();
		//numCompletions�� 0�� ��� �� �迭�� ����
		if(numCompletions==0) {
			return emptyList;
		}
		//����Ʈ�� ª�� �ܾ���� ����
		for(int i = 0; i<comList.size(); i++) {
			for(int j = 0; j<comList.size()-1; j++) {
				if(comList.get(j).length()>comList.get(j+1).length()) {
					Collections.swap(sortList, j, j+1);
				} 
			}
		}
		
		for(int i = 0; i < number; i++) {
			comSortList.add(sortList.get(i));
		}
		return comSortList;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     *numCompletions�� ������ �ܾ��
     *���̰� ���� �ܾ���� ����
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode pointer = root;
    	 //��ȯ�� �迭
    	 List<String> comList = new ArrayList<String>();
    	 //�ҹ��� ��ȯ
    	 String lowWord = prefix.toLowerCase();
    	 //���ڸ� ���� �迭
    	 char[] charAry = lowWord.toCharArray();
    	 
    	 //���λ� ��ŭ ����
    	 for(int i=0; i < charAry.length; i++) {
 	    	if(pointer.getChild(charAry[i])!=null) {
 	    		pointer = pointer.getChild(charAry[i]);
 	    	} else {
 	    		return comList;
 	    	}
    	 }
    	 
    	 collect(pointer, prefix, comList);
    	 List<String> sortList = sort(comList, numCompletions);
    	 
         return sortList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	

	
}