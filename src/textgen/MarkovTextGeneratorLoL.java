package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		//빈 문자열이 매개변수로 주어줬을때 
		if(sourceText.equals("")) {
			return;
		} else {
			//단어 단위로 담을 리스트
			String[] sepWords = sourceText.split("\\s+");
			//첫 단어를 starter로 지정
			starter = sepWords[0];
			//preWord를 지정
			String preWord = starter;
			//w에 쓸 list 만들기
			List<String> sepWords2 = new ArrayList<String>();
			for(int i=1; i<sepWords.length; i++) {
				sepWords2.add(sepWords[i]);
			}
			
			//w는 첫 단어 이후의 모든 단어
			for(String w : sepWords2) {
				//node들의 word를 저장하는 list
				List<String> temp = new ArrayList<String>();
				for(ListNode node : wordList) {
					//노드가 있는 경우
					if(node.getWord().equals(preWord)) {
						node.addNextWord(w);
					}
					temp.add(node.getWord());
				}
				//노드가 없는 경우
				if(temp.indexOf(starter)==-1) {
					ListNode newNode = new ListNode(preWord);
					newNode.addNextWord(w);
					wordList.add(newNode);
				}
				preWord = w;
				starter = w;
			}
			
			//마지막 단어 추가 메소드
			String lastWord = sepWords2.get(sepWords2.size()-1);
			List<String> temp = new ArrayList<String>();
			for(ListNode node : wordList) {
				temp.add(node.getWord());
			}
			//노드가 없는 경우
			if(temp.indexOf(starter)==-1) {
				ListNode newNode = new ListNode(lastWord);
				wordList.add(newNode);
			}
			
			//starter을 lastword의 next로 만들기
			for(ListNode node : wordList) {
				if(node.getWord().equals(sepWords[sepWords.length-1])) {
					node.addNextWord(sepWords[0]);
				}
			}
			//starter 초기화
			starter = sepWords[0];
			
		}
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	//numWords만큼의 문장 생성
	@Override
	public String generateText(int numWords) {
		//매개변수가 0인 경우
		if(numWords == 0) {
			return "";
		} else if (starter.equals("")) {
			return "";
		}
		String currWord = starter;
		String output = "";
		output+=starter;
		int currNum = 1;
		if(starter.equals("")) {
			return null;
		} else {
			while(currNum<numWords) {
				ListNode currNode = null;
				for(ListNode temp : wordList) {
					if(temp.getWord().equals(currWord)) {
						currNode = temp;
					}
				}
				String w = currNode.getRandomNextWord(rnGenerator);
				output+=" "+w;
				currWord = w;
				currNum++;
			}
			return output;
		}
		
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	//기존 train 초기화 메소드
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		gen.train("");
		String s = gen.generateText(20);
		System.out.println(s);
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		MarkovTextGeneratorLoL gen2 = new MarkovTextGeneratorLoL(new Random(42));
		String textString3 = "hi there hi Leo";
		System.out.println(textString3);
		gen2.train(textString3);
		for(ListNode temp : gen2.wordList) {
			System.out.println(temp.toString());
		}
		System.out.println(gen2.generateText(4));
		gen.retrain("");
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	//노드의 다음 값 중 랜덤한 문자를 반환
	public String getRandomNextWord(Random generator)
	{
		String nextWord = null;
		//0이상 size이하의 값 반환
		int ranNum = generator.nextInt(nextWords.size());
		nextWord = nextWords.get(ranNum);
		
	    return nextWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


