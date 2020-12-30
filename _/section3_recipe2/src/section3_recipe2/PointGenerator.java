package section3_recipe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointGenerator {
	
	public static List<Point> generatePointList (int size) {
		List<Point> ret = new ArrayList<>();
		
		Random randomGenerator=new Random();
		for (int i=0; i<size; i++) {
			Point point=new Point();
			point.setX(randomGenerator.nextDouble());
			point.setY(randomGenerator.nextDouble());
			ret.add(point);
		}
		
		return ret;
	}
}
