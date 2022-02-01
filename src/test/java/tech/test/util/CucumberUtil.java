package tech.test.util;

import cucumber.api.DataTable;
import org.openqa.selenium.WebElement;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CucumberUtil {


    private static Dictionary<String, DataCollection> _dataCollection = new Hashtable<String, DataCollection>();


    public static Dictionary<String, DataCollection> ConvertDataTableToDict(DataTable table) {
        List<List<String>> data = table.raw();

        int rowNumber = 0;

        for (List<String> col : data) {
            for (int colIndex = 0; colIndex < col.size(); colIndex++) {
                _dataCollection.put(data.get(0).get(colIndex), new DataCollection(data.get(0).get(colIndex), col.get(colIndex), rowNumber));
            }

            rowNumber++;
        }

        return _dataCollection;

    }

    //ToDo: Passing the rowIndex to get the columnValue based on Row number
    public static String GetCellValue(String columnName) {
        return _dataCollection.get(columnName).ColumnValue;
    }

    public static boolean compareOneColumnList(String text, DataTable table) {

        boolean existData = false;
        List<List<String>> data = table.raw();

        int row = 0;

        for (List<String> col : data) {
            if (row != 0) {
                if (text.equals(data.get(row).get(0))) {
                    existData = true;
//                  System.out.println(text +"-----> "+data.get(row).get(0));
                    break;
                }
            }

            row++;
        }


        return existData;
    }

    public static boolean compareOneColumnList2(List<WebElement> dropDownSpecialist, DataTable table) {

        boolean existData = false;
        List<List<String>> data = table.raw();

        int row = 0;

        for (List<String> col : data) {
            if (row > 1) {
                for (WebElement we : dropDownSpecialist) {
                    if (we.getText().equals(data.get(row).get(0))) {
                        existData = true;
                        break;
                    }
                }
            }

            if (!existData & row > 1) {
                System.out.println("Don't found data " + data.get(row).get(0));
            }
            existData = false;
            row++;
        }

        return existData;
    }

    private static class DataCollection {
        private String ColumnName;
        private String ColumnValue;
        private int RowNumber;

        public DataCollection(String columnName, String columnValue, int rowNumber) {
            ColumnName = columnName;
            ColumnValue = columnValue;
            RowNumber = rowNumber;
        }
    }
}



