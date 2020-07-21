package utility;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import Controls.AdminControl;
import model.Worker;
import service.AddressValidation;
import service.DataWorker;
import service.MobileValidation;
import service.NameValidation;
import service.NumberValidation;

public class WorkersPerticulars {
	public void workers() throws Exception {
		System.out.println("Please enter your choice");
		System.out.println("1. Add worker\n2. Accounts of Worker\n3. Available Workers\n4. Remove Worker");
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		switch(choice) {
		case 1:
			addWorker();
			break;
		case 2:
			accountsOfWorker();
			break;
		case 3:
			worksInfo();
			break;
		case 4:
			removeWorker();
			break;
		default:
			System.out.println("Please enter valid choice");
			workers();
		}
	}

	private void worksInfo() throws Exception {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\Workers.xls");
		FileInputStream fis = new FileInputStream(file);
		
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			Row row = itr.next();
			if(row.getRowNum()!=0)
				System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue()+"\t\t"+row.getCell(5).getStringCellValue());
			else
				System.out.println(row.getCell(0).getStringCellValue()+"\t\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t"+row.getCell(4).getStringCellValue()+"\t\t"+row.getCell(5).getStringCellValue());
//			checkUserName = row.getCell(1).getStringCellValue();
//			checkPassword = row.getCell(3).getStringCellValue();
		}
		workbook.close();
		returning();
	}
	private void removeWorker() throws Exception {
		try {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\Workers.xls");
		FileInputStream fis = new FileInputStream(file);
		int t=0;
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		System.out.println("Available workers..!");
		
		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			Row row = itr.next();
			if(row.getRowNum()!=0)
				System.out.println(t+" "+row.getCell(1).getStringCellValue()+"\t"+row.getCell(2).getStringCellValue());
			else
				System.out.println("  "+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue());
			t++;
		}
		NumberValidation num =new NumberValidation();
		int ch = Integer.parseInt(num.enterNumber());
		Row removeRow = sheet.getRow(ch);
		sheet.removeRow(removeRow);
		workbook.close();
		returning();
		}
		catch(Exception e) {
			System.out.println("Error in remove worker");
		}
	}
	public void returning() throws Exception {
		System.out.println("\n\nReturn to ---->\n1.Worker Section\n2.Main Section");
		NumberValidation num =new NumberValidation();
		int ch = Integer.parseInt(num.enterNumber());
		switch(ch) {
		case 1: 
			workers();
			break;
		case 2:
			AdminControl adminControl = new AdminControl();
			adminControl.adminOptions();
			break;
		default:
			System.out.println("Please enter valid options");
			returning();
		}
	}
	private void accountsOfWorker() throws Exception {
		// TODO Auto-generated method stub
		returning();		
	}
	private void addWorker() throws Exception {
		LocalDate date = LocalDate.now();
		try {
		NameValidation nameValidation = new NameValidation();
		String name = nameValidation.enterName();
		System.out.println("Enter category");
		String category = enterCategory();
		MobileValidation mobileValidation = new MobileValidation();
		String mobileNumber = mobileValidation.enterMobileNumber();
		AddressValidation addressValidation = new AddressValidation();
		String address = addressValidation.enterAddress(); 
		String id = generateId(category,mobileNumber);
		
		Worker worker = new Worker(date,id, name, category, mobileNumber, address);
		worker.setDate(date);
		worker.setId(id);
		worker.setName(name);
		worker.setCategory(category);
		worker.setMobileNumber(mobileNumber);
		worker.setAddress(address);
		
		//Print worker Info
		printWokerInfo(worker);
		
		List<Worker> list = new ArrayList<Worker>();
		DataWorker dataWorker = new DataWorker();
		list.add(worker);
		dataWorker.excelGenerate(worker, list);
		System.out.println("\n=========================================\n"+"\nWorker added successfully...!\n"+"\n=========================================\n");
		returning();
		}
		catch(Exception e) {
			System.out.println("Error in adding worker..!");
		}
	}
	public static String enterCategory() {
		String cate = null;
		System.out.println("1.Master\n2.Master Supporter\n3.Writer\n4.Main Supporter\n5.Down Supporter");
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		switch(choice) {
		case 1:
			cate = "Master";
			break;
		case 2:
			cate = "Master Supporter";
			break;
		case 3:
			cate = "Writer";
			break;
		case 4:
			cate = "Main Supporter";
			break;
		case 5:
			cate = "Down Supporter";
			break;
		default:
			System.out.println("Please enter valid choice");
			enterCategory();
		}
//		in.close();
		return cate;
	}
	private void printWokerInfo(Worker worker) {
		System.out.println("Worker datails");
		System.out.println("ID of worker: "+worker.getId());
		System.out.println("Name of worker: "+worker.getName());
		System.out.println("Category of worker: "+worker.getCategory());
		System.out.println("Mobile Number of worker: "+worker.getMobileNumber());
		System.out.println("Address of worker: "+worker.getAddress());
	}
	private String generateId( String category,String mobileNumber) {
		String id = null,subCategory=null,subMobileNumber=null;
		if(category.equals("Master"))
			subCategory = "MSR";
		else if(category.equals("Master Supporter"))
			subCategory = "MSS";
		else if(category.equals("Writer"))
			subCategory = "WTR";
		else if(category.equals("Main Supporter"))
			subCategory = "MNS";
		else if(category.equals("Down Supporter"))
			subCategory = "DNS";
		else
			subCategory = "#";
		subMobileNumber = mobileNumber.substring(6, 10);
		id = subCategory + subMobileNumber;
//		System.out.println("Worker ID = "+ id);
		return id;
	}
}
