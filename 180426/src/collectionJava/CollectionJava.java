/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionJava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Phuong Anh
 */
public class CollectionJava {

    private ArrayList<Student> listStudent;

    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    private void generateStudents() {
        listStudent = new ArrayList<>();

        listStudent.add(new Student("A001", "Xuân Hùng", 10000, "20/10/2018"));
        listStudent.add(new Student("A002", "Nguyễn Minh", 10000, "20/10/2018"));
        listStudent.add(new Student("A003", "Nguyễn An", 20000, "20/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 10000, "21/10/2018"));
        listStudent.add(new Student("A005", "Phương Anh", 10000, "21/10/2018"));
        listStudent.add(new Student("A006", "Trịnh Thúy", 40000, "22/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 50000, "22/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 60000, "23/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 200000, "24/10/2018"));
        listStudent.add(new Student("A002", "Nguyễn Minh", 20000, "25/10/2018"));
        listStudent.add(new Student("A003", "Nguyễn An", 20000, "26/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 50000, "27/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 70000, "28/10/2018"));
    }

    public static void main(String[] args) throws IOException {
        CollectionJava colJava = new CollectionJava();
        colJava.generateStudents();
        colJava.saveToFile();
        for (Student student : colJava.getListStudent()) {
            System.out.println(student.getName());
        }
    }

    // Câu 1. In ra file
    private void saveToFile() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("danhsachnoptien.txt")) {
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);

            int totalBalance = 0;

            bw.write(String.format("%15s | %15s | %15s | %15s",
                    "Mã sinh viên", "Tên sinh viên", "Số tiền (VNĐ)", "Ngày nộp"));
            bw.newLine();
            bw.write("======================================================================");
            bw.newLine();
            for (int i = 0; i < listStudent.size(); i++) {
                Student student = listStudent.get(i);
                // Format String output
                bw.write(String.format("%15s | %15s | %15s | %15s",
                        student.getRollNumber(), student.getName(), student.getBalance(), student.getUpdatedTime()));

//            bw.write(student.getRollNumber());
//            bw.write("\t\t|\t");
//            bw.write(student.getName());
//            bw.write("\t\t|\t");
//            bw.write(student.getBalance());
//            bw.write("\t\t|\t");
//            bw.write(student.getUpdatedTime());
                totalBalance += student.getBalance();
                bw.newLine();
            }

            bw.write("----------------------------------------------------------------------");
            bw.newLine();
            bw.write(String.format("%50s %15s", "Tổng số tiền", (totalBalance + " VNĐ")));
            bw.newLine();
            bw.close();
            osw.close();
        }
    }

    // Câu 2. Đọc từ file trên, in ra đúng format
    public void printFile() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        br.readLine();

        while ((inputLine = br.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Câu 3. Đọc từ file, với mỗi dòng thông tin sinh viên, lưu ra đối tượng Student,
    // sau đó lưu vào ArrayList khác với listStudent
    public ArrayList<Student> readFileToArray() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("utf-8"));
        BufferedReader br = new BufferedReader(isr);

        String inputLine;
//        br.readLine();
//        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Student> arrStudent = new ArrayList<>();

        // Cách 1: Sử dụng Scanner
//        while ((inputLine = br.readLine()) != null) {
//
//            try {
//                Scanner scn = new Scanner(inputLine);
//                scn.useDelimiter("\\|");
//                int nextCount = 1;
//
//                String rollNumber = "";
//                String name = "";
//                int balance = 0;
//                String updatedTime = "";
//                while (scn.hasNext()) {
//
//                    switch (nextCount) {
//                        case 1:
//                            rollNumber = scn.next();
//                            nextCount++;
//                            rollNumber = rollNumber.trim();
//                            break;
//                        case 2:
//                            name = scn.next();
//                            nextCount++;
//                            name = name.trim();
//                            break;
//                        case 3:
//                            try {
//                                balance = Integer.parseInt(scn.next().trim());
//                                nextCount++;
//                            } catch (NumberFormatException e) {
//                                System.err.println("Không thể parse balance tại dòng " + inputLine);
//                            }
//                            break;
//                        case 4:
//                            updatedTime = scn.next();
//                            nextCount++;
//                            updatedTime = updatedTime.trim();
//                            break;
//                        default:
//                            break;
//                    }
//
//                    if (nextCount == 5) {
//                        Student student = new Student(rollNumber, name, balance, updatedTime);
//                        arrStudent.add(student);
//                    }
//
//                    scn.close();
//                }
//            } catch (NoSuchElementException e) {
//                System.out.println("Lỗi khi đọc dữ liệu dòng " + inputLine);
//                continue;
//            }
//        }
// Cách 2: Sử dụng split
//        Student student = new Student();
//        List<String> studentLines;
//        studentLines = Files.readAllLines(java.nio.file.Paths.get("danhsachnoptien.txt"), StandardCharsets.UTF_8);
        while ((inputLine = br.readLine()) != null) {
            String[] splitArray = inputLine.split("|");
            if (splitArray.length != 4) {
                continue;
            }
            String strBalance = splitArray[2].trim();
            int balance = 0;
            try {
                balance = Integer.parseInt(strBalance);
            } catch (Exception e) {
                continue;
            }
            String rollNumber = splitArray[0].trim();
            String name = splitArray[1].trim();
            String updatedTime = splitArray[3].trim();
            Student student = new Student(rollNumber, name, balance, updatedTime);
            arrStudent.add(student);
        }
        return arrStudent;
    }

    // Câu 4. Đọc từ file, tạo ra hashSet Student, không trùng rollNumber
    public HashSet<Student> readFileToHashSet() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("utf-8"));
        BufferedReader br = new BufferedReader(isr);

        String inputLine;
//        br.readLine();
//        StringBuilder stringBuilder = new StringBuilder();
        HashSet<Student> setStudent = new HashSet<>();
        while ((inputLine = br.readLine()) != null) {
            String[] splitArray = inputLine.split("|");
            if (splitArray.length != 4) {
                continue;
            }
            String strBalance = splitArray[2].trim();
            int balance = 0;
            try {
                balance = Integer.parseInt(strBalance);
            } catch (Exception e) {
                continue;
            }
            String rollNumber = splitArray[0].trim();
            String name = splitArray[1].trim();
            String updatedTime = splitArray[3].trim();
            Student student = new Student(rollNumber, name, balance, updatedTime);
            setStudent.add(student);
        }
        return setStudent;
    }

    // Câu 5. Đọc từ file, tạo 1 danh sách tổng hợp tiền của sinh viên
    public HashMap<String, Student> generateListWithTotalMoney() throws FileNotFoundException, IOException {
        HashMap<String, Student> mapStudent = new HashMap<>();
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("utf-8"));
        BufferedReader br = new BufferedReader(isr);
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            String[] splitArray = inputLine.split("|");
            if (splitArray.length != 4) {
                continue;
            }
            String strBalance = splitArray[2].trim();
            int balance = 0;
            try {
                balance = Integer.parseInt(strBalance);
            } catch (Exception e) {
                continue;
            }
            String rollNumber = splitArray[0].trim();
            String name = splitArray[1].trim();
            String updatedTime = splitArray[3].trim();
            Student student = new Student(rollNumber, name, balance, updatedTime);
            if (mapStudent.containsKey(student.getRollNumber())) {
                Student existing = mapStudent.get(student.getRollNumber());
                student.setBalance(student.getBalance() + existing.getBalance());
            }
            mapStudent.put(student.getRollNumber(), student);
        }
        return mapStudent;
    }
}
