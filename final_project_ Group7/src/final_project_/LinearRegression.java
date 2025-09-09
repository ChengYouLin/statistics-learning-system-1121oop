package final_project_;

public class LinearRegression extends DescriptiveStat {
	private double b0;
	private double b1;
	private double alpha;
	private double sumX = 0, xVar;
	private double[] xValues;
	private String xName;

	public LinearRegression(String yName, double[] sample, String xName, double[] xValues, double alpha, double xVar) {
		super(null, sample, yName);
		this.xValues = xValues;
		this.alpha = alpha;
		this.xVar = xVar;
		this.xName = xName;

		for (int i = 0; i < xValues.length; i++) {
			sumX += xValues[i];
		}

		// 如果他輸入的變異數為0 ，我暫時判定他是不知道母體變異數，所以直接幫他算樣本變異數。
		if (xVar == 0) {
			double avgX = sumX - (xValues.length - 1);
			double x = 0;
			for (int i = 0; i < xValues.length; i++) {
				x = Math.pow(xValues[i] - avgX, 2);
			}
			xVar = x / (xValues.length - 1);
		}

		calculateParameters(); // 計算回歸中會用到的參數
	}

	// 初始化時，就會呼叫他來計算回歸中會用到的參數，並計算完b0 b1
	private void calculateParameters() {
		double n = xValues.length;
		double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;

		for (int i = 0; i < n; i++) {
			sumX += xValues[i];
			sumY += getData()[i];
			sumXY += xValues[i] * getData()[i];
			sumXX += xValues[i] * xValues[i];
		}

		// b1 = [nΣxy - ΣxΣy] / [nΣxx - (Σx)^2]
		b1 = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
		// b0 = [Σy - b1 * Σx] / n
		b0 = (sumY - b1 * sumX) / n;
	}

	// 輸出線性回歸得結果 ŷ 公式
	public String simpleLinearRegression() {
		String result;
		result = "線性回歸模型 - " + "\nbeta0(斜率): " + b1 + "\nbeta1(截距 ): " + b0 + "\n";
		result = result + "本數據的迴歸模型是: ŷ = " + b1 + "x + " + b0 + "\n";

		if (b1 > 0) {
			result = result + "由於 b1>0 , 所以我們可以說: " + getName() + " 和 " + xName + " 可能有正相關的關係（不一定是因果關係）。";
		} else if (b1 < 0) {
			result = result + "由於 b1<0 , 所以我們可以說: " + getName() + " 和 " + xName + " 可能有負相關的關係（不一定是因果關係）。";
		} else {
			result = result + "由於 b1=0 或其他無法判斷之值 , 所以我們可以說: " + getName() + " 和 " + xName + " 沒有太大的關係或需要再調整與做更多的實驗設計。";
		}

		return result;
	}

	public double predict(double x) {
		return b0 + b1 * x;
	}

	// 解釋什麼是線性回歸
	public String linearRegressionExplain() {
		String result;
		result = "Statitical (True) Model is: y = f(x) + ε \n";
		result = result + "其中： y = 應變數  ； x = 自變數 \n";
		result = result + "Statitical (True) Model ----->  Fitted model , eg: ŷ = b1x + b0\n";
		result = result + "利用 data 來 尋找Ｙ和Ｘ的關係，找到一條最適配直線；注意：「結果不一定是因果關係」亦「不局限於二維數據」\n";
		return result;
	}

	// 解釋簡單線性回歸中的含義
	public String simpleLinearRegressionExplain() {
		String result;
		result = "Simple Linear Regression Explanation.\n";
		result = result + "=> involves one independent variable and one dependent variable.\n";
		result = result + "Suppose: y = B0 + B1x + ε , then E(y) = B0 + B1x , ε~NID(0 , σ^2)\n";
		result = result + "Sampling and Fitted : ŷ = b0 + b1x\n";
		result = result + "Estimated: ŷ = b0 + b1x -----> E(y) = B0 + B1x\n";
		result = result + "			  b0 -----> B0  ;  b1 -----> B1\n";
		result = result + "	 (redisual) e = y - ŷ  -----> ε = y - E(y)\n";
		result = result + "				by min Σ(y - ŷ)^2 = min Σ(y - b0 + b1x)^2\n";
		result = result + "				由 ∂SSE / ∂b0 = 0  and  ∂SSE / ∂b1 = 0\n";
		result = result + "				得 b1 = Σ(x - x̄)^2 (y - ȳ)^2 / Σ(x - x̄)^2\n";
		result = result + "				   b0 = ȳ - b1x̄";
		result = result + "";
		return result;
	}

