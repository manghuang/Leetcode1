package leetcode4;

public class Solution4 {

    /*
        方式一、直接使用相关jar包
        方式二、动态规划  f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
     */
//    public boolean isMatch(String s, String p) {
//        if(s == null || p == null){
//            return false;
//        }
//        int sLength = s.length();
//        int pLength = p.length();
//        if(sLength== 0 && pLength == 0){
//            return true;
//        }
//        Pattern pattern = Pattern.compile(p);
//        Matcher matcher = pattern.matcher(s);
//        return matcher.matches();
//    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {

                //  字符串的第一个字符的下标为0，所以要j-1

                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


}
