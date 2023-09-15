package Utility;

import CustomReport.CustomReporter;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
//import org.apache.poi.ss.usermodel.CellType;


public class ReadDataFromExcel {
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static XSSFCell Cell;
    private static XSSFRow row;


    public static void loadUpTheExcelSheet(String path, String sheetName) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (Exception e) {
            e.printStackTrace();


        }
        try {


            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet(sheetName);
            CustomReporter.ReportTestStepStatus(Status.INFO, "Excel with Sheet name " + sheetName + " has been loaded ");
        } catch (Exception e) {
            e.printStackTrace();
            CustomReporter.ReportTestStepStatus(Status.FAIL, "Excel with Sheet name " + sheetName + " has not been loaded ");

        }
    }

    public static String getCellDataFor(int row, int col) {
        DataFormatter df = new DataFormatter();
        Cell = sheet.getRow(row).getCell(col);
        return df.formatCellValue(Cell);

    }

    public static List<String> ReadDatatFromExcelFileByColumnName(String filepath, String columnName) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filepath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        try {
            DataFormatter df = new DataFormatter();
            Workbook book;
            book = new XSSFWorkbook(fis);
            Sheet sheet = book.getSheetAt(0);
            int totalRow = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            Cell cell;
            int colNum = -1;
            for (int i = 0; i <= row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(columnName.toUpperCase())) {
                    colNum = i;
                    break;
                }
            }
            List<String> dbFiledvalue = new ArrayList<String>();
            for (int i = 1; i <= totalRow; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(colNum);
                dbFiledvalue.add(df.formatCellValue(cell));

            }
            return dbFiledvalue;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