	// 接下來計算 Coefficient Determination 判定係數

	public double calculateSSE() {
		double sse = 0;
		for (int i = 0; i < xValues.length; i++) {
			double predicted = predict(xValues[i]);
			double error = getData()[i] - predicted;
			sse += Math.pow(error, 2);
		}
		return sse;
	}

	public double calculateSST() {
		double sst = 0;
		double xAvg = 0;
		if (xVar == 0) {
			xAvg = sumX / (xValues.length - 1);
		} else {
			xAvg = sumX / xValues.length;
		}

		for (int i = 0; i < xValues.length; i++) {
			sst += Math.pow(getData()[i] - xAvg, 2);
		}
		return sst;
	}

	public double calculateSSR() {
		return calculateSST() - calculateSSE();
	}

	public double calculateRSquared() {
		return calculateSSR() / calculateSST();
	}

	// 相關係數
	public double calculateCorrelationCoeff() {
		return Math.signum(b1) * Math.sqrt(calculateRSquared());
	}

	public String coefficientDetermination() {
		String result = "";

		result = result + ("在這個迴歸模型中，計算了以下的數據：\n");
		result = result + ("SSE (Sum of Squares for Error): " + calculateSSE() + "\n");
		result = result + ("SST (Sum of Squares for Total): " + calculateSST() + "\n");
		result = result + ("SSR (Sum of Squares due to Eegression): " + calculateSSR() + "\n");
		result = result + ("R squared: " + calculateRSquared() + "\n");
		result = result + ("r_xy: " + calculateCorrelationCoeff() + "\n");
		return result;
	}

	public String coefficientDeterminationExplain() {
		String result = "";

		result = result + ("SSE = Σ(y - ŷ)^2 是「實際值 y」與「預測值 ŷ」之間的差的平方和；\n");
		result = result + ("SST = Σ(y - ȳ)^2 是「實際值 y」與「平均值 ȳ」之間的差的平方和；\n");
		result = result + ("SSR = Σ(ŷ - ȳ)^2 是「預測值 ŷ」與「平均值 ȳ」之間的差的平方和。\n");
		result = result + ("=> SST = SSR + SSE .");
		result = result + ("R^2 = SSR / SST 量化了迴歸模型擬合資料的程度，亦稱為解釋力。\n");
		result = result + ("r_xy = (sign of b1) √(coefficient of determination = R^2)\n");
		result = result + ("	 = Σ(x - x̄)(y - ȳ) / √(Σ(x - x̄)^2 Σ(y - ȳ)^2)");
		result = result + ("	代表的是 x 和 y 之間的線性相關性，其範圍介於 -1 (完全負相關) 到 1 (完全正相關)。");

		return result;
	}

	// Assumption About the Error term ε (test the slope)

