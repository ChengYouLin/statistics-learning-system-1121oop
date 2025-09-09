package final_project_;

import java.util.ArrayList;


public class HypothesisTest extends DescriptiveStat {
	private double a, avg, var;
	int n;
	private double hypo, xbar;
	private double pValue;
	private String direct;


	public HypothesisTest(ArrayList<Double> data, double[] data_array, String name) {
		super(data, null, name);
		this.a = 0.05;
		this.var = 0;
		this.avg = 0;
		this.n = 2;
		this.xbar = 0;
	}


	public double getAlpha() {
		return a;
	}

	public double getAvg() {
		return avg;
	}

	public double getVar() {
		return var;
	}

	public double getXbar() {
		return xbar;
	}
	
	public int getNum() {
		return n;
	}

	public void setAlpha(double alpha) {
		this.a = alpha;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setVar(double var) {
		this.var = var;
	}

	public void setNum(int num) {
		this.n = num;
	}

	public void setXbar(double xbar) {
		this.xbar = xbar;
	}


	public void setNullHypo(double hypo, String direct) {

		this.hypo = hypo;
		this.direct = direct;

		boolean flag = false;
		while (!flag) {
			if (direct.equals("左尾")) {
				System.out.println("H0: μ>=" + hypo + " H1: μ<" + hypo);
				flag = true;
			} else if (direct.equals("右尾")) {
				System.out.println("H0: μ<=" + hypo + " H1: μ>" + hypo);
				flag = true;
			} else if (direct.equals("雙尾")) {
				System.out.println("H0: μ=" + hypo + " H1: μ≠" + hypo);
				flag = true;
			} else {
				System.out.println("輸入錯誤，請重新輸入");
			}
		}
	}


	public void calculatePValue() {

		if (this.n == 0) {
			double diff = (this.xbar - this.hypo);
			double error = super.populationStandardDeviation() / Math.sqrt(data.size());
			double z = Math.round(100.0 * (diff / error)) / 100.0;

			double p = super.findzTable(z);

			if (0 < z && z < 3.59) {
				this.pValue = p; // 返回 result，自動拆箱為 double
			} else if (z > 3.59) {
				this.pValue = 0.4999; // 直接返回 double 值
			} else if (z < 0) {
				this.pValue = 0.5 - p; // 返回 double，自動拆箱為 double
			}

			System.out.println("可得此情形下，檢定統計量為 " + z);

		} else {
			double diff = this.xbar - this.hypo;
			double error = Math.sqrt(this.var) / Math.sqrt(this.n);
			double z = Math.round(100.0 * (diff / error)) / 100.0;

			double p = super.findzTable(z);

			if (0 < z && z < 3.59) {
				this.pValue = p; // 返回 result，自動拆箱為 double
			} else if (z > 3.59) {
				this.pValue = 0.4999; // 直接返回 double 值
			} else if (z < 0) {
				this.pValue = 0.5 - p; // 返回 double，自動拆箱為 double
			}

			System.out.println("可得此情形下，檢定統計量為 " + z);
		}
	}


	public double tCalculatePValue() {

		if (this.n == 0) {
			double diff = (this.xbar - this.hypo);
			double error = super.populationStandardDeviation() / Math.sqrt(data.size());
			double t = Math.round(100.0 * (diff / error)) / 100.0;

			return t;

		} else {
			double diff = this.xbar - this.hypo;
			double error = Math.sqrt(this.var) / Math.sqrt(this.n);
			double t = Math.round(100.0 * (diff / error)) / 100.0;

			return t;
		}
	}


	public void analysis(double t) {

		if (direct.equals("雙尾")) {

			if (pValue > a / 2.0) {
				System.out.println("在此情境中，由於p-value("+pValue+")大於alpha("+a+")，可知此資料無法拒絕H0之假設");
			} else {
				System.out.println("在此情境中，由於p-value("+pValue+")小於alpha("+a+")，可知此資料無法拒絕H0之假設");
			}

		} else {

			if (pValue > a) {
				System.out.println("在此情境中，由於p-value("+pValue+")大於alpha("+a+")，可知此資料無法拒絕H0之假設");
			} else {
				System.out.println("在此情境中，由於p-value("+pValue+")小於alpha("+a+")，可知此資料無法拒絕H0之假設");
			}
		}
	}

	public void tAnalysis(double t) {

		this.a = Math.round(this.a * 100.0) / 100.0;

		if (direct.equals("右尾")) {

			Double tValue = super.findtTable(this.a, this.n, false);

			if (tValue > t) {
				System.out.println("在此情境中，由於t值("+t+")小於臨界值("+tValue+")，可知此資料無法拒絕H0之假設");
			} else {
				System.out.println("在此情境中，由於t值("+t+")大於臨界值("+tValue+")，可知此資料拒絕H0之假設");
			}

		} else if (direct.equals("左尾")) {

			double tValue = super.findtTable(this.a, this.n, false);
			if (tValue < t) {
				System.out.println("在此情境中，由於t值("+t+")大於臨界值("+tValue+")，可知此資料無法拒絕H0之假設");
			} else {
				System.out.println("在此情境中，由於t值("+t+")小於臨界值("+tValue+")，可知此資料拒絕H0之假設");
			}

		} else if (direct.equals("雙尾")) {

			Double tValue = super.findtTable(this.a, this.n, true);

			if (-tValue < t && tValue > t) {
				System.out.println("在此情境中，由於t值("+t+")落在正負臨界值("+tValue+")之間，可知此資料無法拒絕H0之假設");
			} else {
				System.out.println("在此情境中，由於t值無落在正負臨界值之間，可知此資料拒絕H0之假設");
			}

		}

	}

	public void instruction() {

		System.out.println("假說檢定（英語：hypothesis testing）是推論統計中用於檢定現有數據是否足以支持特定假設的方法。\n"
				+ "一旦能估計未知母數，就會希望根據結果對未知的真正母數值做出適當的推論。\n" + "欲檢定統計上假設的正確性的為虛無假說，虛無假說通常由研究者決定，反映研究者對未知母數的看法。\n"
				+ "相對於虛無假說的其他有關母數之論述是對立假說，它通常反應了執行檢定的研究者對母數可能數值的另一種（對立的）看法\n");
	}

	public void instruction1() {

		System.out.println("以下說明此步驟之意義：\n" + "在檢定之前，我們需要針對情境進行假設。其中包含「虛無假設」以及「對立假設」兩種情況：\n"
				+ "「虛無假設」對母體參數提出一個主張，假設此主張為「真實」（除非能證明此主張非真！）。\n"
				+ "對立假設是相對於虛無假設所提出的另一個不同(相反）的假設或主張，必須有足夠的證據，才能說明此主張為真。\n" + "檢定方式又因虛無假設之不同，分為三種檢定：\n"
				+ "雙尾檢定： H0 : θ=θ0； H1 : θ≠θ0\n" + "左尾檢定： H0 : θ≧θ0 ； H1 : θ<θ0\n" + "右尾檢定： H0 : θ≦θ0 ； H1 : θ>θ0");

	}

	public void instruction2() {

		System.out.println("檢定統計量是由樣本所算出來的一個值，用來決定是否接受或拒絕 H0。\n" + "不同參數的假設檢定，使用不同的檢定統量。常用的檢定統計量有：Z, t。\n"
				+ "在母體平均數的假設檢定裡，不同的情形下使用不同的檢定統計量。\n"
				+ "若母體變異數已知，則使用z = (xbar-μ)/(σ-√n)\n，其中xbar代表檢定量、μ代表母體平均數、σ代表母體標準差、n代表母體樣本數量"
				+ "若母體變異數未知，但樣本數超過30，可將樣本視為一母體，同樣使用z = (xbar-μ)/(σ-√n)\n"
				+ "若母體變異數未知，但樣本數小於30，則同樣使用t = (xbar-μ)/(s-√n)，其中s代表樣本標準差\n");

	}

	public void instruction3() {

		System.out
				.println("研究人員必須決定一個決策法則，以瞭解何時『不拒絕』 H0 ；何時拒絕 H0 。\n" + "一般我們說『不拒絕』H0 ，而不說接受 H0 ，因為我們只是沒有足夠證據拒絕，而不是接受。\n"
						+ "決策法則通常是決定一個不拒絕域 (NonrejectionRegion, 或稱接受域 ) 與拒絕域 (Rejection Region)。\n"
						+ "當檢定統計量落入不拒絕域： 『不拒絕』 H0 ！\n" + "當檢定統計量落入拒絕域：拒絕 H0 ；接受 H1\n"
						+ "接受域與拒絕域的接點，稱為臨界點(Critical Point)。\n" + "臨界值的決定，是根據顯著水準α並利用機率分配計算而得，分成三種形式：\n"
						+ "雙尾檢定 (落在兩邊拒絕)、右尾檢定 (落在右邊拒絕)、左尾檢定 (落在左邊拒絕)\n"
						+ "在z檢定時，我們也會經由計算其p-value並與alpha值比較，並得到結果\n" + "一般來說，當p-value小於alpha，則拒絕H0");

	}

}
