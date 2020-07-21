package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class CheckOrders {
	public void checkDailyOrders() throws Exception {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\Orders.xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Today's Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		Iterator<Row> itr = sheet.iterator();
//		System.out.println("Name of Customer\t"+"No of Varpulu\t"+"No of KGs of Sapuri\t"+"No of KGs of Dupin");
		while(itr.hasNext()) 
		{
			Row row = itr.next();
			System.out.println(row.getCell(0).getStringCellValue()+"\t\t\t"+row.getCell(1).getStringCellValue()+"\t\t\t"+row.getCell(2).getStringCellValue()+"\t\t\t"+row.getCell(3).getStringCellValue()+"\t\t\t"+row.getCell(4).getStringCellValue());
		}
		workbook.close();
	}
}