	// 已知母體
	public String testSlopeVarianceKnown() {
		String result = "Hypothesis Testing: H0: B1=0 ; H1: B1≠0 ."
				+ "\nTest Statistic: Z = b1-B1 / √(σ^2 / Σ(x - x̄)^2) ~ N(0,1) under H0 is true.";
		double z = 0;
		double x = 0, avgX = 0;
		avgX = sumX - xValues.length;

		for (int i = 0; i < xValues.length; i++) {
			x = Math.pow((xValues[i] - avgX), 2);
		}

		z = (b1 - 0) / Math.sqrt(xVar / x);

		// 假設檢定判斷，先把alpha值的Ｚ值找出來
		double alphaNew = 0;

		if (z > 0) {
			if (alpha == 0.1) {
				alphaNew = 1.64;
				alphaNew = findzTable(alphaNew);
			} else if (alpha == 0.01) {
				alphaNew = 2.57;
				alphaNew = findzTable(alphaNew);
			} else {
				alphaNew = 1.96;
				alphaNew = findzTable(alphaNew);
			}
		} else {
			if (alpha == 0.1) {
				alphaNew = 1.64;
				alphaNew = findzTable(alphaNew);
				alphaNew = 1 - alphaNew;
			} else if (alpha == 0.01) {
				alphaNew = 2.57;
				alphaNew = findzTable(alphaNew);
				alphaNew = 1 - alphaNew;
			} else {
				alphaNew = 1.96;
				alphaNew = findzTable(alphaNew);
				alphaNew = 1 - alphaNew;
			}
		}

		// 一樣先判斷左偏或右偏
		if (z < 0) {
			z = z * (0 - 1);
			z = findzTable(z);
			z = 1 - z;

			if (z <= alphaNew) {
				result = result + "\nBecause " + x + " < " + alphaNew + ", reject H0 ! \n" + "+"
						+ "我們不能否認斜率是不為0的假設，斜率實際上是顯著的!";
			} else {
				result = result + "\nBecause " + x + " > " + alphaNew + ", do not reject H0 ! ";
			}
		} else {
			z = findzTable(z);
			if (z >= alphaNew) {
				result = result + "\nBecause " + x + " < " + alphaNew + ", reject H0 ! ";
			} else {
				result = result + "\nBecause " + x + " > " + alphaNew + ", do not reject H0 ! ";
			}
		}
		return result;
	}

	public String testSlopeVarianceKnownExplain() {
		String result = "Assumption About the Error term ε (test the slope)\n";

		result = result + "條件: (σ^2 known)" + "\n";
		result = result + "		  y  = B0 + B1x + ε , ε~NID(0 , σ^2)" + "\n";
		result = result + "		E(y) = B0 + B1x" + "\n";
		result = result + "		  ŷ  = b0 + b1x" + "\n";
		result = result + "		  b1 = Σ(x - x̄)^2 (y - ȳ)^2 / Σ(x - x̄)^2" + "\n";
		result = result + "	  => E(b1) = B1 ; Var(b1) = σ^2 / Σ(x - x̄)^2 (∵ ε~NID(0 , σ^2))" + "\n";
		result = result + "	  => b1 ~ N(B1 , σ^2 / Σ(x - x̄)^2)" + "\n";

		return result;
	}

	// 母體未知
	public String testSlopeVarianceUnknown() {
		String result = "Hypothesis Testing: H0: B1=0 ; H1: B1≠0 ."
				+ "\nTest Statistic: t = b1-B1 / √(MSE / Σ(x - x̄)^2) ~ t(n-2) under H0 is true.";
		double t = 0;
		double x = 0, avgX = 0, MSE = 0;
		avgX = sumX - (xValues.length - 1);

		for (int i = 0; i < xValues.length; i++) {
			x = Math.pow((xValues[i] - avgX), 2);
		}
		MSE = calculateSSE() / (xValues.length - 2);

		t = (b1 - 0) / Math.sqrt(MSE / x);

		// 先把alpha的t值找出來
		double alphaNew;
		alphaNew = findtTable(alpha, (xValues.length - 2), false);

		// 一樣先判斷左偏或右偏
		if (t < 0) {
			alphaNew = 1 - alphaNew;
			if (t <= alphaNew) {
				result = result + "\nBecause " + x + " < " + alphaNew + ", reject H0 ! \n"
						+ "我們不能否認斜率是不為0的假設，斜率實際上是顯著的!";
			} else {
				result = result + "\nBecause " + x + " > " + alphaNew + ", do not reject H0 ! ";
			}
		} else {
			if (t >= alphaNew) {
				result = result + "\nBecause " + x + " < " + alphaNew + ", reject H0 ! ";
			} else {
				result = result + "\nBecause " + x + " > " + alphaNew + ", do not reject H0 ! ";
			}
		}
		return result;
	}

