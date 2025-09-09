package final_project_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DescriptiveStat {

	protected ArrayList<Double> data;
	protected double[] data_array;
	protected String name;

	public DescriptiveStat(ArrayList<Double> data, double[] data_array, String name) {
		this.data = data;
		this.data_array = data_array;
		this.name = name;
	}

	public double[] getData() {
		return data_array;
	}

	public String getName() {
		return name;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double mean() {
		double sum = 0.0;
		for (double num : this.data) {
			sum += num;
		}
		return sum / this.data.size();
	}

	public double standardDeviation() {
		double mean = mean();
		double sumOfSquares = 0.0;
		for (double num : this.data) {
			sumOfSquares += Math.pow(num - mean, 2);
		}
		return Math.sqrt(sumOfSquares / this.data.size());
	}

	public int sampleSize() {
		return this.data.size();
	}

	public double populationVariance() {
		double mean = mean();
		double sumOfSquares = 0.0;
		for (double num : this.data) {
			sumOfSquares += Math.pow(num - mean, 2);
		}
		return sumOfSquares / this.data.size();
	}

	public double populationStandardDeviation() {
		return Math.sqrt(populationVariance());
	}

	String information() {
		return "數據名稱: " + this.name + "\n平均值: " + mean() + "\n標準偏差: " + standardDeviation() + "\n樣本大小: " + sampleSize()
				+ "\n母體方差: " + populationVariance() + "\n母體標準偏差: " + populationStandardDeviation();
	}

	public double findzTable(double z) {

		Map<Double, Double> zTable = new HashMap<>();

		zTable.put(0.00, 0.0000);
		zTable.put(0.01, 0.0040);
		zTable.put(0.02, 0.0080);
		zTable.put(0.03, 0.0120);
		zTable.put(0.04, 0.0160);
		zTable.put(0.05, 0.0199);
		zTable.put(0.06, 0.0239);
		zTable.put(0.07, 0.0279);
		zTable.put(0.08, 0.0319);
		zTable.put(0.09, 0.0359);
		zTable.put(0.10, 0.0398);
		zTable.put(0.11, 0.0438);
		zTable.put(0.12, 0.0478);
		zTable.put(0.13, 0.0517);
		zTable.put(0.14, 0.0557);
		zTable.put(0.15, 0.0596);
		zTable.put(0.16, 0.0636);
		zTable.put(0.17, 0.0675);
		zTable.put(0.18, 0.0714);
		zTable.put(0.19, 0.0753);
		zTable.put(0.20, 0.0793);
		zTable.put(0.21, 0.0832);
		zTable.put(0.22, 0.0871);
		zTable.put(0.23, 0.0910);
		zTable.put(0.24, 0.0948);
		zTable.put(0.25, 0.0987);
		zTable.put(0.26, 0.1026);
		zTable.put(0.27, 0.1064);
		zTable.put(0.28, 0.1103);
		zTable.put(0.29, 0.1141);
		zTable.put(0.30, 0.1179);
		zTable.put(0.31, 0.1217);
		zTable.put(0.32, 0.1255);
		zTable.put(0.33, 0.1293);
		zTable.put(0.34, 0.1331);
		zTable.put(0.35, 0.1368);
		zTable.put(0.36, 0.1406);
		zTable.put(0.37, 0.1443);
		zTable.put(0.38, 0.1480);
		zTable.put(0.39, 0.1517);
		zTable.put(0.40, 0.1554);
		zTable.put(0.41, 0.1591);
		zTable.put(0.42, 0.1628);
		zTable.put(0.43, 0.1664);
		zTable.put(0.44, 0.1700);
		zTable.put(0.45, 0.1736);
		zTable.put(0.46, 0.1772);
		zTable.put(0.47, 0.1808);
		zTable.put(0.48, 0.1844);
		zTable.put(0.49, 0.1879);
		zTable.put(0.50, 0.1915);
		zTable.put(0.51, 0.1950);
		zTable.put(0.52, 0.1985);
		zTable.put(0.53, 0.2019);
		zTable.put(0.54, 0.2054);
		zTable.put(0.55, 0.2088);
		zTable.put(0.56, 0.2123);
		zTable.put(0.57, 0.2157);
		zTable.put(0.58, 0.2190);
		zTable.put(0.59, 0.2224);
		zTable.put(0.60, 0.2257);
		zTable.put(0.61, 0.2291);
		zTable.put(0.62, 0.2324);
		zTable.put(0.63, 0.2357);
		zTable.put(0.64, 0.2389);
		zTable.put(0.65, 0.2422);
		zTable.put(0.66, 0.2454);
		zTable.put(0.67, 0.2486);
		zTable.put(0.68, 0.2517);
		zTable.put(0.69, 0.2549);
		zTable.put(0.70, 0.2580);
		zTable.put(0.71, 0.2611);
		zTable.put(0.72, 0.2642);
		zTable.put(0.73, 0.2673);
		zTable.put(0.74, 0.2704);
		zTable.put(0.75, 0.2734);
		zTable.put(0.76, 0.2764);
		zTable.put(0.77, 0.2794);
		zTable.put(0.78, 0.2823);
		zTable.put(0.79, 0.2852);
		zTable.put(0.80, 0.2881);
		zTable.put(0.81, 0.2910);
		zTable.put(0.82, 0.2939);
		zTable.put(0.83, 0.2967);
		zTable.put(0.84, 0.2995);
		zTable.put(0.85, 0.3023);
		zTable.put(0.86, 0.3051);
		zTable.put(0.87, 0.3078);
		zTable.put(0.88, 0.3106);
		zTable.put(0.89, 0.3133);
		zTable.put(0.90, 0.3159);
		zTable.put(0.91, 0.3186);
		zTable.put(0.92, 0.3212);
		zTable.put(0.93, 0.3238);
		zTable.put(0.94, 0.3264);
		zTable.put(0.95, 0.3289);
		zTable.put(0.96, 0.3315);
		zTable.put(0.97, 0.3340);
		zTable.put(0.98, 0.3365);
		zTable.put(0.99, 0.3389);
		zTable.put(1.00, 0.3413);

		zTable.put(1.01, 0.3438);
		zTable.put(1.02, 0.3461);
		zTable.put(1.03, 0.3485);
		zTable.put(1.04, 0.3508);
		zTable.put(1.05, 0.3531);
		zTable.put(1.06, 0.3554);
		zTable.put(1.07, 0.3577);
		zTable.put(1.08, 0.3599);
		zTable.put(1.09, 0.3621);
		zTable.put(1.10, 0.3643);
		zTable.put(1.11, 0.3665);
		zTable.put(1.12, 0.3686);
		zTable.put(1.13, 0.3708);
		zTable.put(1.14, 0.3729);
		zTable.put(1.15, 0.3749);
		zTable.put(1.16, 0.3770);
		zTable.put(1.17, 0.3790);
		zTable.put(1.18, 0.3810);
		zTable.put(1.19, 0.3830);
		zTable.put(1.20, 0.3849);
		zTable.put(1.21, 0.3869);
		zTable.put(1.22, 0.3888);
		zTable.put(1.23, 0.3907);
		zTable.put(1.24, 0.3925);
		zTable.put(1.25, 0.3944);
		zTable.put(1.26, 0.3962);
		zTable.put(1.27, 0.3980);
		zTable.put(1.28, 0.3997);
		zTable.put(1.29, 0.4015);
		zTable.put(1.30, 0.4032);
		zTable.put(1.31, 0.4049);
		zTable.put(1.32, 0.4066);
		zTable.put(1.33, 0.4082);
		zTable.put(1.34, 0.4099);
		zTable.put(1.35, 0.4115);
		zTable.put(1.36, 0.4131);
		zTable.put(1.37, 0.4147);
		zTable.put(1.38, 0.4162);
		zTable.put(1.39, 0.4177);
		zTable.put(1.40, 0.4192);
		zTable.put(1.41, 0.4207);
		zTable.put(1.42, 0.4222);
		zTable.put(1.43, 0.4236);
		zTable.put(1.44, 0.4251);
		zTable.put(1.45, 0.4265);
		zTable.put(1.46, 0.4279);
		zTable.put(1.47, 0.4292);
		zTable.put(1.48, 0.4306);
		zTable.put(1.49, 0.4319);
		zTable.put(1.50, 0.4332);
		zTable.put(1.51, 0.4345);
		zTable.put(1.52, 0.4357);
		zTable.put(1.53, 0.4370);
		zTable.put(1.54, 0.4382);
		zTable.put(1.55, 0.4394);
		zTable.put(1.56, 0.4406);
		zTable.put(1.57, 0.4418);
		zTable.put(1.58, 0.4429);
		zTable.put(1.59, 0.4441);
		zTable.put(1.60, 0.4452);
		zTable.put(1.61, 0.4463);
		zTable.put(1.62, 0.4474);
		zTable.put(1.63, 0.4484);
		zTable.put(1.64, 0.4495);
		zTable.put(1.65, 0.4505);
		zTable.put(1.66, 0.4515);
		zTable.put(1.67, 0.4525);
		zTable.put(1.68, 0.4535);
		zTable.put(1.69, 0.4545);
		zTable.put(1.70, 0.4554);
		zTable.put(1.71, 0.4564);
		zTable.put(1.72, 0.4573);
		zTable.put(1.73, 0.4582);
		zTable.put(1.74, 0.4591);
		zTable.put(1.75, 0.4599);
		zTable.put(1.76, 0.4608);
		zTable.put(1.77, 0.4616);
		zTable.put(1.78, 0.4625);
		zTable.put(1.79, 0.4633);
		zTable.put(1.80, 0.4641);
		zTable.put(1.81, 0.4649);
		zTable.put(1.82, 0.4656);
		zTable.put(1.83, 0.4664);
		zTable.put(1.84, 0.4671);
		zTable.put(1.85, 0.4678);
		zTable.put(1.86, 0.4686);
		zTable.put(1.87, 0.4693);
		zTable.put(1.88, 0.4699);
		zTable.put(1.89, 0.4706);
		zTable.put(1.90, 0.4713);
		zTable.put(1.91, 0.4719);
		zTable.put(1.92, 0.4726);
		zTable.put(1.93, 0.4732);
		zTable.put(1.94, 0.4738);
		zTable.put(1.95, 0.4744);
		zTable.put(1.96, 0.4750);
		zTable.put(1.97, 0.4756);
		zTable.put(1.98, 0.4761);
		zTable.put(1.99, 0.4767);

		zTable.put(2.00, 0.4772);
		zTable.put(2.01, 0.4778);
		zTable.put(2.02, 0.4783);
		zTable.put(2.03, 0.4788);
		zTable.put(2.04, 0.4793);
		zTable.put(2.05, 0.4798);
		zTable.put(2.06, 0.4803);
		zTable.put(2.07, 0.4808);
		zTable.put(2.08, 0.4812);
		zTable.put(2.09, 0.4817);
		zTable.put(2.10, 0.4821);
		zTable.put(2.11, 0.4826);
		zTable.put(2.12, 0.4830);
		zTable.put(2.13, 0.4834);
		zTable.put(2.14, 0.4838);
		zTable.put(2.15, 0.4842);
		zTable.put(2.16, 0.4846);
		zTable.put(2.17, 0.4850);
		zTable.put(2.18, 0.4854);
		zTable.put(2.19, 0.4857);
		zTable.put(2.20, 0.4861);
		zTable.put(2.21, 0.4864);
		zTable.put(2.22, 0.4868);
		zTable.put(2.23, 0.4871);
		zTable.put(2.24, 0.4875);
		zTable.put(2.25, 0.4878);
		zTable.put(2.26, 0.4881);
		zTable.put(2.27, 0.4884);
		zTable.put(2.28, 0.4887);
		zTable.put(2.29, 0.4890);
		zTable.put(2.30, 0.4893);
		zTable.put(2.31, 0.4896);
		zTable.put(2.32, 0.4898);
		zTable.put(2.33, 0.4901);
		zTable.put(2.34, 0.4904);
		zTable.put(2.35, 0.4906);
		zTable.put(2.36, 0.4909);
		zTable.put(2.37, 0.4911);
		zTable.put(2.38, 0.4913);
		zTable.put(2.39, 0.4916);
		zTable.put(2.40, 0.4918);
		zTable.put(2.41, 0.4920);
		zTable.put(2.42, 0.4922);
		zTable.put(2.43, 0.4925);
		zTable.put(2.44, 0.4927);
		zTable.put(2.45, 0.4929);
		zTable.put(2.46, 0.4931);
		zTable.put(2.47, 0.4932);
		zTable.put(2.48, 0.4934);
		zTable.put(2.49, 0.4936);
		zTable.put(2.50, 0.4938);
		zTable.put(2.51, 0.4940);
		zTable.put(2.52, 0.4941);
		zTable.put(2.53, 0.4943);
		zTable.put(2.54, 0.4945);
		zTable.put(2.55, 0.4946);
		zTable.put(2.56, 0.4948);
		zTable.put(2.57, 0.4949);
		zTable.put(2.58, 0.4951);
		zTable.put(2.59, 0.4952);
		zTable.put(2.60, 0.4953);
		zTable.put(2.61, 0.4955);
		zTable.put(2.62, 0.4956);
		zTable.put(2.63, 0.4957);
		zTable.put(2.64, 0.4959);
		zTable.put(2.65, 0.4960);
		zTable.put(2.66, 0.4961);
		zTable.put(2.67, 0.4962);
		zTable.put(2.68, 0.4963);
		zTable.put(2.69, 0.4964);
		zTable.put(2.70, 0.4965);
		zTable.put(2.71, 0.4966);
		zTable.put(2.72, 0.4967);
		zTable.put(2.73, 0.4968);
		zTable.put(2.74, 0.4969);
		zTable.put(2.75, 0.4970);
		zTable.put(2.76, 0.4971);
		zTable.put(2.77, 0.4972);
		zTable.put(2.78, 0.4973);
		zTable.put(2.79, 0.4974);
		zTable.put(2.80, 0.4974);
		zTable.put(2.81, 0.4975);
		zTable.put(2.82, 0.4976);
		zTable.put(2.83, 0.4977);
		zTable.put(2.84, 0.4977);
		zTable.put(2.85, 0.4978);
		zTable.put(2.86, 0.4979);
		zTable.put(2.87, 0.4979);
		zTable.put(2.88, 0.4980);
		zTable.put(2.89, 0.4981);
		zTable.put(2.90, 0.4981);
		zTable.put(2.91, 0.4982);
		zTable.put(2.92, 0.4982);
		zTable.put(2.93, 0.4983);
		zTable.put(2.94, 0.4984);
		zTable.put(2.95, 0.4984);
		zTable.put(2.96, 0.4985);
		zTable.put(2.97, 0.4985);
		zTable.put(2.98, 0.4986);
		zTable.put(2.99, 0.4986);
		zTable.put(3.00, 0.4987);
		zTable.put(3.01, 0.4987);
		zTable.put(3.02, 0.4987);
		zTable.put(3.03, 0.4988);
		zTable.put(3.04, 0.4988);
		zTable.put(3.05, 0.4988);
		zTable.put(3.06, 0.4989);
		zTable.put(3.07, 0.4989);
		zTable.put(3.08, 0.4989);
		zTable.put(3.09, 0.4990);
		zTable.put(3.10, 0.4990);
		zTable.put(3.11, 0.4990);
		zTable.put(3.12, 0.4991);
		zTable.put(3.13, 0.4991);
		zTable.put(3.14, 0.4991);
		zTable.put(3.15, 0.4992);
		zTable.put(3.16, 0.4992);
		zTable.put(3.17, 0.4992);
		zTable.put(3.18, 0.4992);
		zTable.put(3.19, 0.4993);
		zTable.put(3.20, 0.4993);
		zTable.put(3.21, 0.4993);
		zTable.put(3.22, 0.4993);
		zTable.put(3.23, 0.4994);
		zTable.put(3.24, 0.4994);
		zTable.put(3.25, 0.4994);
		zTable.put(3.26, 0.4994);
		zTable.put(3.27, 0.4994);
		zTable.put(3.28, 0.4995);
		zTable.put(3.29, 0.4995);

		zTable.put(3.30, 0.4995);
		zTable.put(3.31, 0.4995);
		zTable.put(3.32, 0.4995);
		zTable.put(3.33, 0.4995);
		zTable.put(3.34, 0.4995);
		zTable.put(3.35, 0.4996);
		zTable.put(3.36, 0.4996);
		zTable.put(3.37, 0.4996);
		zTable.put(3.38, 0.4996);
		zTable.put(3.39, 0.4996);
		zTable.put(3.40, 0.4996);
		zTable.put(3.41, 0.4997);
		zTable.put(3.42, 0.4997);
		zTable.put(3.43, 0.4997);
		zTable.put(3.44, 0.4997);
		zTable.put(3.45, 0.4997);
		zTable.put(3.46, 0.4997);
		zTable.put(3.47, 0.4997);
		zTable.put(3.48, 0.4998);
		zTable.put(3.49, 0.4998);
		zTable.put(3.50, 0.4998);
		zTable.put(3.51, 0.4998);
		zTable.put(3.52, 0.4998);
		zTable.put(3.53, 0.4998);
		zTable.put(3.54, 0.4998);
		zTable.put(3.55, 0.4998);
		zTable.put(3.56, 0.4998);
		zTable.put(3.57, 0.4998);
		zTable.put(3.58, 0.4998);
		zTable.put(3.59, 0.4998);

		Double result = zTable.get(z);

		if (result != null) {

			if (z < -3.59) {
				return 0.0001;
			} else if (z > 3.59) {
				return 0.9999;
			} else if (z > 0 && z < 3.59) {
				return 0.5 + result.doubleValue();
			} else if (z < 0 && z > -3.59) {
				return 0.5 - result.doubleValue();

			}
		}
		return 0.0;

	}

	// 這個用來創建map裏面的map

	private Map<Integer, Double> createInnerMap(double... values) {
		Map<Integer, Double> innerMap = new HashMap<>();
		for (int i = 1; i <= values.length; i++) {
			innerMap.put(i, values[i - 1]);
		}
		return innerMap;
	}

	// 這個用來查ｔ值表

	public double findtTable(double alpha, int degreesOfFreedom, boolean twoTail) {

		Map<Double, Map<Integer, Double>> oneTailMap = new HashMap<>();
		oneTailMap.put(0.05,
				createInnerMap(6.3138, 2.92, 2.3534, 2.1319, 2.015, 1.9432, 1.8946, 1.8595, 1.8331, 1.8124, 1.7959,
						1.7823, 1.7709, 1.7613, 1.753, 1.7459, 1.7396, 1.7341, 1.7291, 1.7247, 1.7207, 1.7172, 1.7139,
						1.7109, 1.7081, 1.7056, 1.7033, 1.7011, 1.6991));
		oneTailMap.put(0.025,
				createInnerMap(12.7065, 4.3026, 3.1824, 2.7764, 2.5706, 2.4469, 2.3646, 2.306, 2.2621, 2.2282, 2.201,
						2.1788, 2.1604, 2.1448, 2.1314, 2.1199, 2.1098, 2.1009, 2.093, 2.086, 2.0796, 2.0739, 2.0686,
						2.0639, 2.0596, 2.0555, 2.0518, 2.0484, 2.0452));
		oneTailMap.put(0.01,
				createInnerMap(31.8193, 6.9646, 4.5407, 3.747, 3.365, 3.1426, 2.998, 2.8965, 2.8214, 2.7638, 2.7181,
						2.681, 2.6503, 2.6245, 2.6025, 2.5835, 2.5669, 2.5524, 2.5395, 2.528, 2.5176, 2.5083, 2.4998,
						2.4922, 2.4851, 2.4786, 2.4727, 2.4671, 2.462));
		oneTailMap.put(0.005,
				createInnerMap(63.6551, 9.9247, 5.8408, 4.6041, 4.0322, 3.7074, 3.4995, 3.3554, 3.2498, 3.1693, 3.1058,
						3.0545, 3.0123, 2.9768, 2.9467, 2.9208, 2.8983, 2.8784, 2.8609, 2.8454, 2.8314, 2.8188, 2.8073,
						2.797, 2.7874, 2.7787, 2.7707, 2.7633, 2.7564));
		oneTailMap.put(0.0025,
				createInnerMap(127.3447, 14.0887, 7.4534, 5.5976, 4.7734, 4.3168, 4.0294, 3.8325, 3.6896, 3.5814,
						3.4966, 3.4284, 3.3725, 3.3257, 3.286, 3.252, 3.2224, 3.1966, 3.1737, 3.1534, 3.1352, 3.1188,
						3.104, 3.0905, 3.0782, 3.0669, 3.0565, 3.0469, 3.038));
		oneTailMap.put(0.001,
				createInnerMap(318.493, 22.3276, 10.2145, 7.1732, 5.8934, 5.2076, 4.7852, 4.5008, 4.2969, 4.1437,
						4.0247, 3.9296, 3.852, 3.7874, 3.7328, 3.6861, 3.6458, 3.6105, 3.5794, 3.5518, 3.5272, 3.505,
						3.485, 3.4668, 3.4502, 3.435, 3.4211, 3.4082, 3.3962));
		oneTailMap.put(0.0005,
				createInnerMap(636.045, 31.5989, 12.9242, 8.6103, 6.8688, 5.9589, 5.4079, 5.0414, 4.7809, 4.5869,
						4.4369, 4.3178, 4.2208, 4.1404, 4.0728, 4.015, 3.9651, 3.9216, 3.8834, 3.8495, 3.8193, 3.7921,
						3.7676, 3.7454, 3.7251, 3.7067, 3.6896, 3.6739, 3.6594));

		Map<Double, Map<Integer, Double>> twoTailMap = new HashMap<>();
		twoTailMap.put(0.1,
				createInnerMap(6.3138, 2.92, 2.3534, 2.1319, 2.015, 1.9432, 1.8946, 1.8595, 1.8331, 1.8124, 1.7959,
						1.7823, 1.7709, 1.7613, 1.753, 1.7459, 1.7396, 1.7341, 1.7291, 1.7247, 1.7207, 1.7172, 1.7139,
						1.7109, 1.7081, 1.7056, 1.7033, 1.7011, 1.6991));
		twoTailMap.put(0.05,
				createInnerMap(12.7065, 4.3026, 3.1824, 2.7764, 2.5706, 2.4469, 2.3646, 2.306, 2.2621, 2.2282, 2.201,
						2.1788, 2.1604, 2.1448, 2.1314, 2.1199, 2.1098, 2.1009, 2.093, 2.086, 2.0796, 2.0739, 2.0686,
						2.0639, 2.0596, 2.0555, 2.0518, 2.0484, 2.0452));
		twoTailMap.put(0.02,
				createInnerMap(31.8193, 6.9646, 4.5407, 3.747, 3.365, 3.1426, 2.998, 2.8965, 2.8214, 2.7638, 2.7181,
						2.681, 2.6503, 2.6245, 2.6025, 2.5835, 2.5669, 2.5524, 2.5395, 2.528, 2.5176, 2.5083, 2.4998,
						2.4922, 2.4851, 2.4786, 2.4727, 2.4671, 2.462));
		twoTailMap.put(0.01,
				createInnerMap(63.6551, 9.9247, 5.8408, 4.6041, 4.0322, 3.7074, 3.4995, 3.3554, 3.2498, 3.1693, 3.1058,
						3.0545, 3.0123, 2.9768, 2.9467, 2.9208, 2.8983, 2.8784, 2.8609, 2.8454, 2.8314, 2.8188, 2.8073,
						2.797, 2.7874, 2.7787, 2.7707, 2.7633, 2.7564));
		twoTailMap.put(0.005,
				createInnerMap(127.3447, 14.0887, 7.4534, 5.5976, 4.7734, 4.3168, 4.0294, 3.8325, 3.6896, 3.5814,
						3.4966, 3.4284, 3.3725, 3.3257, 3.286, 3.252, 3.2224, 3.1966, 3.1737, 3.1534, 3.1352, 3.1188,
						3.104, 3.0905, 3.0782, 3.0669, 3.0565, 3.0469, 3.038));
		twoTailMap.put(0.002,
				createInnerMap(318.493, 22.3276, 10.2145, 7.1732, 5.8934, 5.2076, 4.7852, 4.5008, 4.2969, 4.1437,
						4.0247, 3.9296, 3.852, 3.7874, 3.7328, 3.6861, 3.6458, 3.6105, 3.5794, 3.5518, 3.5272, 3.505,
						3.485, 3.4668, 3.4502, 3.435, 3.4211, 3.4082, 3.3962));
		twoTailMap.put(0.001,
				createInnerMap(636.045, 31.5989, 12.9242, 8.6103, 6.8688, 5.9589, 5.4079, 5.0414, 4.7809, 4.5869,
						4.4369, 4.3178, 4.2208, 4.1404, 4.0728, 4.015, 3.9651, 3.9216, 3.8834, 3.8495, 3.8193, 3.7921,
						3.7676, 3.7454, 3.7251, 3.7067, 3.6896, 3.6739, 3.6594));

		Map<Double, Map<Integer, Double>> selectedMap = twoTail ? twoTailMap : oneTailMap;
		if (selectedMap.containsKey(alpha)) {
			// Get the inner map for the specific alpha value
			Map<Integer, Double> innerMap = selectedMap.get(alpha);

			// Check if the 'degreesOfFreedom' value exists in the inner map
			if (innerMap.containsKey(degreesOfFreedom)) {
				// Get the value corresponding to the alpha and degrees of freedom
				double result = innerMap.get(degreesOfFreedom);

				// Now 'result' contains the desired value
				return result;
			} else {
				// Handle case where degrees of freedom value is not found
				throw new IllegalArgumentException("Degrees of freedom not found for alpha: " + alpha);
			}
		} else {
			// Handle case where alpha value is not found
			throw new IllegalArgumentException("Alpha value not found: " + alpha);
		}
	}

}