import java.util.Comparator;
import java.util.Map.Entry;

public class Ascendente implements Comparator <Entry<Character,Integer>> {
	
	public int compare(Entry <Character, Integer> obj1, Entry <Character,Integer> obj2) {
		return obj1.getKey() - obj2.getKey();
	}
}