	public String testSlopeVarianceUnknownExplain() {
		String result = "Assumption About the Error term ε (test the slope)\n";

		result = result + "條件: (σ^2 unknown)" + "\n";
		result = result + "		  y  = B0 + B1x + ε , ε~NID(0 , σ^2)" + "\n";
		result = result + "		E(y) = B0 + B1x" + "\n";
		result = result + "		  ŷ  = b0 + b1x" + "\n";
		result = result + "		  b1 = Σ(x - x̄)^2 (y - ȳ)^2 / Σ(x - x̄)^2" + "\n";
		result = result + "	  	Estimated: MSE = (SSE / n-2)  -----> σ^2" + "\n";
		result = result + "				   (n-2 的原因：一個是b0 一個是b1) " + "\n";
		result = result + "	  			   => E(MSE) = σ^2 ; MSE = Σ(y - ŷ)^2 / n-2" + "\n";
		result = result + "	  => E(b1) = B1 ; Var(b1) = MSE / Σ(x - x̄)^2 (∵ ε~NID(0 , σ^2))" + "\n";

		return result;
	}

	// Using the Estimated Regression Equation for Estimation and Prediction

	public String confidenceIntervalVarKnown(double xOld) {

		double alphaNew;
		if (alpha == 0.1) {
			alphaNew = 1.64;
			alphaNew = findzTable(alphaNew);
		} else if (alpha == 0.01) {
			alphaNew = 2.57;
			alphaNew = findzTable(alphaNew);
		} else {
			alphaNew = 1.96;
			alphaNew = findzTable(alphaNew);
		}

		String result = "";
		double x = 0, max = 0, min = 0;
		double avgX = sumX / xValues.length;

		for (int i = 0; i < xValues.length; i++) {
			x = Math.pow((xValues[i] - avgX), 2);
		}
		x = Math.sqrt(xVar * (Math.pow((xOld - avgX), 2) / x));

		x = Math.round(x * 1000);
		x = x / 1000;

		max = predict(xOld) + alphaNew * x;
		min = predict(xOld) - alphaNew * x;

		result = "E(y|x = xOld) = ŷ ∓ " + alphaNew + " * " + x + "\n";
		result = result + "=> Max: " + max + " ~ Min: " + min + "\n";

		return result;
	}

	public String confidenceIntervalVarKnownExplain() {
		String result = "Using the Estimated Regression Equation for Estimation and Prediction\n";

		result = result + "Confidence interval: an interval estimate of the mean of y for a given of x*" + "\n";
		result = result + "(x* is an old point, ŷ estimate E(y))" + "\n";
		result = result + "b1 -----> B1 , b1 ~ N(B1 , σ^2 / Σ(x - x̄)^2)" + "\n";
		result = result + "ŷ = b0 + b1x -----> E(y) = B0 + B1x" + "\n";
		result = result + "Sampling distribution of ŷ" + "\n";
		result = result + "		E(ŷ) = B0 + B1x" + "\n";
		result = result
				+ "	  Var(ŷ) = Var(ȳ + b1(x* - x̄)) = σ^2/n + (x* - x̄)^2 * σ^2 / Σ(x - x̄)^2 = σ^2 (1/n + (x* - x̄)^2 / Σ(x - x̄)^2)"
				+ "\n";
		result = result + "	  => ŷ ~ N(E(y),σ^2 (1/n + (x* - x̄)^2 / Σ(x - x̄)^2)" + "\n";
		result = result + "" + "\n";
		result = result + "=> E(y|x=x*) = ŷ ∓ Z * √(σ^2*(1/n + (x* - x̄)^2 / Σ(x - x̄)^2))" + "\n";

		return result;
	}

	public String confidenceIntervalVarUnknown(double xOld) {

		double alphaNew;
		alphaNew = findtTable(alpha, (xValues.length - 2), false);

		String result = "";
		double x = 0, max = 0, min = 0, MSE = 0;
		double avgX = sumX / (xValues.length - 1);

		for (int i = 0; i < xValues.length; i++) {
			x = Math.pow((xValues[i] - avgX), 2);
		}
		MSE = calculateSSE() / (xValues.length - 2);
		x = Math.sqrt(MSE * (Math.pow((xOld - avgX), 2) / x));

		x = Math.round(x * 1000);
		x = x / 1000;

		max = predict(xOld) + alphaNew * x;
		min = predict(xOld) - alphaNew * x;

		result = "E(y|x = xOld) = ŷ ∓ " + alphaNew + " * " + x + "\n";
		result = result + "=> Max: " + max + " ~ Min: " + min + "\n";

		return result;
	}

