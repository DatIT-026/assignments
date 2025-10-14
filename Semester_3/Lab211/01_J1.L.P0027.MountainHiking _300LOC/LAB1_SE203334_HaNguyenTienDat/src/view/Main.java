package view;
import model.Student;
import mylib.Inputter;
import services.MountainList;
import services.StudentList;
import services.iConstants;

public class Main {
    public static void main(String[] args) {
        String filename = "MountainList.csv";
        MountainList ds_nui = new MountainList();
        ds_nui.loadDataFromFile(filename);

        String choice="";
        StudentList ds_sv = new StudentList();
        boolean saved = false; // kiem tra da save hay chua

        do {
            try {
                System.out.println("--------- Mountain Hiking Challenge Registration ---------");
                System.out.println("1. New Registration");
                System.out.println("2. Update Registration Information");
                System.out.println("3. Display all registered students");
                System.out.println("4. Delete a student registration");
                System.out.println("5. Search student by name");
                System.out.println("6. Filter registrations by campus");
                System.out.println("7. Statistics of registration numbers by location");
                System.out.println("8. Save Data to file");
                System.out.println("9. Exit the Program");
                System.out.println("----------------------------------------------------------");
                System.out.print("Enter choice: ");
                choice = Inputter.inputString("^[1-9]$");
                switch(choice) {
                    case "1":
                        if (ds_sv.createStudent(ds_nui) == true) System.out.println("\nDone!\n");
                            else System.out.println("\nFailed!\n");
                        break;

                    case "2":
                        if (ds_sv.updateStudent(ds_nui) == true) System.out.println("\nUpdate successful!\n");
                            else System.out.println("\nUpdate failed!\n");
                        break;

                    case "3":
                        ds_sv.displayStudents();
                        break;

                    case "4":
                        if (ds_sv.removeStudents(ds_nui) == true) System.out.println("\nThe registration has been successfully deleted!\n");
                            else System.out.println("\nCannot remove. Nothing changed!\n");
                        break;

                    case "5":
                        ds_sv.displayStudentByName();
                        break;

                    case "6":
                        ds_sv.filterStudentsByCampus();
                        break;

                    case "7":
                        ds_sv.statisticsByPeak(ds_nui);
                        break;

                    case "8":
                    ds_sv.saveBinaryFile("StudentList.csv");
                        saved = true;
                        break;

                    case "9":
                            if (saved) {
                                System.out.println("Exiting program...");
                                System.exit(0);
                            } else {
                                // neu chua? hoi xac nhan muon thoat nhu binh thuong
                                System.out.print("Are you sure you want to exit? (Y/N): ");
                                try {
                                    String confirm = Inputter.inputString(iConstants.COMFIRMATION).toUpperCase();
                                    if (confirm.equals("Y")) {
                                        ds_sv.saveBinaryFile("StudentList.csv");
                                        System.out.print("Exiting program...");
                                        System.exit(0);
                                    } else if (confirm.equals("N")) {
                                        System.out.println("Exiting...");
                                        System.exit(0);
                                    }
                                } catch (Exception e) {
                                    System.out.println("\nInvalid input!\n");
                                    choice = "";
                                }
                            }
                            break;
                    }
            } catch (Exception e) {
                System.out.println("\nInvalid Input!\n");
            }
        } while (!choice.equals("9"));
    }
}
