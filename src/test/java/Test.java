/**
 * Copyright (C), 2015-2018
 * FileName: Test
 * Author:   Evan
 * Date:     2018/1/16 20:20
 * Description: ss
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * ss
 *
 * @author Evan
 * @create 2018/1/16
 * @since 1.0.0
 */
public class Test{
    public static void main(String[] a){
        try {
            URL url=new URL("https://book.douban.com/subject_search?search_text=互联网，编程，算法&cat=1001");
            BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer=new BufferedWriter(new FileWriter("index.html"));
            String line;
            while((line=reader.readLine())!=null){
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
