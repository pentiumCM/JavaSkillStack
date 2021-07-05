package indi.pentiumcm.utils.poi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils.poi
 * @className: ExcelUtil
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/7/27 20:49
 * @describe: poi工具类
 */


public class ExcelUtil {

    /**
     * excel导出
     *
     * @param name
     * @param ja
     * @param tbName
     */
    public static void exportExcel(String name, JSONArray ja, String tbName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        int index = 0;
        if (tbName.equals("")) {
            tbName = "excel";
        }
        File file = new File("D:\\" + tbName);
        if (!file.exists()) {
            file.mkdir();
        }
        JSONObject first = ja.getJSONObject(0);
        Iterator<String> iterator = first.keys(); // 得到第一项的key集合
        while (iterator.hasNext()) { // 遍历key集合
            String key = (String) iterator.next(); // 得到key
            String value = first.getString(key);
            XSSFCell cell = row.createCell(index);
            cell.setCellValue(key);
            index++;
        }
        for (int i = 0; i < ja.size(); i++) {
            row = sheet.createRow(i + 1);
            JSONObject jaa = ja.getJSONObject(i);
            Iterator<String> iterator1 = jaa.keys();
            int index1 = 0;
            while (iterator1.hasNext()) { // 遍历key集合
                String key1 = (String) iterator1.next(); // 得到key
                String value = jaa.getString(key1);
                System.out.println(value);
                XSSFCell cell = row.createCell(index1);
                cell.setCellValue(value);
                index1++;
            }
        }
        try {
            String tmpPath = "D:\\" + tbName + "\\" + name + ".xlsx";
            OutputStream outputStream = new FileOutputStream(tmpPath);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void importExcel() {
        ImportParams params = new ImportParams();
        // 表格标题所在行，计数从0开始
        params.setTitleRows(0);
        // head头部所在行，计数从0开始
        params.setHeadRows(0);

        // 表格sheet数量
        // params.setSheetNum(9);

        params.setNeedSave(true);

        List<ExcelEntity> list = ExcelImportUtil.importExcel(new File(
                "D:/test.xlsx"), ExcelEntity.class, params);
        System.out.println("");
    }

    public static void main(String[] args) {
        importExcel();
    }


//    public static void main(String[] args) {
//        String jaStr = "";
//
//        JSONArray jsonArray = JSONArray.fromObject(jaStr);
//
//        JSONArray newJsons = new JSONArray();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject json = (JSONObject) jsonArray.get(i);
//
//            if (json.get("f_vc_shijlx").equals("10")) {
//                newJsons.add(json);
//            }
//            System.out.println("hello");
//        }
//
//        ExcelUtil.exportExcel("data_7", newJsons, "data7");
//
//    }
}
