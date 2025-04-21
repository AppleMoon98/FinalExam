import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelRead {
	public static double[][] MEMORY;
	public int rows;
    ExcelRead() {
        try {
            FileInputStream file = new FileInputStream("data/기말프로젝트1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
        	List<Double> ARRAY = new ArrayList<Double>();
 
            int rowindex=0;
            int columnindex=0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet=workbook.getSheetAt(0);
            System.out.println("ARRAY 등록 시작");
            //행의 수
            int rows=sheet.getPhysicalNumberOfRows();
            for(rowindex=1;rowindex<rows;rowindex++){
                //행을읽는다
                XSSFRow row=sheet.getRow(rowindex);
                if(row !=null){
                    for(columnindex=0; columnindex<=4; columnindex++){
                        //셀값을 읽는다
                        XSSFCell cell=row.getCell(columnindex);
                        String value="";
                        //셀이 빈값일경우를 위한 널체크
                        if(cell==null){
                            continue;
                        }else{
                            //타입별로 내용 읽기
                            switch (cell.getCellType()){
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value=cell.getNumericCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value=cell.getStringCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                            }
                        }
                        ARRAY.add(Double.valueOf(value).doubleValue());
                    }
 
                }
            }
            System.out.println("STRING > DOUBLE 형 변환 완료");
            System.out.println("ARRAY 등록 완료");
            System.out.println("배열 전환 진행중...");
            MEMORY = new double[rows][4];
            this.rows = rows;
            for(int i = 1; i < rows; i++)
            	for(int j = 0; j < 4; j++) 
            		MEMORY[i-1][j] = ARRAY.get((i-1)*4+j);
            System.out.println("배열 전환 완료");
        }catch(Exception e) {
            e.printStackTrace();
        }
 
    }
 
}