package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;

public class MountainList {
    // danh sach luu tru cac doi tuong Mountain
    private ArrayList<Mountain> list;

    // Contructor
    public MountainList() {
        list = new ArrayList<>();
    }
    
    // Ham nay de mo file MountainList.csv doc,
    // doc du lieu tung dong,
    // va luu ket qua vao arrayList list
    public void loadDataFromFile(String filename) {
            FileReader f = null;
            BufferedReader r = null;
            try {
                f = new FileReader(filename); // mo file
                r = new BufferedReader(f); // doc tuan tu tung dong
                r.readLine(); // doc dong dau tien trong file va bo di, vi no la lable
                while (r.ready()) { // ktra xem du lieu co dc doc hay không
                    String s = r.readLine();        // s la 1 dong trong file
                    String[] result = s.split(","); // cut chuoi s dua vao ','
                    
                    // Vi 1 mountain co 4 fields: code, name, ...; nen can 4 chuoi con sau khi cut
                    if (result.length >= 3) {
                        String code = result[0];
                        String name = result[1];
                        String location = result[2];
                        String description = "";
                        
                        if (result.length == 4) description = result[3];

                        // tao doi tuong Mountain
                        Mountain m = new Mountain(code, name, location, description);

                        list.add(m); // them vao danh sach
                    }
                }
            } catch (Exception e) {
                System.out.println("File does not exist or cannot be read.");
            }
            
            finally {
                try {
                    if (f != null) f.close(); // close file
                    if (r != null) r.close(); // close cache
                } catch (Exception e) {
                    System.out.println("Cannot close file.");
                }
            }
        }

        // in toan bo noi dung list Mountain ra man hinh
        public void displayAll() {
            // check rong
            if (list.isEmpty()) {
                System.out.println("No mountains found in the list.");
                System.out.print("\n");
                return;
            }

            System.out.println("|-------------- MOUNTAINS LIST ---------------|");
            System.out.printf("| %-5s | %-20s | %-13s|%n",
                    "Code", "Name", "Location");
            System.out.println("|---------------------------------------------|");

            // duyet tung doi tuong Mountain trong list
            for (Mountain m : list) {
                System.out.println(m.toString());
            }

            System.out.println("|---------------------------------------------|");
        }

        public Mountain findMountain(String mountCode) {
            for (Mountain m : list) {
                if (m.getCode().equals(mountCode)) return m;
            }
            return null;
        }

        /*
            public void displayAll() {
                // check rong
                if (list.isEmpty()) {
                    System.out.println("No mountains found in the list.");
                    System.out.print("\n");
                    return;
                }

                System.out.println("|------------------------------------- MOUNTAINS LIST ---------------------------------------|");
                System.out.printf("| %-5s | %-20s | %-12s | %-44s |\n",
                        "Code", " Name", " Location", " Description");
                System.out.println("|--------------------------------------------------------------------------------------------|");

                // duyet tung doi tuong Mountain trong list
                for (Mountain m : list) {
                    // goi ham wrapText ben duoi de cat text tu m.getDescription() thanh nhieu dong voi width = 40
                    List<String> descLines = wrapText(m.getDescription(), 44);

                    if (descLines.isEmpty()) {
                        descLines.add("");
                    }

                    System.out.println(String.format("| %-5s | %-20s | %-12s | %-44s |",
                            m.getCode(), m.getName(),
                            m.getLocation(), descLines.get(0)));

                    for (int i = 1; i < descLines.size(); i++) {
                        System.out.println(String.format("| %-5s | %-20s | %-12s | %-44s |",
                                "", "", "", descLines.get(i)));
                    }
                    System.out.println("|--------------------------------------------------------------------------------------------|");
                }
            }

            // ham nay dung de cat chuoi thanh nhieu dong theo do rong width cho truoc
            public static List<String> wrapText(String text, int width) {
                List<String> lines = new ArrayList<>();
                // loop chay tu 0 den het do dai chuoi, moi lan nhay them width
                for (int i = 0; i < text.length(); i += width) {
                    // text.substring(i, Math.min(i + width, text.length())) nghia la cat chuoi con tu vi tri i den i+width
                    // neu i+width vuot qua do dai chuoi thi lay den text.length() nho vao ham Math.min()
                    // Vi du: text = "ABCDEFGH" voi width = 3 thi:
                    //          lan 1: i = 0 -> substring(0, 3) = "ABC"
                    //          lan 2: i = 3 -> substring(3, 6) = "DEF"
                    //          lan 3: i = 6 -> substring(6, 8) = "GH"

                    lines.add(text.substring(i, Math.min(i + width, text.length())));
                }
                return lines; // tra ve danh sach cac dong
            }

            // ham nay de tim Mountain dua vao code
            // tra ve: Mountain tim thay hoac null
            public Mountain findMountain(String mountCode) {
                for (Mountain m : list) {
                    if (m.getCode().equals(mountCode)) return m;
                }
                return null;
            }
        */
}
