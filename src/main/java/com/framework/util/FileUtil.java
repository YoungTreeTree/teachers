package com.framework.util;

import com.young.entity.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class FileUtil {

    static public String fileUpload(HttpServletRequest request, String path) throws IOException {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        String realPath = request.getSession().getServletContext().getRealPath(path);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String fileName = generateFileName(file.getOriginalFilename());
                    File new_file = new File(realPath, fileName);
                    //上传
                    file.transferTo(new_file);
                    return path + "/" + fileName;
                }
            }
        }
        return "";
    }

    static public String saveFile(HttpServletRequest request, CommonsMultipartFile file, String path) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath(path);
        String fileName = generateFileName(file.getOriginalFilename());
        File new_file = new File(realPath, fileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new_file);
        return path + "/" + fileName;
    }


    static public String generateFileName(String fileName) {
        //时间
        DateFormat df = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
        String formatDate = df.format(new Date());
        //全球唯一编号
        String uuid = UUID.randomUUID().toString();
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return formatDate + uuid + extension;
    }

    public static String creat_file_static(String year , ArrayList<TableCopy> tableCopies, String path, String filename,User user) throws Exception {
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(year + "年申请人信息汇总表（单位填写）"));

        //2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell c2_0 = row2.createCell(0);
        c2_0.setCellValue(new HSSFRichTextString("单位名称"));
        HSSFCell c2_1 = row2.createCell(1);
        c2_1.setCellValue(new HSSFRichTextString(user.getuName()));
        HSSFCell c2_2 = row2.createCell(2);
        c2_2.setCellValue(new HSSFRichTextString("联系人"));
        HSSFCell c2_3 = row2.createCell(3);
        c2_3.setCellValue(new HSSFRichTextString(user.getuV2Name()));
        HSSFCell c2_4 = row2.createCell(4);
        c2_4.setCellValue(new HSSFRichTextString("联系电话"));
        HSSFCell c2_5 = row2.createCell(5);
        c2_5.setCellValue(new HSSFRichTextString(user.getuV2Phone()));
        HSSFCell c2_6 = row2.createCell(6);
        c2_6.setCellValue(new HSSFRichTextString("联系邮箱"));
        HSSFCell c2_7 = row2.createCell(7);
        c2_7.setCellValue(new HSSFRichTextString(user.getuV2Email()));




        //第san行
        HSSFRow row3 = sheet.createRow(2);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("教师姓名"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("身份证号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("国内单位"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("赴任时间"));
        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("离任时间"));

        HSSFCell c3_6 = row3.createCell(6);
        c3_6.setCellValue(new HSSFRichTextString("赴任国家(洲)（中文）"));
        HSSFCell c3_33 = row3.createCell(7);
        c3_33.setCellValue(new HSSFRichTextString("赴任国家(国)（中文）"));

        HSSFCell c3_7 = row3.createCell(8);
        c3_7.setCellValue(new HSSFRichTextString("国外岗位名称（中文）"));

        HSSFCell c3_8 = row3.createCell(9);
        c3_8.setCellValue(new HSSFRichTextString("赴任国家(洲)（英文）"));
        HSSFCell c3_44 = row3.createCell(10);
        c3_44.setCellValue(new HSSFRichTextString("赴任国家(国)（英文）"));

        HSSFCell c3_9 = row3.createCell(11);
        c3_9.setCellValue(new HSSFRichTextString("国外岗位名称（英文）"));
        HSSFCell c3_10 = row3.createCell(12);
        c3_10.setCellValue(new HSSFRichTextString("申请人手机"));
        HSSFCell c3_11 = row3.createCell(13);
        c3_11.setCellValue(new HSSFRichTextString("申请人邮箱"));
        HSSFCell c3_12 = row3.createCell(14);
        c3_12.setCellValue(new HSSFRichTextString("单位联系人"));
        HSSFCell c3_13 = row3.createCell(15);
        c3_13.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_14 = row3.createCell(16);
        c3_14.setCellValue(new HSSFRichTextString("邮寄地址（有单位填写单位地址）"));
        HSSFCell c3_15 = row3.createCell(17);
        c3_15.setCellValue(new HSSFRichTextString("邮编"));

        HSSFCell c3_16 = row3.createCell(18);
        c3_16.setCellValue(new HSSFRichTextString("是否提前离任"));

        HSSFCell c3_17 = row3.createCell(19);
        c3_17.setCellValue(new HSSFRichTextString("国内单位审批意见"));


        int row_num = 3;
        int index = 1;
        for (TableCopy tc:tableCopies
             ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(tc.gettName()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString(tc.gettIdCard()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(tc.gettChinaQ()+" "+tc.gettChinaW()+" "+tc.gettChinaE()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(tc.gettStartY()+"-"+tc.gettStartM()+"-"+tc.gettStartD()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(tc.gettEndY()+"-"+tc.gettEndM()+"-"+tc.gettEndD()));

            HSSFCell c4_6 = row4.createCell(6);
            c4_6.setCellValue(new HSSFRichTextString(tc.gettWorldQ()));

            HSSFCell c4_33 = row4.createCell(7);
            c4_33.setCellValue(new HSSFRichTextString(tc.gettWorldW()));

            HSSFCell c4_7 = row4.createCell(8);
            c4_7.setCellValue(new HSSFRichTextString(tc.gettWorldE()));

            HSSFCell c4_8 = row4.createCell(9);
            c4_8.setCellValue(new HSSFRichTextString(tc.gettWorldQE()));

            HSSFCell c4_44 = row4.createCell(10);
            c4_44.setCellValue(new HSSFRichTextString(tc.gettWorldWE()));

            HSSFCell c4_9 = row4.createCell(11);
            c4_9.setCellValue(new HSSFRichTextString(tc.gettWorldEE()));

            HSSFCell c4_10 = row4.createCell(12);
            c4_10.setCellValue(new HSSFRichTextString(tc.gettPhone()));

            HSSFCell c4_11 = row4.createCell(13);
            c4_11.setCellValue(new HSSFRichTextString(tc.gettMail()));

            HSSFCell c4_12 = row4.createCell(14);
            c4_12.setCellValue(new HSSFRichTextString(tc.gettWho()));

            HSSFCell c4_13 = row4.createCell(15);
            c4_13.setCellValue(new HSSFRichTextString(tc.gettZuoji()));

            HSSFCell c4_14 = row4.createCell(16);
            c4_14.setCellValue(new HSSFRichTextString(tc.gettAddress()));

            HSSFCell c4_15 = row4.createCell(17);
            c4_15.setCellValue(new HSSFRichTextString(tc.gettEms()));

            HSSFCell c4_16 = row4.createCell(18);
            if (tc.gettIfGo()==1){
                c4_16.setCellValue(new HSSFRichTextString("是"));
            }else{
                c4_16.setCellValue(new HSSFRichTextString("否"));
            }


            HSSFCell c4_17 = row4.createCell(19);
            c4_17.setCellValue(new HSSFRichTextString(tc.gettQNoReason()));
            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 20; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }

    public static String creat_file_static3(String year , ArrayList<TableCopy> tableCopies, String path, String filename,User user) throws Exception {
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(year + "年申请人信息汇总表（单位填写）"));

        //2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell c2_0 = row2.createCell(0);
        c2_0.setCellValue(new HSSFRichTextString("单位名称"));
        HSSFCell c2_1 = row2.createCell(1);
        c2_1.setCellValue(new HSSFRichTextString(user.getuName()));
        HSSFCell c2_2 = row2.createCell(2);
        c2_2.setCellValue(new HSSFRichTextString("联系人"));
        HSSFCell c2_3 = row2.createCell(3);
        c2_3.setCellValue(new HSSFRichTextString(user.getuV2Name()));
        HSSFCell c2_4 = row2.createCell(4);
        c2_4.setCellValue(new HSSFRichTextString("联系电话"));
        HSSFCell c2_5 = row2.createCell(5);
        c2_5.setCellValue(new HSSFRichTextString(user.getuV2Phone()));
        HSSFCell c2_6 = row2.createCell(6);
        c2_6.setCellValue(new HSSFRichTextString("联系邮箱"));
        HSSFCell c2_7 = row2.createCell(7);
        c2_7.setCellValue(new HSSFRichTextString(user.getuV2Email()));




        //第san行
        HSSFRow row3 = sheet.createRow(2);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("教师姓名"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("身份证号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("国内单位"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("赴任时间"));
        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("离任时间"));

        HSSFCell c3_6 = row3.createCell(6);
        c3_6.setCellValue(new HSSFRichTextString("赴任国家(洲)（中文）"));
        HSSFCell c3_33 = row3.createCell(7);
        c3_33.setCellValue(new HSSFRichTextString("赴任国家(国)（中文）"));

        HSSFCell c3_7 = row3.createCell(8);
        c3_7.setCellValue(new HSSFRichTextString("国外岗位名称（中文）"));

        HSSFCell c3_8 = row3.createCell(9);
        c3_8.setCellValue(new HSSFRichTextString("赴任国家(洲)（英文）"));
        HSSFCell c3_44 = row3.createCell(10);
        c3_44.setCellValue(new HSSFRichTextString("赴任国家(国)（英文）"));

        HSSFCell c3_9 = row3.createCell(11);
        c3_9.setCellValue(new HSSFRichTextString("国外岗位名称（英文）"));
        HSSFCell c3_10 = row3.createCell(12);
        c3_10.setCellValue(new HSSFRichTextString("申请人手机"));
        HSSFCell c3_11 = row3.createCell(13);
        c3_11.setCellValue(new HSSFRichTextString("申请人邮箱"));
        HSSFCell c3_12 = row3.createCell(14);
        c3_12.setCellValue(new HSSFRichTextString("单位联系人"));
        HSSFCell c3_13 = row3.createCell(15);
        c3_13.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_14 = row3.createCell(16);
        c3_14.setCellValue(new HSSFRichTextString("邮寄地址（有单位填写单位地址）"));
        HSSFCell c3_15 = row3.createCell(17);
        c3_15.setCellValue(new HSSFRichTextString("邮编"));

        HSSFCell c3_16 = row3.createCell(18);
        c3_16.setCellValue(new HSSFRichTextString("是否提前离任"));

        HSSFCell c3_17 = row3.createCell(19);
        c3_17.setCellValue(new HSSFRichTextString("国内单位审批意见"));

        HSSFCell c3_18 = row3.createCell(20);
        c3_18.setCellValue(new HSSFRichTextString("汉办审批意见"));

        HSSFCell c3_19 = row3.createCell(21);
        c3_19.setCellValue(new HSSFRichTextString("证书发放日期"));

        HSSFCell c3_20 = row3.createCell(22);
        c3_20.setCellValue(new HSSFRichTextString("证书编号"));


        int row_num = 3;
        int index = 1;
        for (TableCopy tc:tableCopies
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(tc.gettName()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString(tc.gettIdCard()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(tc.gettChinaQ()+" "+tc.gettChinaW()+" "+tc.gettChinaE()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(tc.gettStartY()+"-"+tc.gettStartM()+"-"+tc.gettStartD()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(tc.gettEndY()+"-"+tc.gettEndM()+"-"+tc.gettEndD()));

            HSSFCell c4_6 = row4.createCell(6);
            c4_6.setCellValue(new HSSFRichTextString(tc.gettWorldQ()));

            HSSFCell c4_33 = row4.createCell(7);
            c4_33.setCellValue(new HSSFRichTextString(tc.gettWorldW()));

            HSSFCell c4_7 = row4.createCell(8);
            c4_7.setCellValue(new HSSFRichTextString(tc.gettWorldE()));

            HSSFCell c4_8 = row4.createCell(9);
            c4_8.setCellValue(new HSSFRichTextString(tc.gettWorldQE()));

            HSSFCell c4_44 = row4.createCell(10);
            c4_44.setCellValue(new HSSFRichTextString(tc.gettWorldWE()));

            HSSFCell c4_9 = row4.createCell(11);
            c4_9.setCellValue(new HSSFRichTextString(tc.gettWorldEE()));

            HSSFCell c4_10 = row4.createCell(12);
            c4_10.setCellValue(new HSSFRichTextString(tc.gettPhone()));

            HSSFCell c4_11 = row4.createCell(13);
            c4_11.setCellValue(new HSSFRichTextString(tc.gettMail()));

            HSSFCell c4_12 = row4.createCell(14);
            c4_12.setCellValue(new HSSFRichTextString(tc.gettWho()));

            HSSFCell c4_13 = row4.createCell(15);
            c4_13.setCellValue(new HSSFRichTextString(tc.gettZuoji()));

            HSSFCell c4_14 = row4.createCell(16);
            c4_14.setCellValue(new HSSFRichTextString(tc.gettAddress()));

            HSSFCell c4_15 = row4.createCell(17);
            c4_15.setCellValue(new HSSFRichTextString(tc.gettEms()));

            HSSFCell c4_16 = row4.createCell(18);
            if (tc.gettIfGo()==1){
                c4_16.setCellValue(new HSSFRichTextString("是"));
            }else{
                c4_16.setCellValue(new HSSFRichTextString("否"));
            }
            HSSFCell c4_17 = row4.createCell(19);
            c4_17.setCellValue(new HSSFRichTextString(tc.gettQNoReason()));

            HSSFCell c4_18 = row4.createCell(20);
            c4_18.setCellValue(new HSSFRichTextString(tc.gettENoReason()));

            HSSFCell c4_19 = row4.createCell(21);
            c4_19.setCellValue(new HSSFRichTextString(tc.gettDateY()+" - "+tc.gettDateM()+" - "+tc.gettDateD()));

            HSSFCell c4_20 = row4.createCell(22);
            c4_20.setCellValue(new HSSFRichTextString(tc.gettNumber()));
            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 20; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }


    public static String creat_users(User user , ArrayList<User> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_3 = row3.createCell(1);
        c3_3.setCellValue(new HSSFRichTextString("联系人姓名"));
        HSSFCell c3_4 = row3.createCell(2);
        c3_4.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_5 = row3.createCell(3);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        int row_num = 2;
        int index = 1;

        for (User u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_3 = row4.createCell(1);
            c4_3.setCellValue(new HSSFRichTextString(u.getuV2Name()));

            HSSFCell c4_4 = row4.createCell(2);
            c4_4.setCellValue(new HSSFRichTextString(u.getuV2Phone()));

            HSSFCell c4_5 = row4.createCell(3);
            c4_5.setCellValue(new HSSFRichTextString(u.getuV2Email()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }

    public static String creat_users2(User user , ArrayList<User> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_3 = row3.createCell(1);
        c3_3.setCellValue(new HSSFRichTextString("联系人姓名"));
        HSSFCell c3_6 = row3.createCell(2);
        c3_6.setCellValue(new HSSFRichTextString("联系人身份证"));
        HSSFCell c3_4 = row3.createCell(3);
        c3_4.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_5 = row3.createCell(4);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        int row_num = 2;
        int index = 1;

        for (User u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_3 = row4.createCell(1);
            c4_3.setCellValue(new HSSFRichTextString(u.getuV2Name()));

            HSSFCell c4_6 = row4.createCell(2);
            c4_6.setCellValue(new HSSFRichTextString(u.getuCodeW()));

            HSSFCell c4_4 = row4.createCell(3);
            c4_4.setCellValue(new HSSFRichTextString(u.getuV2Phone()));

            HSSFCell c4_5 = row4.createCell(4);
            c4_5.setCellValue(new HSSFRichTextString(u.getuV2Email()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }


    public static String creat_usersw(User user , ArrayList<User> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("单位名称"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("账号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("联系人姓名"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        int row_num = 2;
        int index = 1;

        for (User u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(u.getuName()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString(u.getuCodeE()+" "+u.getuCodeW()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(u.getuV2Name()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(u.getuV2Phone()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(u.getuV2Email()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }

    public static String creat_usersr(User user , ArrayList<User> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("单位名称"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("账号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("联系人姓名"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("联系人电话"));
        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        int row_num = 2;
        int index = 1;

        for (User u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(u.getuName()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString(u.getuCodeE()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(u.getuV2Name()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(u.getuV2Phone()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(u.getuV2Email()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }

    public static String creat_usersr1(User user , List<User1> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("单位名称"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("账号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("联系人姓名"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("联系人电话"));

        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        HSSFCell c3_6 = row3.createCell(6);
        c3_6.setCellValue(new HSSFRichTextString("联系人地址"));

        int row_num = 2;
        int index = 1;

        for (User1 u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(u.getU1ScName()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString("sc"+u.getU1CodeQ()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(u.getU1UName()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(u.getU1Phone()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(u.getU1Email()));

            HSSFCell c4_6 = row4.createCell(6);
            c4_6.setCellValue(new HSSFRichTextString(u.getU1Address()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }


    public static String creat_usersr2(User user , ArrayList<User2> users, String path, String filename) throws Exception{
//创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("统计表");
        //创建单元格
        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
//设置样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString(user.getuName() + "通讯录信息汇总表"));

        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString(user.getuV2Name()));

        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString(user.getuV2Phone()));

        HSSFCell c6 = row.createCell(6);
        c6.setCellValue(new HSSFRichTextString(user.getuV2Email()));

        //第二行
        HSSFRow row3 = sheet.createRow(1);

        HSSFCell c3_0 = row3.createCell(0);
        c3_0.setCellValue(new HSSFRichTextString("序号"));
        HSSFCell c3_1 = row3.createCell(1);
        c3_1.setCellValue(new HSSFRichTextString("个体教师姓名"));
        HSSFCell c3_2 = row3.createCell(2);
        c3_2.setCellValue(new HSSFRichTextString("教师账号"));
        HSSFCell c3_3 = row3.createCell(3);
        c3_3.setCellValue(new HSSFRichTextString("教师联系电话"));
        HSSFCell c3_4 = row3.createCell(4);
        c3_4.setCellValue(new HSSFRichTextString("教师身份证号"));

        HSSFCell c3_5 = row3.createCell(5);
        c3_5.setCellValue(new HSSFRichTextString("联系人邮箱"));

        HSSFCell c3_6 = row3.createCell(6);
        c3_6.setCellValue(new HSSFRichTextString("通讯地址"));

        int row_num = 2;
        int index = 1;

        for (User2 u:users
                ) {
            HSSFRow row4 = sheet.createRow(row_num);

            HSSFCell c4_0 = row4.createCell(0);
            c4_0.setCellValue(new HSSFRichTextString(index+""));

            HSSFCell c4_1 = row4.createCell(1);
            c4_1.setCellValue(new HSSFRichTextString(u.getU2Name()));

            HSSFCell c4_2 = row4.createCell(2);
            c4_2.setCellValue(new HSSFRichTextString("te"+u.getU2Phone()));

            HSSFCell c4_3 = row4.createCell(3);
            c4_3.setCellValue(new HSSFRichTextString(u.getU2Phone()));

            HSSFCell c4_4 = row4.createCell(4);
            c4_4.setCellValue(new HSSFRichTextString(u.getU2IdCode()));

            HSSFCell c4_5 = row4.createCell(5);
            c4_5.setCellValue(new HSSFRichTextString(u.getU2Mail()));

            HSSFCell c4_6 = row4.createCell(6);
            c4_6.setCellValue(new HSSFRichTextString(u.getU2Address()));

            index ++ ;
            row_num ++;
        }


        for (int i = 0; i < 22; i++) {
            sheet.setColumnWidth(i, 10 * 512);
        }
        FileOutputStream stream = new FileOutputStream(path + filename);
        workbook.write(stream);
        return path + filename;
    }

}
