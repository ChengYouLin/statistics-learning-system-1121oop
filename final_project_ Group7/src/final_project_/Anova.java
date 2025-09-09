package final_project_;

import java.util.ArrayList;

public class Anova extends DescriptiveStat {
	private double[][] blocks;
	private double[][] treatments;
	private double a;
	private ArrayList<Double> data;

	// 此處省略與 ANOVA 類別相同的構造方法、靜態方法等

	/**
	 * 使用多組數據構造 CombinedANOVA 對象,用於隨機區組設計。
	 *
	 * @param treatments 代表不同處理的數據組
	 * @param blocks     代表區組的數據組
	 * @param name       數據集的名稱
	 * @param data
	 */
	public Anova(ArrayList<Double> data, double[][] treatments, String name) {
		super(data, flatten(treatments), name);
		this.treatments = treatments;
		this.data = data;
		for (double[] group : treatments) {
			for (double value : group) {
				this.data.add(value);
			}
		}
	}

	public double[][] generateBlocks(double[][] treatments) {

		int numBlocks = treatments[0].length;
		int numSamples = treatments.length;

		double[][] blocks = new double[numBlocks][numSamples];

		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < numSamples; j++) {
				blocks[i][j] = treatments[j][i];
			}
		}
		return blocks;
	}

	public ArrayList<Double> getData1() {
		return data;
	}

	public double getAlpha() {
		return a;
	}

	public void setAlpha(double alpha) {
		this.a = alpha;
	}

	public double[][] gettreatments() {
		return treatments;
	}

	public void setTreatments(double[][] groups) {
		this.treatments = groups;
	}

	public double[][] getBlocks() {
		return blocks;
	}

	public static double[] flatten(double[][] arrays) {

		int totalLength = 0;

		for (double[] array : arrays) {
			totalLength += array.length;
		}

		double[] flattened = new double[totalLength];

		int index = 0;
		for (double[] array : arrays) {
			for (double value : array) {
				flattened[index] = value;
				index++;
			}
		}

		return flattened;
	}

//	算total的mean和各blocks的mean

	public double overallMean() {
		return mean();
	}

//	計算SST

	public double totalSumOfSquares() {
		double mean = mean();
		double sum = 0;
		for (double num : this.getData1()) {
			sum += Math.pow(num - mean, 2);
		}
		return Math.round(sum * 10.0) / 10.0;
	}

//	計算SSTR

	public double betweenTreatmentsSumOfSquaresofCRD() {
		double overallMean = mean();
		double sum = 0;

		for (double[] group : treatments) {
			double groupMean = mean(group); // 使用新的mean方法计算每个组的平均值

			sum += group.length * Math.pow(groupMean - overallMean, 2);
		}

		return Math.round(sum * 10.0) / 10.0;
	}

	// 新增一个用于计算数组平均值的辅助方法
	private double mean(double[] array) {
		double sum = 0;
		for (double v : array) {
			sum += v;
		}
		return array.length > 0 ? sum / array.length : 0;
	}

	public double betweenTreatmentsSumOfSquaresofRBD() {
		double overallMean = mean();
		double sum = 0;

		for (double[] group : treatments) {
			double groupMean = mean(group); // 同样使用新的mean方法

			sum += group.length * Math.pow(groupMean - overallMean, 2);
		}

		return Math.round(sum * 10.0) / 10.0;
	}

//	計算SSBL

	public double betweenBlockSumOfSquares() {
		double overallMean = overallMean();
		double sum = 0;
		double[][] blocks = generateBlocks(treatments);

		for (double[] block : blocks) {
			double blockMean = mean(block); // 使用已有的 mean 方法计算块的平均值
			sum += block.length * Math.pow(blockMean - overallMean, 2);
		}

		return Math.round(sum * 10.0) / 10.0;

	}

//  計算SSE

	public double errorSumOfSquaresofRBD() {
		return totalSumOfSquares() - betweenTreatmentsSumOfSquaresofRBD() - betweenBlockSumOfSquares();
	}

	public double errorSumOfSquaresofCRD() {
		return totalSumOfSquares() - betweenTreatmentsSumOfSquaresofCRD();
	}

