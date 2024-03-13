package plagiarismCheckingSystem;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //原文路径
        String address1;
        //抄袭文路径
        String address2;
        //结果文件路径
        String ans;

        try {
            address1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("未输入原文路径，请输入原文路径!");
            return;
        }

        try {
            address2 = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("未输入抄袭文路径，请输入抄袭文路径!");
            return;
        }

        try {
            ans = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("未输入结果文件路径，请输入结果文件路径!");
            return;
        }

        FileToArticle ft = new FileToArticle();

        //通过路径导入原文文章
        String article1;
        try {
            article1 = createPaper(address1, ft);
        } catch (IOException e) {
            System.out.println("原文章路径错误，请检查路径是否正确！");
            return;
        }

        //通过路径导入抄袭文章
        String article2;
        try {
            article2 = createPaper(address2, ft);
        } catch (IOException e) {
            System.out.println("抄袭文章路径错误，请检查路径是否正确！");
            return;
        }

        //原文对象
        Paper p1 = new Paper(article1);
        //抄袭文对象
        Paper p2 = new Paper(article2);

        //结果
        double[] d = new double[1];
        d[0] = p1.calculateSimilarity(p2);
        try {
            ft.printResult(ans, d);
        } catch (IOException e) {
            System.out.println("结果文件路径错误，请检查路径是否正确！");
            return;
        }

        System.out.println("结果已成功导出到--" + ans);
    }

    //通过路径导入文章
    public static String createPaper(String address, FileToArticle ft) throws IOException {
        ft.setStr(address);
        return ft.getStr();
    }
}
