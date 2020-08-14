package textgen;

/**
 *  The interface for the MarkovTextGenerator
 *  @author UC San Diego Intermediate Programming MOOC team
 *  
 */
public interface MarkovTextGenerator {
	
	/** Train the generator by adding the sourceText */
	//�ؽ�Ʈ Ʈ���̴� �޼ҵ�
	public void train(String sourceText);
	
	/** Generate the text with the specified number of words */
	//�䷯���� ��ŭ�� ���� ����
	public String generateText(int numWords);
	
	/** Retrain the generator from scratch on the source text */
	//�ٽ� Ʈ���̴�
	public void retrain(String sourceText);
}
