package plagiarismCheckingSystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileToArticle {
    //文件中的文本
    private String str;

    //空参构造
    public FileToArticle() {
    }

    //返回文本内容
    public String getStr() {
        return str;
    }

    //通过文件路径导入文本
    public void setStr(String address) throws IOException {
        FileReader fr = new FileReader(address);
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        fr.close();
        str = sb.toString();
    }

    //将double数组结果通过文件路径导出
    public void printResult(String address, double[] result) throws IOException {
        FileWriter fw = new FileWriter(address);
        for (double v : result) {
            fw.write(String.format("%.2f",v));
        }
        fw.close();
    }
}
