package com.inf1.app.dto;

import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GlobalStep2DTO {

	private LocalDate date;
	private CasLineaire casLineaire;
	private CasSIR casSIR;
	private CasSVIR casSVIR;
	private CasMachineLearning casMachineLearning;
	private VaccinLineaire vaccinLineaire;
	private VaccinLog vaccinLog;
	private VaccinMachineLearning vaccinMachineLearning;
	
	public GlobalStep2DTO() {
		this.date = LocalDate.now();
		casLineaire = new CasLineaire();
		casSIR = new CasSIR();
		casMachineLearning = new CasMachineLearning();
		casSVIR = new CasSVIR();
		casMachineLearning = new CasMachineLearning();
		vaccinLineaire = new VaccinLineaire();
		vaccinLog = new VaccinLog();
		vaccinMachineLearning = new VaccinMachineLearning();
	}
	
	private class CasLineaire {
		private Map<LocalDate, Integer> values;
		private int a, b;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public Map<LocalDate, Integer> getValues() {
			return values;
		}
	}
	
	private class CasSIR {
		private Map<LocalDate, Integer> values;
		private int S, I, R, R0 ;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}

		public int getS() {
			return S;
		}

		public void setS(int s) {
			S = s;
		}

		public int getI() {
			return I;
		}

		public void setI(int i) {
			I = i;
		}

		public int getR() {
			return R;
		}

		public void setR(int r) {
			R = r;
		}

		public int getR0() {
			return R0;
		}

		public void setR0(int r0) {
			R0 = r0;
		}

		public Map<LocalDate, Integer> getValues() {
			return values;
		}
		
	}
	
	private class CasSVIR {
		private Map<LocalDate, Integer> values;
		private int S, I, R, V, R0 ;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}

		public int getS() {
			return S;
		}

		public void setS(int s) {
			S = s;
		}

		public int getI() {
			return I;
		}

		public void setI(int i) {
			I = i;
		}

		public int getR() {
			return R;
		}

		public void setR(int r) {
			R = r;
		}

		public int getR0() {
			return R0;
		}

		public void setR0(int r0) {
			R0 = r0;
		}

		public Map<LocalDate, Integer> getValues() {
			return values;
		}

		public int getV() {
			return V;
		}

		public void setV(int v) {
			V = v;
		}
	}
	
	private class CasMachineLearning {
		private Map<LocalDate, Integer> values;
		private Map<String, Integer> variablesExplicatives;
		private Map<String, Integer> coefficients;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}
		
		public void putVariablesExplicatives(String s, int value) {
			variablesExplicatives.put(s, value);
		}
		
		public void putCoefficients(String s, int value) {
			coefficients.put(s, value);
		}

		public Map<LocalDate, Integer> getValues() {
			return values;
		}

		public Map<String, Integer> getVariablesExplicatives() {
			return variablesExplicatives;
		}

		public Map<String, Integer> getCoefficients() {
			return coefficients;
		}
	}
	
	private class VaccinLineaire {
		private Map<LocalDate, Integer> values;
		private int a, b;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public Map<LocalDate, Integer> getValues() {
			return values;
		}
	}
	
	private class VaccinLog {
		private Map<LocalDate, Integer> values;
		private int A, B;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}
		public int getA() {
			return A;
		}
		public void setA(int A) {
			this.A = A;
		}
		public int getB() {
			return B;
		}
		public void setB(int B) {
			this.B = B;
		}
		public Map<LocalDate, Integer> getValues() {
			return values;
		}
	}
	
	private class VaccinMachineLearning {
		private Map<LocalDate, Integer> values;
		private Map<String, Integer> variablesExplicatives;
		private Map<String, Integer> coefficients;
		
		public void putValue(LocalDate date, int value) {
			values.put(date, value);
		}
		
		public void putVariablesExplicatives(String s, int value) {
			variablesExplicatives.put(s, value);
		}
		
		public void putCoefficients(String s, int value) {
			coefficients.put(s, value);
		}

		public Map<LocalDate, Integer> getValues() {
			return values;
		}

		public Map<String, Integer> getVariablesExplicatives() {
			return variablesExplicatives;
		}

		public Map<String, Integer> getCoefficients() {
			return coefficients;
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public CasLineaire getCasLineaire() {
		return casLineaire;
	}

	public CasSIR getCasSIR() {
		return casSIR;
	}

	public CasSVIR getCasSVIR() {
		return casSVIR;
	}

	public CasMachineLearning getCasMachineLearning() {
		return casMachineLearning;
	}

	public VaccinLineaire getVaccinLineaire() {
		return vaccinLineaire;
	}

	public VaccinLog getVaccinLog() {
		return vaccinLog;
	}

	public VaccinMachineLearning getVaccinMachineLearning() {
		return vaccinMachineLearning;
	}
	

}
