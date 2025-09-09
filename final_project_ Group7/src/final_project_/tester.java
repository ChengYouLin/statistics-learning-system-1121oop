package final_project_;

import java.util.ArrayList;
import java.util.Scanner;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("您好！歡迎使用統計計算系統，請問您今天想要使用什麼功能？\n" + "假設檢定h、迴歸分析l、變異數分析a、關閉系統q");
			String system = sc.next();

			// 進入假設檢定之功能

			if (system.equalsIgnoreCase("h")) {

				HypothesisTest test = new HypothesisTest(null, null, "name");
				System.out.println("歡迎使用「假設檢定」功能，請問有需要為您進行假設檢定之概念講解嗎？(輸入y/n)");

				if (sc.next().equalsIgnoreCase("y")) {
					test.instruction();
				}

				System.out.println("第一個步驟為「確立虛無假設」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

				if (sc.next().equalsIgnoreCase("y")) {
					test.instruction1();
				}

				System.out.println("\n題目是否有提供敘述統計量？(輸入y/n)");
				String userInput = sc.next();

				if (userInput.equalsIgnoreCase("y")) {

					System.out.println("請依序輸入「虛無假設值」以及選擇「左尾、右尾、雙尾」");
					test.setNullHypo(sc.nextDouble(), sc.next());

					System.out.println("您想進行z檢定還是t檢定（a.z檢定 b.t檢定(僅提供29筆以下之數據使用)，輸入a/b）");

					String testChoose = sc.next();
					double t = 0;

					if (testChoose.equalsIgnoreCase("a")) {

						System.out.println("請依序輸入「樣本平均數」、「母體變異數」、「信心水準」(1-alpha)、「母體數量」");
						test.setXbar(sc.nextDouble());
						test.setVar(sc.nextDouble());
						test.setAlpha(Math.round(100.0 * (1 - sc.nextDouble())) / 100.0);
						test.setNum(sc.nextInt());

						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equalsIgnoreCase("y")) {
							test.instruction2();
						}
						test.calculatePValue();

						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equalsIgnoreCase("y")) {
							test.instruction3();
						}
						test.analysis(t);

					} else if (testChoose.equalsIgnoreCase("b")) {

						System.out.println("請依序輸入「樣本平均數」、「樣本變異數」、「信心水準」(1-alpha)、「母體數量」");
						test.setXbar(sc.nextDouble());
						test.setVar(sc.nextDouble());
						test.setAlpha(Math.round(100.0 * (1 - sc.nextDouble())) / 100.0);
						test.setNum(sc.nextInt());

						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equalsIgnoreCase("y")) {
							test.instruction2();
						}
						t = test.tCalculatePValue();
						System.out.println("可得此情形下，檢定統計量為" + t + "，自由度為" + (test.getNum() - 1));

						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equalsIgnoreCase("y")) {
							test.instruction3();
						}
						test.tAnalysis(t);

					}
					// 以下是要自己手動輸入資料的程式碼
				} else if (userInput.equalsIgnoreCase("n")) {

					ArrayList<Double> dataList = new ArrayList<>();

					while (true) {
						System.out.print("輸入資料（輸入 'q' 結束）：");
						String userInputnum = sc.next();

						if (userInputnum.equalsIgnoreCase("q")) {
							break; // 用戶輸入 'q'，跳出迴圈
						}

						try {
							// 嘗試將輸入轉換為 double
							double number = Double.parseDouble(userInputnum);
							dataList.add(number);
						} catch (NumberFormatException e) {
							System.out.println("請輸入有效的數字或 'q' 來結束。");
						}
					}

					HypothesisTest test1 = new HypothesisTest(dataList, null, "name");
					System.out.println("請依序輸入「虛無假設值」以及「左尾、右尾、雙尾」");
					test1.setNullHypo(sc.nextDouble(), sc.next());

					System.out.println("您想進行z檢定還是t檢定（a.z檢定 b.t檢定(僅提供29筆以下之數據使用)，輸入a/b");

					String testChoose = sc.next();
					double t = 0;

					if (testChoose.equalsIgnoreCase("a")) {

						System.out.println("請輸入信心水準(1-alpha)");
						test1.setAvg(test1.mean());
						test1.setVar(test1.populationVariance());
						test1.setAlpha(Math.round(100.0 * (1 - sc.nextDouble())) / 100.0);

						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							test1.instruction2();
						}
						test1.calculatePValue();

						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							test1.instruction3();
						}
						test1.analysis(t);

					} else if (testChoose.equalsIgnoreCase("b")) {

						System.out.println("請輸入信心水準");
						test1.setAvg(test1.mean() * dataList.size() / (dataList.size() - 1));
						test1.setVar(test1.populationVariance() * dataList.size() / (dataList.size() - 1));
						test1.setNum(dataList.size());
						test1.setAlpha(Math.round(100.0 * (1 - sc.nextDouble())) / 100.0);

						System.out.println("第二個步驟為「計算檢定統計量」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							test1.instruction2();
						}
						double t1 = test1.tCalculatePValue();
						System.out.println("可得此情形下，檢定統計量為" + t1 + "，自由度為" + test.getNum());

						System.out.println("第三個步驟為「針對結果進行分析」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							test1.instruction3();
						}
						test1.tAnalysis(t1);

					}

				}

				System.out.println("");

			} else if (system.equalsIgnoreCase("l")) {
				System.out.println("歡迎使用「迴歸分析」功能，讓我先知道您的資料內容吧！\n\n" + "第一步：請輸入你的 y 資料集");

				ArrayList<Double> yData = new ArrayList<Double>();

				while (true) {
					System.out.print("（輸入 'q' 結束）：");
					String userInputnum = sc.next();

					if (userInputnum.equalsIgnoreCase("q")) {
						break; // 用戶輸入 'q'，跳出迴圈
					}

					try {
						// 嘗試將輸入轉換為 double
						double number = Double.parseDouble(userInputnum);
						yData.add(number);
					} catch (NumberFormatException e) {
						System.out.println("請輸入有效的數字或 'q' 來結束。");
					}
				}

				double[] yValues = new double[yData.size()];
				for (int i = 0; i < yData.size(); i++) {
					yValues[i] = yData.get(i);
				}

				System.out.println("\n第二步：請輸入你的 x 資料集");

				ArrayList<Double> xData = new ArrayList<Double>();

				while (true) {
					System.out.print("（輸入 'q' 結束）：");
					String userInputnum = sc.next();

					if (userInputnum.equalsIgnoreCase("q")) {
						break; // 用戶輸入 'q'，跳出迴圈
					}

					try {
						// 嘗試將輸入轉換為 double
						double number = Double.parseDouble(userInputnum);
						xData.add(number);
					} catch (NumberFormatException e) {
						System.out.println("請輸入有效的數字或 'q' 來結束。");
					}
				}

				double[] xValues = new double[xData.size()];
				for (int i = 0; i < xData.size(); i++) {
					xValues[i] = xData.get(i);
				}

				System.out.println("\n第三步：請依照敘述要求完成最後的資料內容");
				String yName, xName;
				double alpha, xVar;
				boolean varKnown = true;

				System.out.println("請輸入「y資料集名稱」和「x資料集名稱」（空格隔開）：");
				yName = sc.nextLine();
				xName = sc.nextLine();
				System.out
						.println("請輸入「信賴水準」(1-alpha)之浮點數字 (例: 95% 請輸入 0.05 )：\n" + "(如果不需要做關於線性迴歸的假設檢定，請將信賴水準值輸入“０”)");
				alpha = sc.nextDouble();
				System.out.println("請輸入「x母體變異數」之浮點數字：\n" + "(如果題目沒有給母體變異數，請輸入“０”，接下來的計算便會以 無母體變異數 的條件進行)");
				xVar = sc.nextDouble();
				if (xVar == 0) {
					varKnown = false;
				}

				LinearRegression simple = new LinearRegression(yName, yValues, xName, xValues, alpha, xVar);

				String question = ("\n請問您想對資料進行哪項計算與解釋？（請輸入1~5 或 q）\n" + "1. 線性方程式：ŷ = b1x + b0 \n"
						+ "2. 判定係數(Coefficient Determination) 和 Rsquard\n"
						+ "3. 假設檢定— Assumption About the Error term ε (test the slope)\n"
						+ "4. 信賴區間— Estimation (old point)\n" + "5. 信賴區間— Presiction (new point)\n" + "q. 結束");

				boolean test = true;

				while (test) {
					System.out.println(question);
					String testNum = sc.next();

					if (testNum.equals("1")) {
						System.out.println(simple.simpleLinearRegression());
						System.out.println("\n請問您需要對模型做簡單的預測嗎？（若有需要，請直接輸入一個浮點數）");
						if (sc.hasNextDouble()) {
							System.out.println(simple.predict(sc.nextDouble()));
						}
						System.out.println("\n請問需要為您解釋什麼是「線性迴歸」嗎？(y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							System.out.println(simple.linearRegressionExplain());
						}
						System.out.println("\n請問需要為您解釋什麼是「簡單線性迴歸」嗎？(y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							System.out.println(simple.simpleLinearRegressionExplain());
						}
					} else if (testNum.equals("2")) {
						System.out.println(simple.coefficientDetermination());
						System.out.println("\n請問需要為您解釋什麼是「相關係數」嗎？(y/n)");
						if (sc.next().equalsIgnoreCase("y")) {
							System.out.println(simple.coefficientDeterminationExplain());
						}
					} else if (testNum.equals("3")) {
						if (varKnown == true) {
							System.out.println(simple.testSlopeVarianceKnown());
							System.out.println("\n請問需要為您解釋本假設檢定的條件與計算過程嗎？(y/n)");
							if (sc.next().equalsIgnoreCase("y")) {
								System.out.println(simple.testSlopeVarianceKnownExplain());
							}
						} else {
							System.out.println(simple.testSlopeVarianceUnknown());
							System.out.println("\n請問需要為您解釋本假設檢定的條件與計算過程嗎？(y/n)");
							if (sc.next().equalsIgnoreCase("y")) {
								System.out.println(simple.testSlopeVarianceUnknownExplain());
							}
						}
					} else if (testNum.equals("4")) {
						System.out.println("\n請輸入「x資料集」中，原本就有的其中一筆資料（浮點數）:\n" + "（我們要利用原本的數據測試這個線性方程式的可信範圍。）");
						if (varKnown == true) {
							if (sc.hasNextDouble()) {
								System.out.println(simple.confidenceIntervalVarKnown(sc.nextDouble()));
							}
							System.out.println("\n請問需要為您解釋本估計的條件與推倒過程嗎？(y/n)");
							if (sc.next().equalsIgnoreCase("y")) {
								System.out.println(simple.confidenceIntervalVarKnownExplain());
							}
						} else {
							if (sc.hasNextDouble()) {
								System.out.println(simple.confidenceIntervalVarUnknown(sc.nextDouble()));
							}
							System.out.println("\n請問需要為您解釋本估計的條件與推倒過程嗎？(y/n)");
							if (sc.next().equalsIgnoreCase("y")) {
								System.out.println(simple.confidenceIntervalVarUnknownExplain());
							}
						}
					} else if (testNum.equals("5")) {
						System.out.println("\n請輸入一個「浮點數」，用來預測原本已知數據所計算出來的線性方程式之可信範圍。");
						if (sc.hasNextDouble()) {
							System.out.println(simple.predictionIntervalVarUnknown(sc.nextDouble()));
							System.out.println("\n請問需要為您解釋本估計的條件與推倒過程嗎？(y/n)");
							if (sc.next().equalsIgnoreCase("y")) {
								System.out.println(simple.predictionIntervalVarUnknownExplain());
							}
						}
					} else if (testNum.equalsIgnoreCase("q")) {
						test = false;
					}
				}

			} else if (system.equalsIgnoreCase("a")) {
				double[][] treatments = { {} };
				ArrayList<Double> data = new ArrayList<Double>();

				Anova test = new Anova(data, treatments, "name");
				System.out.println("歡迎使用「變異數分析」功能，請問有需要為您進行ANOVA之概念講解嗎？(輸入y/n)");

				if (sc.next().equals("y")) {
					test.explainANOVA();
				}
				System.out.println("第一個步驟為「決定實驗類型」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

				if (sc.next().equals("y")) {
					System.out.println("CRD\n");
					System.out.println(test.explainCRD());

					System.out.println("RBD\n");
					System.out.println(test.explainRBD());
				}
				System.out.println("\n題目是否有提供資料？(輸入y/n)");
				String userInput = sc.next();

				if (userInput.equals("y")) {

					System.out.println("請依序輸入資料(優先輸入要比較的 Treatments 值)");
					System.out.println("請輸入組數：");
					int groupsNum = sc.nextInt();

					// 宣告二維陣列儲存每組的數據
					double[][] groups = new double[groupsNum][];

					// 逐組輸入數據
					for (int i = 0; i < groupsNum; i++) {
						System.out.println("請輸入第 " + (i + 1) + " 組的數據點個數：");
						int groupSize = sc.nextInt();

						// 根據數據點個數初始化該組的數據陣列
						groups[i] = new double[groupSize];

						// 輸入每個數據點
						System.out.println("請輸入第 " + (i + 1) + " 組的數據：");
						for (int j = 0; j < groupSize; j++) {
							groups[i][j] = sc.nextDouble();
							data.add(groups[i][j]);
						}
					}

					test.setTreatments(groups);

					System.out.println("您想進行CRD還是RBD（a.CRD b.RBD，輸入a/b）");

					String testChoose = sc.next();

					if (testChoose.equals("a")) {

						System.out.println("請輸入「信心水準」(輸入alpha值)ex:0.05");
						test.setAlpha(sc.nextDouble());

						System.out.println("第二個步驟為「計算SSTR及SSE及SST」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction1();
						}
						System.out.println("SSTR:" + test.betweenTreatmentsSumOfSquaresofCRD());
						System.out.println("SST:" + test.totalSumOfSquares());
						System.out.println("SSE:" + test.errorSumOfSquaresofCRD());

						System.out.println("第三個步驟為「計算自由度」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction2();
						}
						System.out.println("DF of SSTR:" + test.calculateDFTRofCRD());
						int dfTotal = test.calculateDFTRofCRD() + test.calculateDFEofCRD();
						System.out.println("DF of SST:" + dfTotal);
						System.out.println("DF of SSE:" + test.calculateDFEofCRD());

						System.out.println("第四個步驟為「計算MSTR及MSE」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction3();
						}

						System.out.println("MSTR:" + test.calculateMSTRofCRD());
						System.out.println("MSE:" + test.calculateMSEofCRD());

						System.out.println("第五個步驟為「計算F_value」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction4();
						}

						System.out.println("F:" + test.calculateFValueofCRD());

						System.out.println("最後一個步驟為「製作ANOVA Table 並比較F_value」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction5();
						}
						test.printCRDTable();
						System.out.println(test.getAlpha());

						if (test.getAlpha() == 0.01)
							test.ftable001();
						else if (test.getAlpha() == 0.05)
							test.ftable005();
						else if (test.getAlpha() == 0.1)
							test.ftable01();
						else
							System.out.println("請輸入正確的alpha值");

						test.conclusion();

					} else if (testChoose.equals("b")) {

						System.out.println("請輸入「信心水準」(1-alpha)");
						test.setAlpha(sc.nextDouble());

						System.out.println("第二個步驟為「計算SSTR及SSE及SSBL及SST」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction6();
						}
						System.out.println("SSTR:" + test.betweenTreatmentsSumOfSquaresofRBD());
						System.out.println("SSBL:" + test.betweenBlockSumOfSquares());
						System.out.println("SST:" + test.totalSumOfSquares());
						System.out.println("SSE:" + test.errorSumOfSquaresofRBD());

						System.out.println("第三個步驟為「計算自由度」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction7();
						}
						System.out.println("DF of SSTR:" + test.calculateDFTRofRBD());
						System.out.println("DF of SSBL:" + test.calculateDFBLofRBD());
						System.out.println("DF of SSE:" + test.calculateDFEofRBD());
						int dfTotal = test.calculateDFTRofRBD() + test.calculateDFBLofRBD() + test.calculateDFEofRBD();
						System.out.println("DF of SST:" + dfTotal);

						System.out.println("第四個步驟為「計算MSTR及MSE及MSBL」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction8();
						}

						System.out.println("MSTR:" + test.calculateMSTRofRBD());
						System.out.println("MSBL:" + test.calculateMSBLofRBD());
						System.out.println("MSE:" + test.calculateMSEofRBD());

						System.out.println("第五個步驟為「計算F_value」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction4();
						}

						System.out.println("F:" + test.calculateFValueofRBD());

						System.out.println("最後一個步驟為「製作ANOVA Table 並比較F_value」，請問有需要為您進行該步驟之概念講解嗎？(輸入y/n)");

						if (sc.next().equals("y")) {
							test.instruction5();
						}
						test.printRBDTable();
						System.out.println();

						if (test.getAlpha() == 0.01)
							test.ftable001();
						else if (test.getAlpha() == 0.05)
							test.ftable005();
						else if (test.getAlpha() == 0.1)
							test.ftable01();
						else
							System.out.println("請輸入正確的alpha值");

						test.conclusion();
					}

				}

			} else if (system.equalsIgnoreCase("q")) {
				break;

			}

		}
		sc.close();
	}

}
