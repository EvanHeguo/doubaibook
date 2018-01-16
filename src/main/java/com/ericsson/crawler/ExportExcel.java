/**
 * Copyright (C), 2015-2018
 * FileName: ExportExcel
 * Author:   Evan
 * Date:     2018/1/16 22:57
 * Description: 把数据导出
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ericsson.crawler;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;

import java.io.File;
import java.sql.*;

/**
 * 把数据导出
 *
 * @author Evan
 * @create 2018/1/16
 * @since 1.0.0
 */
public class ExportExcel {
    public static final String url = "jdbc:mysql://localhost/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";

    public static Connection conn = null;
    public static Statement st = null;

    public void exportExcel() {
        String[] titles = {"序号", "书名", "评分", "评价人数", "作者", "出版社", "出版日期", "价格"};
        //创建Excel文件
        File file = new File("./jxl_test.xls");
        try {
            file.createNewFile();
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            Label label = null;
            //第一行设置列名
            for (int i = 0; i < titles.length; i++) {
                label = new Label(i, 0, titles[i]);
                sheet.addCell(label);
            }
            try {
                Class.forName(name);// 指定连接类型
                conn = DriverManager.getConnection(url, user, password);// 获取连接
                String sql = "SELECT *FROM  books ORDER BY score limit 0,100";
                st = conn.createStatement();// 准备执行语句
                ResultSet resultSet = st.executeQuery(sql);
                Integer i = 1;
                while (resultSet.next()) {
                    label = new Label(0, i, i.toString());
                    sheet.addCell(label);
                    label = new Label(1, i, resultSet.getString("title"));
                    sheet.addCell(label);
                    label = new Label(2, i, resultSet.getString("score"));
                    sheet.addCell(label);
                    label = new Label(3, i, resultSet.getString("rating_sum"));
                    sheet.addCell(label);
                    label = new Label(4, i, resultSet.getString("author"));
                    sheet.addCell(label);
                    label = new Label(5, i, resultSet.getString("press"));
                    sheet.addCell(label);
                    label = new Label(6, i, resultSet.getString("date"));
                    sheet.addCell(label);
                    label = new Label(7, i, resultSet.getString("title"));
                    sheet.addCell(label);
                    i++;
                }
                //写入数据
                workbook.write();
                workbook.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
                this.st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