//	計算自由度(Degree Freedom)

	public int calculateDFTRofCRD() {
		return treatments.length - 1;
	}

	public int calculateDFEofCRD() {
		int totalObservations = 0;
		for (double[] group : treatments) {
			totalObservations += group.length;
		}
		int numGroups = treatments.length;
		return totalObservations - numGroups;
	}

	public int calculateDFBLofRBD() {
		double[][] blocks = generateBlocks(treatments);
		return blocks.length - 1;
	}

	public int calculateDFTRofRBD() {
		return treatments.length - 1;
	}

	public int calculateDFEofRBD() {
		return calculateDFTRofRBD() * calculateDFBLofRBD();
	}

	public int calculateDFTotalofRBD() {
		return (treatments.length * blocks.length) - 1;
	}

//	計算MS (CRD)

	public double calculateMSTRofCRD() {
		double sstr = betweenTreatmentsSumOfSquaresofCRD();
		int dfBetween = calculateDFTRofCRD();
		return Math.round((sstr / dfBetween) * 10.0) / 10.0;
	}

	public double calculateMSEofCRD() {
		double sse = errorSumOfSquaresofCRD();
		int dfWithin = calculateDFEofCRD();
		return Math.round((sse / dfWithin) * 10.0) / 10.0;
	}

//	計算MS (RBD)

	public double calculateMSBLofRBD() {
		double ssb = betweenBlockSumOfSquares();
		int dfBetween = calculateDFBLofRBD();
		return Math.round((ssb / dfBetween) * 10.0) / 10.0;
	}

	public double calculateMSTRofRBD() {
		double ssw = betweenTreatmentsSumOfSquaresofRBD();
		int dfWithin = calculateDFTRofRBD();
		return Math.round((ssw / dfWithin) * 10.0) / 10.0;
	}

	public double calculateMSEofRBD() {
		double ssw = errorSumOfSquaresofRBD();
		int dfWithin = calculateDFEofRBD();
		return Math.round((ssw / dfWithin) * 10.0) / 10.0;
	}

//	計算F_value

	public double calculateFValueofCRD() {
		double mstr = calculateMSTRofCRD();
		double mse = calculateMSEofCRD();
		return Math.round((mstr / mse) * 1000.0) / 1000.0;
	}

	public double calculateFValueofRBD() {
		double mstr = calculateMSTRofRBD();
		double mse = calculateMSEofRBD();
		return Math.round((mstr / mse) * 1000.0) / 1000.0;
	}

	public void printCRDTable() {

		double sstr = betweenTreatmentsSumOfSquaresofCRD();
		int dftr = calculateDFTRofCRD();
		double mstr = calculateMSTRofCRD();
		double f = calculateFValueofCRD();

		double sse = errorSumOfSquaresofCRD();
		int dfe = calculateDFEofCRD();
		double mse = calculateMSEofCRD();

		double sst = totalSumOfSquares();
		int dft = dftr + dfe;

		int firstGroupSize = treatments[0].length;
		for (double[] group : treatments) {
			if (group.length != firstGroupSize) {
				System.out.println("樣本數不同，無法執行 ANOVA。");
				return;
			}
		}

		System.out.printf("            Anova Table%n");
		System.out.printf("           -------------%n");
		System.out.printf("Source    |   SS    | df |   MS  |  F%n");
		System.out.printf("Treatment | %4.2f | %2d | %.1f | %.2f%n", sstr, dftr, mstr, f);
		System.out.printf("Error     | %4.2f | %d | %.1f | %n", sse, dfe, mse);
		System.out.printf("Total     | %4.2f | %d | %n", sst, dft);

	}

	public void printRBDTable() {

		double sstr = betweenTreatmentsSumOfSquaresofRBD();
		int dftr = calculateDFTRofRBD();
		double mstr = calculateMSTRofRBD();
		double f = calculateFValueofRBD();

		double ssbl = betweenBlockSumOfSquares();
		int dfbl = calculateDFBLofRBD();
		double msbl = calculateMSBLofRBD();

		double sse = errorSumOfSquaresofRBD();
		int dfe = calculateDFEofRBD();
		double mse = calculateMSEofRBD();

		double sst = totalSumOfSquares();
		int dft = dftr + dfbl + dfe;

		int firstGroupSize = treatments[0].length;
		for (double[] group : treatments) {
			if (group.length != firstGroupSize) {
				System.out.println("樣本數不同，無法執行 ANOVA。");
				return;
			}
		}

		System.out.printf("           Anova Table(RBD)%n");
		System.out.printf("          ------------------%n");
		System.out.printf("Source    |   SS   | df |  MS  |  F%n");
		System.out.printf("Treatment |  %4.2f | %2d | %.1f | %.2f%n", sstr, dftr, mstr, f);
		System.out.printf("Blocks    | %4.2f | %2d | %.1f | %n", ssbl, dfbl, msbl);
		System.out.printf("Error     |  %4.2f | %2d |  %.1f | %n", sse, dfe, mse);
		System.out.printf("Total     | %4.2f | %d | %n", sst, dft);

	}

