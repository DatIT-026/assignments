package services;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import model.Mountain;
import model.Student;
import mylib.Inputter;

public class StudentList {
    private ArrayList<Student> list = new ArrayList();

    public Student findStudent(String id) {
        for (Student student : list) {
            if (student.getStuid().equals(id)) return student;
        }
        return null;
    }

    // Tao sv moi
    public boolean createStudent(MountainList ds_nui) {
        Student stu = new Student();
        boolean cont = false;
        
        // loop tao sinh vien
        do {
            try {
                // Student id
                String id = "";
                Student result = null;
                do {
                    System.out.print("Enter student id: ");
                    id = Inputter.inputString(iConstants.StudentCodePattern);
                    result = findStudent(id);
                    if (result != null) System.out.println("Duplicate id!");
                } while (result != null); // id nhap da ton tai

                // Student name
                System.out.print("Enter student name: ");
                String name = Inputter.inputString(iConstants.StudentNamePattern);

                // Student email
                System.out.print("Enter student email: ");
                String email = Inputter.inputString(iConstants.StudentEmailPattern);

                //Student phone
                System.out.print("Enter student phone: ");
                String phone = Inputter.inputString(iConstants.StudentPhonePattern);

                // Student Mountain
                Mountain find = null;
                String mountCode = "";
                do {
                    ds_nui.displayAll();
                    System.out.print("Enter mountain code: ");
                    mountCode = Inputter.inputString(iConstants.MountainCodePattern);
                    find = ds_nui.findMountain(mountCode);
                } while (find == null);

                // fee
                double price = 6000000;
                if (phone.matches(iConstants.VIETTEL_VALID) || phone.matches(iConstants.VNPT_VALID)) {
                    price *= 0.65;
                }

                // lay cac data nhap dc gan vao stu
                stu.setStuid(id);
                stu.setName(name);
                stu.setEmail(email);
                stu.setPhone(phone);
                stu.setMountainCode(mountCode);
                stu.setPrice(price);

                // them stu vao array
                list.add(stu);
                cont = false; // gan false de do... while không loop nua
                return true;

            } catch (Exception e) {
                System.out.println("\nStudent information is invalid.\n");
                cont = true;
            }
        } while (cont);

        return false;
    }

