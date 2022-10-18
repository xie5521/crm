package com.bjpowernode.crm.poi;

import com.bjpowernode.crm.commons.utils.HSSFUtiles;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/*
* 使用apache-poi解析excel
* */
public class ParseExcelTest {

    @Test
    public  void main() throws Exception{
        //根据excel文件生成HSSFWorkbook对象，封装了excel文件的全部信息
        InputStream is = new FileInputStream("E:\\CRM-ssm\\code\\新建文件夹\\studentList.xls");
        HSSFWorkbook wb = new HSSFWorkbook(is);
        //根据wb获取HSSFSheet对象，封装了一页的所有信息
        HSSFSheet sheet = wb.getSheetAt(0);       //页的下标
        //根据sheet获取HSSFRow对象，封装了一行的所有信息
        HSSFRow row = null;
        HSSFCell cell = null;
        for(int i = 0; i <= sheet.getLastRowNum(); i++){     //getLastRowNum最后一行的下表
            row = sheet.getRow(i);        //行的下表，下表从0开始，
            for(int j = 0; j < row.getLastCellNum(); j++){     //getLastCellNum最后一列下表+1
                //根据row获取HSSFCell对象，封装了一列的所有信息
                cell = row.getCell(j);

                //获取数据
                System.out.print(HSSFUtiles.getCellValueForStr(cell)+ " ");
            }
            //每一行中的所有列都打完，打印一个换行
            System.out.println();
        }
    }


}