//	解釋

	public String explainANOVA() {
		String result = "";

		result = "ANOVA 是變異數分析（Analysis of Variance）的縮寫，是一種統計方法，用於分析樣本(Sample)中不同組別之間母體(Body)平均值的差異。"
				+ "\n藉由假設檢定判斷組間和平均值的平方和(SSTR)與觀察值和平均值的平方和(SSE)" + "\n將兩者相除比較F_value的大小檢定推測母體的平均值是否相同。"
				+ "\n通常都假設H0 = mean相同, H1 = reject H0";

		return result;
	}

	public String explainRBD() {
		String result = "";

		result = "Randomized Block Designs(RBD) 是Anova的其中一種方法" + "\n因為其蒐集資料過程為每一組別(blocks)取樣，所以組間的變異數要再多加考慮blocks的部分"
				+ "\n藉由假設檢定判斷組間和平均值的平方和(SSTR)與觀察值和平均值的平方和(SSE)" + "\n將兩者相除比較F_value的大小檢定推測母體的平均值是否相同。"
				+ "\n通常都假設H0 = mean相同, H1 = reject H0";
		;

		return result;
	}

	public String explainCRD() {
		String result = "";

		result = "Completely randomized design(CRD) 是Anova的其中一種方法" + "\n其蒐集資料過程為隨機取樣"
				+ "\n藉由假設檢定判斷組間和平均值的平方和(SSTR)與觀察值和平均值的平方和(SSE)" + "\n將兩者相除比較F_value的大小檢定推測母體的平均值是否相同。"
				+ "\n通常都假設H0 = mean相同, H1 = reject H0";
		;

		return result;
	}

	public void ftable01() {
		System.out.println("\na=0.1	df1=1	2	3	4	5   	6	7	8	9	10	12	15	20	24	30	40	60	120	");
		System.out.println(
				"df1=1   39.8634	49.5000	53.5932	55.8329	57.2400	58.2044	58.9059	59.4389	59.8575	60.1949	60.7052	61.2203	61.7402	62.0020	62.2649	62.5290	62.7942	63.0606 ");
		System.out.println(
				"df2=2	8.52632	9.00000	9.16179	9.24342	9.29263	9.32553	9.34908	9.36677	9.38054	9.39157	9.40813	9.42471	9.44131	9.44962	9.45793	9.46624	9.47456	9.48289	");
		System.out.println(
				"df2=3	5.53832	5.46238	5.39077	5.34264	5.30916	5.28473	5.26619	5.25167	5.24000	5.23041	5.21562	5.20031	5.18448	5.17636	5.16811	5.15972	5.15119	5.14251	");
		System.out.println(
				"df2=4	4.54477	4.32456	4.19086	4.10725	4.05058	4.00975	3.97897	3.95494	3.93567	3.91988	3.89553	3.87036	3.84434	3.83099	3.81742	3.80361	3.78957	3.77527	");
		System.out.println(
				"df2=5	4.06042	3.77972	3.61948	3.52020	3.45298	3.40451	3.36790	3.33928	3.31628	3.29740	3.26824	3.23801	3.20665	3.19052	3.17408	3.15732	3.14023	3.12279	");
		System.out.println(
				"df2=6	3.77595	3.46330	3.28876	3.18076	3.10751	3.05455	3.01446	2.98304	2.95774	2.93693	2.90472	2.87122	2.83634	2.81834	2.79996	2.78117	2.76195	2.74229	");
		System.out.println(
				"df2=7	3.58943	3.25744	3.07407	2.96053	2.88334	2.82739	2.78493	2.75158	2.72468	2.70251	2.66811	2.63223	2.59473	2.57533	2.55546	2.53510	2.51422	2.49279	");
		System.out.println(
				"df2=8	3.45792	3.11312	2.92380	2.80643	2.72645	2.66833	2.62413	2.58935	2.56124	2.53804	2.50196	2.46422	2.42464	2.40410	2.38302	2.36136	2.33910	2.31618	");
		System.out.println(
				"df2=9	3.36030	3.00645	2.81286	2.69268	2.61061	2.55086	2.50531	2.46941	2.44034	2.41632	2.37888	2.33962	2.29832	2.27683	2.25472	2.23196	2.20849	2.18427	");
		System.out.println(
				"df2=10	3.28502	2.92447	2.72767	2.60534	2.52164	2.46058	2.41397	2.37715	2.34731	2.32260	2.28405	2.24351	2.20074	2.17843	2.15543	2.13169	2.10716	2.08176	");
		System.out.println(
				"df2=11	3.22520	2.85951	2.66023	2.53619	2.45118	2.38907	2.34157	2.30400	2.27350	2.24823	2.20873	2.16709	2.12305	2.10001	2.07621	2.05161	2.02612	1.99965	");
		System.out.println(
				"df2=12	3.17655	2.80680	2.60552	2.48010	2.39402	2.33102	2.28278	2.24457	2.21352	2.18776	2.14744	2.10485	2.05968	2.03599	2.01149	1.98610	1.95973	1.93228	");
		System.out.println(
				"df2=13	3.13621	2.76317	2.56027	2.43371	2.34672	2.28298	2.23410	2.19535	2.16382	2.13763	2.09659	2.05316	2.00698	1.98272	1.95757	1.93147	1.90429	1.87591	");
		System.out.println(
				"df2=14	3.10221	2.72647	2.52222	2.39469	2.30694	2.24256	2.19313	2.15390	2.12195	2.09540	2.05371	2.00953	1.96245	1.93766	1.91193	1.88516	1.85723	1.82800	");
		System.out.println(
				"df2=15	3.07319	2.69517	2.48979	2.36143	2.27302	2.20808	2.15818	2.11853	2.08621	2.05932	2.01707	1.97222	1.92431	1.89904	1.87277	1.84539	1.81676	1.78672	");
		System.out.println(
				"df2=16	3.04811	2.66817	2.46181	2.33274	2.24376	2.17833	2.12800	2.08798	2.05533	2.02815	1.98539	1.93992	1.89127	1.86556	1.83879	1.81084	1.78156	1.75075	");
		System.out.println(
				"df2=17	3.02623	2.64464	2.43743	2.30775	2.21825	2.15239	2.10169	2.06134	2.02839	2.00094	1.95772	1.91169	1.86236	1.83624	1.80901	1.78053	1.75063	1.71909	");
		System.out.println(
				"df2=18	3.00698	2.62395	2.41601	2.28577	2.19583	2.12958	2.07854	2.03789	2.00467	1.97698	1.93334	1.88681	1.83685	1.81035	1.78269	1.75371	1.72322	1.69099	");
		System.out.println(
				"df2=19	2.98990	2.60561	2.39702	2.26630	2.17596	2.10936	2.05802	2.01710	1.98364	1.95573	1.91170	1.86471	1.81416	1.78731	1.75924	1.72979	1.69876	1.66587	");
		System.out.println(
				"df2=20	2.97465	2.58925	2.38009	2.24893	2.15823	2.09132	2.03970	1.99853	1.96485	1.93674	1.89236	1.84494	1.79384	1.76667	1.73822	1.70833	1.67678	1.64326	");
	}

	public void ftable005() {
		System.out
				.println("\na=0.05	df1=1	2	3	4	5   	6	7	8	9	10	12	15	20	24	30	40	60	120	");
		System.out.println(
				"df1=1   161.44	199.50	215.70	224.58	230.16	233.98	236.76	238.88	240.54	241.88	243.90	245.94	248.01	249.05	250.09	251.14	252.19	253.25	");
		System.out.println(
				"df2=2	18.512	19.000	19.164	19.246	19.296	19.329	19.353	19.371	19.384	19.395	19.412	19.429	19.445	19.454	19.462	19.470	19.479	19.487	");
		System.out.println(
				"df2=3	10.128	9.5521	9.2766	9.1172	9.0135	8.9406	8.8867	8.8452	8.8123	8.7855	8.7446	8.7029	8.6602	8.6385	8.6166	8.5944	8.5720	8.5494	");
		System.out.println(
				"df2=4	7.7086	6.9443	6.5914	6.3882	6.2561	6.1631	6.0942	6.0410	5.9988	5.9644	5.9117	5.8578	5.8025	5.7744	5.7459	5.7170	5.6877	5.6581	");
		System.out.println(
				"df2=5	6.6079	5.7861	5.4095	5.1922	5.0503	4.9503	4.8759	4.8183	4.7725	4.7351	4.6777	4.6188	4.5581	4.5272	4.4957	4.4638	4.4314	4.3985	");
		System.out.println(
				"df2=6	5.9874	5.1433	4.7571	4.5337	4.3874	4.2839	4.2067	4.1468	4.0990	4.0600	3.9999	3.9381	3.8742	3.8415	3.8082	3.7743	3.7398	3.7047	");
		System.out.println(
				"df2=7	5.5914	4.7374	4.3468	4.1203	3.9715	3.8660	3.7870	3.7257	3.6767	3.6365	3.5747	3.5107	3.4445	3.4105	3.3758	3.3404	3.3043	3.2674	");
		System.out.println(
				"df2=8	5.3177	4.4590	4.0662	3.8379	3.6875	3.5806	3.5005	3.4381	3.3881	3.3472	3.2839	3.2184	3.1503	3.1152	3.0794	3.0428	3.0053	2.9669	");
		System.out.println(
				"df2=9	5.1174	4.2565	3.8625	3.6331	3.4817	3.3738	3.2927	3.2296	3.1789	3.1373	3.0729	3.0061	2.9365	2.9005	2.8637	2.8259	2.7872	2.7475	");
		System.out.println(
				"df2=10	4.9646	4.1028	3.7083	3.4780	3.3258	3.2172	3.1355	3.0717	3.0204	2.9782	2.9130	2.8450	2.7740	2.7372	2.6996	2.6609	2.6211	2.5801	");
		System.out.println(
				"df2=11	4.8443	3.9823	3.5874	3.3567	3.2039	3.0946	3.0123	2.9480	2.8962	2.8536	2.7876	2.7186	2.6464	2.6090	2.5705	2.5309	2.4901	2.4480	");
		System.out.println(
				"df2=12	4.7472	3.8853	3.4903	3.2592	3.1059	2.9961	2.9134	2.8486	2.7964	2.7534	2.6866	2.6169	2.5436	2.5055	2.4663	2.4259	2.3842	2.3410	");
		System.out.println(
				"df2=13	4.6672	3.8056	3.4105	3.1791	3.0254	2.9153	2.8321	2.7669	2.7144	2.6710	2.6037	2.5331	2.4589	2.4202	2.3803	2.3392	2.2966	2.2524	");
		System.out.println(
				"df2=14	4.6001	3.7389	3.3439	3.1122	2.9582	2.8477	2.7642	2.6987	2.6458	2.6022	2.5342	2.4630	2.3879	2.3487	2.3082	2.2664	2.2229	2.1778	");
		System.out.println(
				"df2=15	4.5431	3.6823	3.2874	3.0556	2.9013	2.7905	2.7066	2.6408	2.5876	2.5437	2.4753	2.4034	2.3275	2.2878	2.2468	2.2043	2.1601	2.1141	");
		System.out.println(
				"df2=16	4.4940	3.6337	3.2389	3.0069	2.8524	2.7413	2.6572	2.5911	2.5377	2.4935	2.4247	2.3522	2.2756	2.2354	2.1938	2.1507	2.1058	2.0589	");
		System.out.println(
				"df2=17	4.4513	3.5915	3.1968	2.9647	2.8100	2.6987	2.6143	2.5480	2.4943	2.4499	2.3807	2.3077	2.2304	2.1898	2.1477	2.1040	2.0584	2.0107	");
		System.out.println(
				"df2=18	4.4139	3.5546	3.1599	2.9277	2.7729	2.6613	2.5767	2.5102	2.4563	2.4117	2.3421	2.2686	2.1906	2.1497	2.1071	2.0629	2.0166	1.968	");
		System.out.println(
				"df2=19	4.3807	3.5219	3.1274	2.8951	2.7401	2.6283	2.5435	2.4768	2.4227	2.3779	2.3080	2.2341	2.1555	2.1141	2.0712	2.0264	1.9795	1.9302	");
		System.out.println(
				"df2=20	4.3512	3.4928	3.0984	2.8661	2.7109	2.5990	2.5140	2.4471	2.3928	2.3479	2.2776	2.2033	2.1242	2.0825	2.0391	1.9938	1.9464	1.8963	");
	}

	public void ftable001() {
		System.out
				.println("\na=0.01	df1=1	2	3	4	5   	6	7	8	9	10	12	15	20	24	30	40	60	120	");
		System.out.println(
				"df1=1   4052.1	4999.5	5403.3	5624.5	5763.6	5858.9	5928.3	5981.0	6022.4	6055.8	6106.3	6157.2	6208.7	6234.6	6260.6	6286.7	6313.0	6339.3 ");
		System.out.println(
				"df2=2	98.503	99.000	99.166	99.249	99.299	99.333	99.356	99.374	99.388	99.399	99.416	99.433	99.449	99.458	99.466	99.474	99.482	99.491 ");
		System.out.println(
				"df2=3	34.116	30.817	29.457	28.710	28.237	27.911	27.672	27.489	27.345	27.229	27.052	26.872	26.690	26.598	26.505	26.411	26.316	26.221 ");
		System.out.println(
				"df2=4	21.198	18.000	16.694	15.977	15.522	15.207	14.976	14.799	14.659	14.546	14.374	14.198	14.020	13.929	13.838	13.745	13.652	13.558 ");
		System.out.println(
				"df2=5	16.258	13.274	12.060	11.392	10.967	10.672	10.456	10.289	10.158	10.051	9.888	9.722	9.553	9.466	9.379	9.291	9.202	9.112	");
		System.out.println(
				"df2=6	13.745	10.925	9.780	9.148	8.746	8.466	8.260	8.102	7.976	7.874	7.718	7.559	7.396	7.313	7.229	7.143	7.057	6.969	");
		System.out.println(
				"df2=7	12.246	9.547	8.451	7.847	7.460	7.191	6.993	6.840	6.719	6.620	6.469	6.314	6.155	6.074	5.992	5.908	5.824	5.737	");
		System.out.println(
				"df2=8	11.259	8.649	7.591	7.006	6.632	6.371	6.178	6.029	5.911	5.814	5.667	5.515	5.359	5.279	5.198	5.116	5.032	4.946	");
		System.out.println(
				"df2=9	10.561	8.022	6.992	6.422	6.057	5.802	5.613	5.467	5.351	5.257	5.111	4.962	4.808	4.729	4.649	4.567	4.483	4.398	");
		System.out.println(
				"df2=10	10.044	7.559	6.552	5.994	5.636	5.386	5.200	5.057	4.942	4.849	4.706	4.558	4.405	4.327	4.247	4.165	4.082	3.996	");
		System.out.println(
				"df2=11	9.646	7.206	6.217	5.668	5.316	5.069	4.886	4.744	4.632	4.539	4.397	4.251	4.099	4.021	3.941	3.860	3.776	3.690	");
		System.out.println(
				"df2=12	9.330	6.927	5.953	5.412	5.064	4.821	4.640	4.499	4.388	4.296	4.155	4.010	3.858	3.780	3.701	3.619	3.535	3.449	");
		System.out.println(
				"df2=13	9.074	6.701	5.739	5.205	4.862	4.620	4.441	4.302	4.191	4.100	3.960	3.815	3.665	3.587	3.507	3.425	3.341	3.255	");
		System.out.println(
				"df2=14	8.862	6.515	5.564	5.035	4.695	4.456	4.278	4.140	4.030	3.939	3.800	3.656	3.505	3.427	3.348	3.266	3.181	3.094	");
		System.out.println(
				"df2=15	8.683	6.359	5.417	4.893	4.556	4.318	4.142	4.004	3.895	3.805	3.666	3.522	3.372	3.294	3.214	3.132	3.047	2.959	");
		System.out.println(
				"df2=16	8.531	6.226	5.292	4.773	4.437	4.202	4.026	3.890	3.780	3.691	3.553	3.409	3.259	3.181	3.101	3.018	2.933	2.845	");
		System.out.println(
				"df2=17	8.400	6.112	5.185	4.669	4.336	4.102	3.927	3.791	3.682	3.593	3.455	3.312	3.162	3.084	3.003	2.920	2.835	2.746	");
		System.out.println(
				"df2=18	8.285	6.013	5.092	4.579	4.248	4.015	3.841	3.705	3.597	3.508	3.371	3.227	3.077	2.999	2.919	2.835	2.749	2.660	");
		System.out.println(
				"df2=19	8.185	5.926	5.010	4.500	4.171	3.939	3.765	3.631	3.523	3.434	3.297	3.153	3.003	2.925	2.844	2.761	2.674	2.584	");
		System.out.println(
				"df2=20	8.096	5.849	4.938	4.431	4.103	3.871	3.699	3.564	3.457	3.368	3.231	3.088	2.938	2.859	2.778	2.695	2.608	2.517	");
	}

	public void instruction1() {
		System.out.println("計算各組間(Treatments)與各組的平均數間的變異數(SSTR)\n以及個數字間(Error)與樣本平均數的變異數(SSE)");
	}

	public void instruction2() {
		System.out.println("計算每組間的自由度n-1\n以及誤差值的自由度");
	}

	public void instruction3() {
		System.out.println("將變異數與自由度相除，得到平均變異數(mean square)\n通常只需計算需要的MSTR以及MSE");
	}

	public void instruction4() {
		System.out.println("將MSTR以及MSE相除，可以求出F_value");

	}

	public void instruction5() {
		System.out.println("製作Anova table 將上述資料擺進去，並且查表比較F_value");

	}

	public void conclusion() {
		System.out.println("如果F_value值比較後，符合H0代表各組間的母體平均數推測是一樣的，反之亦然");
	}

	public void instruction6() {
		System.out.println(
				"計算各組間(Treatments)與各組的平均數間的變異數(SSTR)\n以及個數字間(Error)與樣本平均數的變異數(SSE)\n以及各實驗組(Blocks)與各組間平均數間的變異數(SSBL)");
	}

	public void instruction7() {
		System.out.println("計算每組間的自由度n-1\n以及誤差值的自由度");

	}

	public void instruction8() {
		System.out.println("將變異數與自由度相除，得到平均變異數(mean square)\n通常只需計算需要的MSTR以及MSE以及MSBL");

	}

}
