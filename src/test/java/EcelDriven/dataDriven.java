package EcelDriven;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\hp/Desktop\\demodata.xlsx"); // path of that excel 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);  // In this stage we have access to the workbook
		int sheets=workbook.getNumberOfSheets();
		for(int i =0; i<sheets; i++) {
			
			if(workbook.getSheetName(i).equalsIgnoreCase("TestData"))
			{
			XSSFSheet sheet=workbook.getSheetAt(i);  // In this stage we have access to the sheet and sheet is collection of entire row
			
			Iterator <Row> rows=sheet.rowIterator();  // Iterating each row of the sheet
			Row firstrow=rows.next();
			Iterator <Cell> ce=firstrow.cellIterator();
			
			int k=0;
			int column=0;
			while(ce.hasNext()) {
				Cell value=ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
					column=k;
				}
				k++;
			}
			System.out.println(column);
			
			}
		}
	}

}
