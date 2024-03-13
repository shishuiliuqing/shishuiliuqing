package plagiarismCheckingSystem;

public class Paper {
    //原文(无标点符号)
    private String article;
    //原文长(无标点符号)
    private int length;

    //初始化文章
    public Paper(String article) {
        setArticle(article);
    }

    //配置文章
    public void setArticle(String article) {
        this.article = delete(article);
        this.length = this.article.length();
    }

    //返回文章
    public String getArticle() {
        return article;
    }

    //删除文章中的标点符号，回车，空格
    private String delete(String article) {
        return article.replaceAll("[\\s，。：“”、！；？《》（）*+\\-/]", "");
    }

    //计算本文与另一文章的相似度
    public double calculateSimilarity(Paper anotherArticle) {
        //另一篇文章
        String original = anotherArticle.getArticle();
        if (null == original) return 0;
        //长文章作为比较原文，短文章作为参考原文
        if (original.length() > length) {
            return anotherArticle.calculateSimilarity(this);
        }

        //文章比较操作
        articleCompare(anotherArticle);
        return 1-(article.length()*1.0/length);
    }

    //将本文与另一文章进行相似比较,删除所有与另一文章相同的词和语句
    private void articleCompare(Paper anotherArticle) {
        //比较原文
        String original = anotherArticle.getArticle();
        //词长
        int wordLength = 3;
        //文本连续判断，若连续则把连续片段标记为 *
        while (original.length() >= wordLength) {
            //从文章开头取一个词
            String word = original.substring(0, wordLength);

            if (!markWord(word)) {
                char character = word.charAt(wordLength - 1);
                markCharacter(character);
            }

            //删除文章第一个字
            original = deleteFirstCharacter(original);
        }

        //把剩余不足一个词长的文字进行判断
        for (int i = 0; i < wordLength - 1 && i < original.length(); i++) {
            markCharacter(original.charAt(i));
        }

        article = article.replace("*", "");

    }

    //在文章标记某个词,成功找到该词则返回true，否则返回false
    private boolean markWord(String word) {
        int length = article.length();
        article = article.replace(word, "*");
        return length != article.length();
    }

    //在文章标记某个字
    private void markCharacter(char character) {
        markWord("*" + character);
    }

    //删除文章第一个字
    private String deleteFirstCharacter(String article) {
        if (article.equals("") || article.length() == 1) return "";
        return article.substring(1);
    }
}
