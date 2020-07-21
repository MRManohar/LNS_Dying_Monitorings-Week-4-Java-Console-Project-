package utility;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import Controls.CustomerControl;
import service.NumberValidation;

public class Bills {
	public void billsGenerating() throws Exception {
		int i=0;
		try {
			String orders = "C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\Orders.xls";
			FileInputStream fis = new FileInputStream(orders);
			
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			
			Iterator<Row> itr = sheet.iterator();
			System.out.println("Name of Customer");
			while(itr.hasNext()) 
			{
				Row row = itr.next();
				if(i>0) 
					System.out.println(i+". "+row.getCell(1));
				i++;
			}
			System.out.println("Please enter your choice to select the customer and generate bill");
			NumberValidation num =new NumberValidation();
			int cus = Integer.parseInt(num.enterNumber());
			Row row = sheet.getRow(cus);
			String name = row.getCell(1).getStringCellValue();
//			System.out.println(name+"'s Bill");
			CustomerControl customerControl = new CustomerControl();
			customerControl.cusBillGeneration(name);		
		}
		catch(Exception e) {
			System.out.println("Error in bill generating in admin controller");
		}
	}
}
