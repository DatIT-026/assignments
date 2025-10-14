package mylib;

import java.util.Scanner;
import java.util.function.Predicate;

public class Inputter {
    private static Scanner sc = new Scanner(System.in);

    // nhap chuoi không rong va phai khop voi bieu thuc regex
    public static String getString(String msg, String pattern) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim(); // doc input, cut khoang trang o 2 dau neu co

            // neu chuoi rong
            if (input.isEmpty()) {
                System.err.println("Input cannot be empty! Please try again.");
                continue;
            }
            
            if (input.matches(pattern)) return input;
                else System.err.println("Input not valid. Please try again.");
        }
    }
    
    // Nhap chuoi phai khop pattern va phai unique
    public static String readUniqueStringWithPattern(String msg, String pattern, String formatErrMsg, String uniqueErrMsg, Predicate<String> existsChecker) {
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();

            // Kiem tra rong
            if (input.isEmpty()) {
                System.err.println("Input cannot be empty! Please try again.");
                continue;
            }

            // Kiem tra format
            if (!input.matches(pattern)) {
                System.err.println(formatErrMsg);
                continue;
            }

            // Kiem tra unique
            if (existsChecker.test(input)) {
                System.err.println(uniqueErrMsg);
                continue;
            }

            return input;
        }
    }

    // ham cap nhat chuoi, neu input rong (user không nhap gi) => "No change!", nguoc lai ktra pattern
    public static String getStringUpdate(String msg, String pattern) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim(); // doc va cat khoang trang
            if (input.isEmpty()) {
                System.out.println("No change!");
                return input; // tra ve chuoi rong (khong thay doi)
            } else {
                if (input.matches(pattern)) {
                    return input;
                } else System.err.println("Input not valid. Please try again.");
            }
        }
    }

    // doc 1 dong string, co the rong
    public static String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim(); // doc dong va tra ve sau khi cat khoang trang
    }
    
    public static boolean confirmation(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) return true;

            if (input.equalsIgnoreCase("N") || input.isEmpty()) return false;

            System.err.println("Invalid input!");
        }
    }

    // ham nhap so nguyen trong khoang [min, max]
    public static int getInt(String msg, int min, int max) {
        String input;
        int result;

        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();

            if(input.isEmpty()) {
                System.err.println("Input cannot be empty! Please try again.");
                continue;
            }

            // neu input chi chua chu so
            if (input.matches("\\d+")) {
                result = Integer.parseInt(input); // chuyen chuoi sang int
                if (result >= min && result <= max) return result; // neu trong khoang hop le thi tra ve ngay
                    else System.err.println("Please enter number in range [" + min + "-" + max + "].");
            } else System.err.println("Please enter integer number.");
        }
    }

    // ham nhap so thuc phai > 0
    public static double readPositiveDouble(String msg, String errMsg) {
        double value;
        do {
            System.out.print(msg);
            // ktra input neu không phai la so thuc
            while (!sc.hasNextDouble()) {
                System.err.println("Invalid input! Please enter a number.");
                System.out.print(msg); // yeu cau nhap lai
                sc.nextLine(); // bo dong không hop le
            }

            value = sc.nextDouble(); // doc so thuc
            sc.nextLine();

            if (value <= 0) System.err.println(errMsg);
        } while (value <= 0);
        return value; // tra ve so hop le
    }

    // viet hoa chu cai dau tien cua moi tu, con lai thi la chu thuong
    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) return str;

        StringBuilder result = new StringBuilder(); // dung StringBuilder de ghep chuoi
        String[] words = str.split("\\s+"); // tach chuoi theo khoang trang

        for (String w : words) {
            if (w.isEmpty()) continue;

            // viet hoa ky tu dau, con lai chu thuong
            String formattedWord = w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase();

            // neu da co tu truoc do, them khoang trang giua cac tu do
            if (result.length() > 0) result.append(" ");
            result.append(formattedWord); // cuoi cung la them tu da dc format
        }

        return result.toString();
    }
}