	public String confidenceIntervalVarUnknownExplain() {
		String result = "Using the Estimated Regression Equation for Estimation and Prediction\n";

		result = result + "Confidence interval: an interval estimate of the mean of y for a given of x*" + "\n";
		result = result + "(x* is an old point, ŷ estimate E(y))" + "\n";
		result = result + "b1 -----> B1 , b1 ~ N(B1 , σ^2 / Σ(x - x̄)^2)" + "\n";
		result = result + "ŷ = b0 + b1x -----> E(y) = B0 + B1x" + "\n";
		result = result + "Sampling distribution of ŷ" + "\n";
		result = result + "		E(ŷ) = B0 + B1x" + "\n";
		result = result
				+ "	  Var(ŷ) = Var(ȳ + b1(x* - x̄)) = σ^2/n + (x* - x̄)^2 * σ^2 / Σ(x - x̄)^2 = σ^2 (1/n + (x* - x̄)^2 / Σ(x - x̄)^2)"
				+ "\n";
		result = result + "	  Estimate: MSE -----> σ^2";
		result = result + "" + "\n";
		result = result + "=> E(y|x=x*) = ŷ ∓ t(alpha/2)(n-2) *  √(MSE *(1/n + (x* - x̄)^2 / Σ(x - x̄)^2))" + "\n";

		return result;
	}

	public String predictionIntervalVarUnknown(double xNew) {

		double alphaNew;
		alphaNew = findtTable(alpha, (xValues.length - 2), false);

		String result = "";
		double x = 0, max = 0, min = 0, MSE = 0;
		double avgX = sumX / xValues.length;

		for (int i = 0; i < xValues.length; i++) {
			x = Math.pow((xValues[i] - avgX), 2);
		}
		MSE = calculateSSE() / (xValues.length - 2);
		x = Math.sqrt(MSE * (1 + Math.pow((xNew - avgX), 2) / x));

		x = Math.round(x * 1000);
		x = x / 1000;

		max = predict(xNew) + alphaNew * x;
		min = predict(xNew) - alphaNew * x;

		result = "E(y|x = xOld) = ŷ ∓ " + alphaNew + " * " + x + "\n";
		result = result + "=> Max: " + max + " ~ Min: " + min + "\n";

		return result;
	}

	public String predictionIntervalVarUnknownExplain() {
		String result = "Using the Estimated Regression Equation for Estimation and Prediction\n";

		result = result + "Prediction interval: used x0 wherver we want to predict an individual value  of y" + "\n";
		result = result + "(x0 is an new point, ŷ estimate y)" + "\n";
		result = result + "" + "\n";
		result = result + "(x0, y = ?)" + "\n";
		result = result + "ŷ(x0) -----> point predict of y" + "\n";
		result = result + "Find the sampling distribution of ŷ(x0) - y(x0)" + "\n";
		result = result + "		E(ŷ(x0) - y(x0)) = E(b0 + b1x) - E(y|x0) = 0" + "\n";
		result = result
				+ "	  Var(ŷ(x0) - y(x0)) = Var(ŷ(x0)) + Var(y(x0)) = σ^2 (1/n + (x* - x̄)^2 / Σ(x - x̄)^2) + σ^2 "
				+ "\n";
		result = result + "						 = 1 + σ^2 (1/n + (x* - x̄)^2 / Σ(x - x̄)^2)" + "\n";
		result = result + "   => ŷ ~ N(0,σ^2 (1 + 1/n + (x* - x̄)^2 / Σ(x - x̄)^2)" + "\n";
		result = result + "" + "\n";
		result = result + "Estimate: MSE -----> σ^2" + "\n";
		result = result + "=> t = (ŷ(x0) - y(x0)) - 0 / √(MSE * (1 + 1/n + (x* - x̄)^2 / Σ(x - x̄)^2))" + "\n";
		result = result + "" + "\n";
		result = result + "=> E(y|x=x0) = ŷ ∓ t(alpha/2)(n-2) *  √(MSE *(1 + 1/n + (x* - x̄)^2 / Σ(x - x̄)^2))" + "\n";

		return result;
	}

}