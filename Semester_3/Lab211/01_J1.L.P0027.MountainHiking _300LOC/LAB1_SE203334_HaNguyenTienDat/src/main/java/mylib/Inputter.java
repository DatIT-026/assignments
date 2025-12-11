package mylib;

import java.util.Scanner;

public class Inputter {
    // nhap 1 chuoi va ktra chuoi vua nhap co phu hop voi pattern
    // tra ve chuoi da nhap
    // ham dung o nhieu noi nen dung throws thay vi try.. catch
    public static String inputString(String pattern) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (!str.matches(pattern)) throw new Exception(); // throw nay khac voi throws o tren
        return str;
    }

    public static String inputOptionalString(String pattern) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();
        if (str.isEmpty()) return "";
        if (!str.matches(pattern)) return null;
        return str;
    }
}