    // Cap nhat thong tin dang ky moi cho sinh vien
    public boolean updateStudent (MountainList ds_nui) {
        String id = "";
        Student stu = null;

        // tim sv da dang ky de update
        do {
            try {
                System.out.print("Enter student id to update: ");
                id = Inputter.inputString(iConstants.StudentCodePattern);
                stu = findStudent(id);

                if (stu == null) {
                    System.out.print("\nThis student has not registered yet.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid ID Format!\n");
            }
        } while (stu == null);

        // goi ham updateField de cap nhat thong tin sinh vien
        stu.setName(updateField("name", stu.getName(), iConstants.StudentNamePattern));
        stu.setEmail(updateField("email", stu.getEmail(), iConstants.StudentEmailPattern));
        stu.setPhone(updateField("phone", stu.getPhone(), iConstants.StudentPhonePattern));
        
        ds_nui.displayAll();
        stu.setMountainCode(updateField("mountain code", stu.getMountainCode(), iConstants.MountainCodePattern));

        // Re-calculate fee
        double price = 6000000;
        if (stu.getPhone().matches(iConstants.VNPT_VALID) || stu.getPhone().matches(iConstants.VIETTEL_VALID))
            price *= 0.65;
        stu.setPrice(price);

        return true;
    }

    // ham nay ho tro cho viec update sv 1 cach gon hon, tranh bi trung lap
    private String updateField(String label, String oldValue, String pattern) {
        System.out.print("Enter new " + label + " (leave blank to keep old): ");
        String value = Inputter.inputOptionalString(pattern); // goi inputter de nhap thong tin can dc cap nhat
        if (value == null) { // Neu value la null, giu nguyen info nhu cu
            System.out.println("Invalid " + label + " format. Keep old value.");
            return oldValue; // tra ve gia tri cu
        }
        return value.isEmpty() ? oldValue : value; // neu value rong thi tra ve value cu, khong thi update value moi
    }

    // Hien thi sv da dang ky
    public void displayStudents() {
        if (list.isEmpty()) {
            System.out.println("\nNo students have registered yet.");
            System.out.print("\n");
            return;
        }

        System.out.println("\nRegistered Students:");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-24s | %-10s | %-10s | %s%n",
                "Student ID", "Name", "Phone", "Mountain Code", "Fee");
        System.out.println("--------------------------------------------------------------------------------");

        for (Student student : list) {
            System.out.println(student);
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // Xoa sv da dang ky
    public boolean removeStudents(MountainList ds_nui) {
        String id = "";
        Student stu = null;
        boolean cont = false;

        do {
            try {
                System.out.print("Enter student id to delete: ");
                id = Inputter.inputString(iConstants.StudentCodePattern).toUpperCase();
                stu = findStudent(id);

                if (stu == null) {
                    System.out.print("\nThis student has not registered yet.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid ID Format!\n");
                cont = true;
            }
        } while (cont);

            System.out.println("\nStudents Details:");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("StudentID: %s\nName: %s\nPhone: %s\nMountain: %s\nFee: %,10.0f", stu.getStuid(), stu.getName(), stu.getPhone(), stu.getMountainCode(), stu.getPrice());
            System.out.println("\n--------------------------------------------------------------------------------");

            // xac nhan
            System.out.print("Are you sure you want to delete this registration? (Y/N): ");
            String choice = null;
            try {
                choice = Inputter.inputString(iConstants.COMFIRMATION).toUpperCase();
                if (choice.contains("Y")) {
                    list.remove(stu);
                    return true; // xoa thanh cong
                } else if (choice.contains("N")) {
                    return false; // nguoi dung tu choi
                }
            } catch (Exception e) {
                System.out.print("\nInvalid Input!");
                choice = "";
                return false; // co van de nhap lieu -> không xoa
            }

        return true;
    }

    // search
    public void displayStudentByName() {
        boolean flag = false;
        try {
            System.out.print("Enter name to search: ");
            String name = Inputter.inputString(iConstants.StudentNamePattern);

            if (name.isEmpty()) {
                System.out.println("Invalid name format.");
                return;
            }

            System.out.println("\nSearch Result:\n");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-24s | %-10s | %-10s | %s%n",
                    "Student ID", "Name", "Phone", "Mountain Code", "Fee");
            System.out.println("--------------------------------------------------------------------------------");

            for (Student s : list) {
                if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(s.toString());
                    flag = true;
                }
            }

            if (!flag) {
                System.out.println("|                       No one matches the search criteria!                    |");
            }

            System.out.println("--------------------------------------------------------------------------------\n");


        } catch (Exception e) {
            System.out.println("\nInvalid ID Format!\n");
        }
    }

    // Filter
    public void filterStudentsByCampus() {
        boolean flag = false;
        String campus = "";

        try {
            System.out.print("Enter campus code (CE, DE, HE, SE, QE): ");
            campus = Inputter.inputString("(?i)^(CE|DE|HE|SE|QE)$").toUpperCase();

            String campusName;
            switch (campus) {
                case "CE": campusName = "Can Tho"; break;
                case "DE": campusName = "Da Nang"; break;
                case "HE": campusName = "Ha Noi"; break;
                case "SE": campusName = "Ho Chi Minh"; break;
                case "QE": campusName = "Quy Nhon"; break;
                default: campusName = campus; break;
            }

            System.out.println("\nRegistered Students Under " + campusName + " Campus (" + campus + "):");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-24s | %-10s | %-10s | %s\n",
                    "StudentID", "Name", "Phone", "Mountain Code", "Fee");
            System.out.println("--------------------------------------------------------------------------------");

            for (Student s : list) {
                if (s.getStuid().startsWith(campus)) {
                    System.out.println(s.toString());
                    /*System.out.printf(" %-10s | %-20s | %-12s | %-10s | %,10.0f \n",
                            s.getStuid(), s.getName(), s.getPhone(),
                            s.getMountainCode(), s.getPrice());*/
                    flag = true;
                }
            }

            if (!flag) {
                System.out.println("                 No students have registered under this campus.             ");
            }

            System.out.println("--------------------------------------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("\nInvalid campus code format!\n");
        }
    }

    // Statistics
    public void statisticsByPeak(MountainList ds_nui) {
        if (list.isEmpty()) {
            System.out.println("\nNo students registered yet!\n");
            return;
        }

        // Hashmap de dem so luong nguoi di va tong phi theo tung ngon nui
        HashMap<String, Integer> countMap = new HashMap<>();
        HashMap<String, Double> costMap = new HashMap<>();

        // loop duyet tung sv trong list
        for (Student s : list) {
            // tim mountain tuong ung voi ma mountain ma sv da chon
            Mountain m = ds_nui.findMountain(s.getMountainCode());

            // neu tim thay mountain (hay m không duoc rong)
            if (m != null) {
                // lay ten dinh nui de dung lam key trong HashMap
                String peakName = m.getName();

                // cap nhat so luong nguoi dky cho dinh nui nay`
                // countMap.getOrDefault(peakName, 0) se lay gia tri hien tai, neu chua thi tra ve 0
                // sau do cong them 1 cho sv hien tai
                countMap.put(peakName, countMap.getOrDefault(peakName, 0) + 1);

                // cap nhat tong chi phi
                // costMap.getOrDefault(peakName, 0.0) lay tong chi hien tai, neu chua co thi = 0
                // sau do cong them chi phi ma sv hien tai da tra voi s.getPrice()
                costMap.put(peakName, costMap.getOrDefault(peakName, 0.0) + s.getPrice()); // tong cong chi phi
            }
        }

        System.out.println("\nStatistics of Registration by Mountain Peak:");
        System.out.println("|--------------------------------------------------------------|");
        System.out.printf("| %-20s | %-20s | %-12s |\n",
                " Peak Name", "Number of Participants", "Total Cost");
        System.out.println("|--------------------------------------------------------------|");

        // duyet qua tung peak de in so lieu
        for (String peak : countMap.keySet()) {
            int num = countMap.get(peak); // lay so luong nguoi dang ky o ngon nui hien tai
            double total = costMap.get(peak); // lay tong chi phi cua all sv o ngon nui do
            System.out.printf("| %-20s | %-22d | %-,12.0f |\n", peak, num, total);
        }
        System.out.println("|--------------------------------------------------------------|\n");
    }

    /* just for test case
    public void loadBinaryFile(String filename) {
        FileInputStream f = null;
        ObjectInputStream ois = null;
        try {
            f = new FileInputStream(filename);
            ois = new ObjectInputStream(f);

            // read data (cast ve dung kieu)
            list = (ArrayList<Student>) ois.readObject();

            System.out.println("\nData loaded successfully from " + filename + ":\n");
            for (Student s : list) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        } finally {
            try {
                if (ois != null) ois.close();
                if (f != null) f.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    */

    // Save Data (serialize Student list)
    public void saveBinaryFile(String filename) {
        if (list.isEmpty()) {
            System.out.println("\nNo students to save!\n");
            return;
        }

        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(filename);
            of = new ObjectOutputStream(f);
            of.writeObject(list); // lenh nay chi run dc neu chung ta enable tinh nang convert object -> byte
            of.flush(); // bat buoc compiler day data

            System.out.println("\nRegistration data has been successfully saved to " + filename + "\n");
        } catch (Exception e) {
            System.out.println("\nError saving file: " + e.getMessage() + "\n");
            // e.printStackTrace();
        } finally {
            try {
                if (of != null) of.close();
                if (f != null) f.close();
            } catch (Exception e) {
                System.out.println("\nError closing file: " + e.getMessage() + "\n");
                // e.printStackTrace();
            }
        }
    }
}
