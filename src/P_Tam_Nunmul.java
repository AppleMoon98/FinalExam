import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class P_Tam_Nunmul {
	// 평균
	public double ave(double[] n) {
		Mean m = new Mean();
		for (int i = 0; i < n.length; i++)
			m.incrementAll(n);
		return m.getResult();
	}
	
	// 분산
	public double variance(double[] n) {
		Variance v = new Variance();
		for(int i = 0; i < n.length; i++) 
			v.incrementAll(n);
		return v.getResult();
	}
	
	// 표준오차
	public double StandardError(double[] n) {
		return Math.sqrt(new P_Tam_Nunmul().variance(n));
	}
	
	public double CorrelationCoefficient(double[] n) {
		
		return 1.0;
	}
}
