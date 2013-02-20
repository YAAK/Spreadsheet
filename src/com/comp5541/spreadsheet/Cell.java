
/**
 * @function: Cell 
 * @author Huantao Liu 5998662, Hojabr Sattari 6435807, Santhosh Srinivasan 6583059 ,Yi Wang 9676449
 * @Modified by:
 * @date 2013.1.18
 */
package com.comp5541.spreadsheet;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class Cell {
	protected String nColumnName;
	protected String sCellname;
	protected final Double nDefaultValue = 0.0;
	protected String sFormula = null;
	protected Double nValue = null;
	boolean bValid = false;

	public Cell(String cellname){ 
		this.sCellname = cellname.trim();
		try{
			this.nColumnName = cellname.trim().substring(0, 1);	
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
	}


	
	public boolean hasFormula(){
		if(this.sFormula !=null){
			return true;
		}else{
			return false;
		}
	}

	public boolean hasValue(){
		if(this.nValue !=null){
			return true;
		}else{
			return false;
		}
	}

	public boolean hasComputedValue(){
		boolean ret=false;
		if(this.sFormula !=null && this.nValue == null){
			ret = false;
		}else if(this.sFormula !=null && this.nValue != null){
			ret = true;
		}else if(this.sFormula ==null && this.nValue != null){
			ret = true;
		}else if(this.sFormula ==null && this.nValue == null){
			ret = true;
		}
		return ret;
	}
	/**
	 * @function To parse the formula if the cell has formula, 
	 * and make it with no other variable than itself
	 * @param cells
	 * @return
	 */
	public String parseFormula(Cell cells[][]){
		//replace variable with computed value
		String selfname =this.sCellname;
		String str = this.sFormula; 
		boolean flag = true;
		int n=1;
		loop:
			while(flag){
				for(int i=0;i<10;i++){		
					for(int j=0;j<11;j++){ 			    //get each cell's name,and replace them with primitive value				   
						if(str.contains(selfname)){		//get the name of cell according to the formula's variable
							this.bValid = false;
							break loop;
						}
						if(str.contains(cells[i][j].sCellname)&&( !selfname.equals(cells[i][j].sCellname) )){
							if(cells[i][j].hasComputedValue()){//if has computedvalue, replace the variable with the value

								if(cells[i][j].nValue==null){//nothing in the cell, use default value
									str = str.replace(cells[i][j].sCellname, Double.toString(cells[i][j].nDefaultValue));
								}else{							    // the cell has nValue
									str = str.replace(cells[i][j].sCellname, Double.toString(cells[i][j].nValue));
								}							
							}else{// can only replace variable with another formula
								str = str.replace(cells[i][j].sCellname, "("+cells[i][j].sFormula.trim().substring(1)+")");
							}
							//System.out.println(str);
						}
						//check whether str has selfname
						if(str.contains(selfname)){
							this.bValid = false;
							break loop;
						}
					}
				}
				if(str.matches(".*[A-K].*")){//still have cellname in str, do while again
					if(n>50){
						flag = false;
						this.bValid = false;
					}
				}else{
					this.bValid = true;
					flag = false;
				}
				n++;
			}
		return str;

	}
	/**
	 * 
	 * @function: cell's computeValue, cleanFormula doesn't have any variables
	 */
	public boolean computeValue(String cleanFormula){
		boolean ret = false;
		if(!cleanFormula.trim().equals("")){
			ExpressionBuilder eb = new ExpressionBuilder(cleanFormula.substring(1));
			//eb.withVariable("x", 2).withVariable("y", 3);
			Calculable calc;
			try {
				calc = eb.build();
				this.nValue=calc.calculate();
				ret = true;
				return ret;
			} catch (UnknownFunctionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ret;
			} catch (UnparsableExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ret;
			}
			
		}else{
			return ret;
		}
	}


	public static String validateContent(String content){
		String ret ="error";
		String cont[] = content.trim().split("");
		if(cont[1].equals("=")&&cont.length>2){//check if it's a formula
			ret = "formula";
			for(int i=2;i<cont.length;i++){
				if(!cont[i].matches( "^[A-K0-9+\\-*/()]$" )){
					ret = "error";
					break;//it's not a formla,jump out of for loop
				}
			}
			if(content.trim().substring(1).matches(".*[A-K].*")){
				//check for formula with too large cell range
				if(!content.trim().substring(1).matches(".*[A-k]([1-9][+\\-*/()].*|[1-9]|10[+\\-*/()].*)")){
					ret = "error";
				}
			}
		
		}else if(cont[1].equals("=")&&cont.length<=2){
			ret = "error";
		}else{//check if it's an value
			ret = "value";
			for(int i=1;i<cont.length;i++){
				if(!cont[i].matches( "^[0-9.]$" )){
					ret = "error";
					break;
				}
			}
		}

		return ret;
	}
	
	
	public boolean setCellValue(String value){
		boolean ret;
		String result = validateContent(value);
		if(result.equals("value")){
			this.nValue = Double.parseDouble(value);
			this.bValid = true;
			ret = true;
		}else if(result.equals("formula")){
			this.sFormula = value;
			this.bValid = true;
			ret = true;
		}else{
			ret = false;
		}	
		return ret;
	}

	/**
	 * For user to view content of cell value, to be used for usecase 2
	 * @throws UnparsableExpressionException 
	 * @throws UnknownFunctionException 
	 */
	public String getCellValue(Cell cells[][]){
		String ret = "";
		if(this.sFormula != null) {// if this cell has formula,
			// first validate it
			if (!Cell.validateContent(this.sFormula).equals("error")) {
				//then parse it
				String result = this.parseFormula(cells);
				if (this.bValid) {
					this.computeValue(result);
					ret = this.nValue.toString();
				}else{
					ret = "InvalidFormula";
				}
				
			}else{
				ret="InvalidFormula";
			}
						
		}else if (this.nValue == null) {// if this cell is empty
			this.bValid = true;
			this.nValue = this.nDefaultValue;
			ret = this.nValue.toString();
		}else{
			ret = this.nValue.toString();
		}
		
		return ret;
	}
	

}
