package restApiAutomation.utils;

import java.io.FileInputStream;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {

	Workbook workBook;
	Sheet workSheet;
	Row row;
	Cell cell;
	private Object sheetData[][];

	public Object[][] getExcelSheetData(String filePath, String sheetName) {
		try {
			workSheet = getExcelSheet(filePath, sheetName);
			int rowCount = workSheet.getLastRowNum();
			int lastCellNum = workSheet.getRow(0).getLastCellNum();
			sheetData = new Object[rowCount + 1][lastCellNum];
			int cellCount = 0;
			for (int i = 0; i <= rowCount; i++) {
				row = workSheet.getRow(i);
				cellCount = row.getLastCellNum();
				for (int j = 0; j < cellCount; j++) {
					cell = row.getCell(j);
					if (cell != null) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							sheetData[i][j] = cell.getStringCellValue();
						} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
							sheetData[i][j] = cell.getNumericCellValue();
						} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
							sheetData[i][j] = cell.getBooleanCellValue();
						} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
							sheetData[i][j] = cell.getCellFormula();
						}
					} else {
						sheetData[i][j] = "";
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetData;
	}

	public String getCellData(String filePath, String sheetName, int rowNum, int colNum) {

		String cellData = null;
		try {
			if (rowNum < 1)
				throw new FrameworkException(Constants.EXCEL_ROW_NO_EXCEPTION);
			if (colNum < 1)
				throw new FrameworkException(Constants.EXCEL_COLUMN_NO_EXCEPTION);

			workSheet = getExcelSheet(filePath, sheetName);
			row = workSheet.getRow(rowNum - 1);

			if (row == null)
				throw new FrameworkException(Constants.EXCEL_BLANK_ROW_EXCEPTION);

			cell = row.getCell(colNum - 1);

			if (cell != null) {
				if (cell.getCellTypeEnum() == CellType.STRING) {
					cellData = cell.getStringCellValue();
				} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					cellData = cell.getNumericCellValue() + "";
				} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
					cellData = cell.getBooleanCellValue() + "";
				} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
					cellData = cell.getCellFormula();
				}
			} else {
				cellData = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}

	public int getRowCount(String filePath, String sheetName) {

		int rowCount = 0;
		try {
			workSheet = getExcelSheet(filePath, sheetName);
			rowCount = workSheet.getLastRowNum() + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public int getColumnCount(String filePath, String sheetName) {

		int colCount = 0;
		try {
			workSheet = getExcelSheet(filePath, sheetName);
			row = workSheet.getRow(0);

			if (row == null)
				return -1;
			colCount = row.getLastCellNum();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return colCount;
	}

	public ArrayList<String> getRowData(String filePath, String sheetName, int rowNum) {

		ArrayList<String> rowData = new ArrayList<String>();
		try {
			if (rowNum < 1)
				throw new FrameworkException(Constants.EXCEL_ROW_NO_EXCEPTION);
			workSheet = getExcelSheet(filePath, sheetName);
			int cellCount = 0;
			row = workSheet.getRow(rowNum - 1);
			if (row == null)
				throw new FrameworkException(Constants.EXCEL_BLANK_ROW_EXCEPTION);
			cellCount = row.getLastCellNum();
			for (int j = 0; j < cellCount; j++) {
				cell = row.getCell(j);
				if (cell != null) {
					if (cell.getCellTypeEnum() == CellType.STRING) {
						rowData.add(cell.getStringCellValue());
					} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						rowData.add(cell.getNumericCellValue() + "");
					} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
						rowData.add(cell.getBooleanCellValue() + "");
					} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
						rowData.add(cell.getCellFormula());
					}
				} else {
					rowData.add("");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowData;
	}

	public ArrayList<String> getColumnData(String filePath, String sheetName, int columnNum) {

		ArrayList<String> colData = new ArrayList<String>();

		int columnEmptyFlag = 0;
		try {
			if (columnNum < 1)
				throw new FrameworkException(Constants.EXCEL_COLUMN_NO_EXCEPTION);

			workSheet = getExcelSheet(filePath, sheetName);
			int rowNum = workSheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {
				row = workSheet.getRow(i);
				cell = row.getCell(columnNum - 1);
				if (cell != null) {
					if (cell.getCellTypeEnum() == CellType.STRING) {
						colData.add(cell.getStringCellValue());
					} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						colData.add(cell.getNumericCellValue() + "");
					} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
						colData.add(cell.getBooleanCellValue() + "");
					} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
						colData.add(cell.getCellFormula());
					}
				} else {
					colData.add("");
					columnEmptyFlag++;
				}

				if (columnEmptyFlag == rowNum)
					throw new FrameworkException(Constants.EXCEL_BLANK_COLUMN_EXCEPTION);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return colData;
	}

	private Sheet getExcelSheet(String filePath, String sheetName) {
		try {
			if (filePath == null || filePath.equals(""))
				throw new FrameworkException(Constants.EXCEL_PATH_EXCEPTION);

			FileInputStream fs = new FileInputStream(filePath);
			
			if(filePath.endsWith(".xlsx")){
				workBook = new XSSFWorkbook(fs);
			}else if(filePath.endsWith(".xls")){
				workBook = new HSSFWorkbook(fs);
			}
			int index = workBook.getSheetIndex(sheetName);
			if (index == -1)
				throw new FrameworkException(Constants.EXCEL_SHEET_NAME_EXCEPTION);

			workSheet = workBook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workSheet;
	}
}
