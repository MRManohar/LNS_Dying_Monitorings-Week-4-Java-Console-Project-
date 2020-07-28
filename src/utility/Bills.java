package utility;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import controller.CustomerControl;
import service.NumberValidation;

public class Bills {
	public void billsGenerating() throws Exception {
		try {
			String orders = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls";
			FileInputStream fis = new FileInputStream(orders);
			
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			String pastName = sheet.getRow(1).getCell(1).getStringCellValue();
			String nameData = pastName;
			Iterator<Row> itr = sheet.iterator();
			System.out.println("Name of Customer");
			while(itr.hasNext()) 
			{
				Row row = itr.next();
				if(row.getRowNum()!=0) {
					if(!(pastName.equals(row.getCell(1).getStringCellValue()))) {
						pastName = row.getCell(0).getStringCellValue();
						nameData = nameData.concat(","+row.getCell(1).getStringCellValue());
					}
				}
			}
			System.out.println("Please enter your choice to select the customer and generate bill");
			String[] nameArr = toStringArr(nameData);
			for(int i=0;i<nameArr.length;i++)
				System.out.println((i+1)+" "+nameArr[i]);
			NumberValidation numberValidation = new NumberValidation();
			int n = Integer.parseInt(numberValidation.enterNumber());
			String name = nameArr[n-1];
			System.out.println("Bill name = "+name);
			CustomerControl customerControl = new CustomerControl();
			customerControl.cusBillGeneration(name,0);		
		}
		catch(Exception e) {
			System.out.println("Error in bill generating in admin controller");
		}
	}
	private String[] toStringArr(String string) {
		String[] dataArr = string.split(",");
		int c=0,k=0;
		for(int i=0;i<dataArr.length;i++){
			for(int j=i+1;j<dataArr.length;j++){
				if(dataArr[i].equals(dataArr[j])) {
					dataArr[j] = "#";
				}
			}
		}
		for(int i=0;i<dataArr.length;i++)
			if(dataArr[i].equals("#"))
				c++;
		String[] returnArr = new String[dataArr.length-c];
		for(int i=0;i<dataArr.length;i++) {
			if (!(dataArr[i].equals("#"))) {
				returnArr[k++] = dataArr[i];
			}
		}
		return returnArr;
	}
}