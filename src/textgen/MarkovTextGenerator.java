package textgen;

/**
 *  The interface for the MarkovTextGenerator
 *  @author UC San Diego Intermediate Programming MOOC team
 *  
 */
public interface MarkovTextGenerator {
	
	/** Train the generator by adding the sourceText */
	//텍스트 트레이닝 메소드
	public void train(String sourceText);
	
	/** Generate the text with the specified number of words */
	//페러미터 만큼의 문구 생성
	public String generateText(int numWords);
	
	/** Retrain the generator from scratch on the source text */
	//다시 트레이닝
	public void retrain(String sourceText);
}
