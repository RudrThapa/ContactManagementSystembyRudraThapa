import com.sun.source.tree.TryTree;

import java.sql.*;
import java.util.Scanner;

public class ContactManagementSystem {
    static Scanner sc = new Scanner(System.in);

    // Function for insertion into the DataBase...
    public static void insertInto(String phone, String fName, String lName, String city) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        String sql = "INSERT INTO ContactManagement.contact (phone, fname, lname, city) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, phone);
        ps.setString(2, fName);
        ps.setString(3, lName);
        ps.setString(4, city);

        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Successfully Inserted!");
        } else {
            System.out.println("Failed to insert!");
        }

        ps.close();
        con.close();
    }

    // Function for Update of the Tuples of the Table...
    public static void updateInfo(int choice, String phone, String fName, String lName, String city) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");

        switch (choice) {

            case 1:
                String sql = "UPDATE ContactManagement.contact SET phone = ? WHERE fname = ? AND lname = ? AND city = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, phone);
                ps.setString(2, fName);
                ps.setString(3, lName);
                ps.setString(4, city);

                int i = ps.executeUpdate();

                if (i > 0) {
                    System.out.println("Successfully Updated!");
                } else {
                    System.out.println("Failed To Update!");
                }
                ps.close();
                break;

            case 2:
                String sql1 = "UPDATE ContactManagement.contact SET fname = ? AND lname = ? WHERE phone = ? And city = ?";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, fName);
                ps1.setString(2, lName);
                ps1.setString(3, phone);
                ps1.setString(4, city);

                int i1 = ps1.executeUpdate();

                if (i1 > 0) {
                    System.out.println("Successfully Updated!");
                } else {
                    System.out.println("Failed To Update!");
                }
                ps1.close();
                break;

            case 3:
                String sql2 = "UPDATE ContactManagement.contact SET city = ? WHERE fname = ? AND lname = ? AND phone = ?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setString(1, city);
                ps2.setString(2, fName);
                ps2.setString(3, lName);
                ps2.setString(4, phone);

                int i2 = ps2.executeUpdate();

                if (i2 > 0) {
                    System.out.println("Successfully Updated!");
                } else {
                    System.out.println("Failed To Update!");
                }
                ps2.close();
                break;

            default:
                System.out.println("Entered a wrong choice. Please try again.");
        }

        con.close();
    }

    // Function for Deletion...
    public static void deleteInfo(String phone, String fName, String lName, String city)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        String sql = "DELETE FROM ContactManagement.contact WHERE phone = ? AND fname = ? AND lname = ? AND city = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, phone);
        ps.setString(2, fName);
        ps.setString(3, lName);
        ps.setString(4, city);

        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Successfully Deleted!");
        } else {
            System.out.println("Failed To Delete!");
        }
        ps.close();
        con.close();
    }

    // Function to Display all the Data inside the Table...
    public static void displayContact() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ContactManagement.contact");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String roll = rs.getString("phone");
            System.out.println("Phone Number: " + roll);

            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            System.out.println("Name: " + fName + " " + lName);

            String city = rs.getString("city");
            System.out.println("City : " + city);
            System.out.println("-------------------------------------------------------------------");
        }

        rs.close();
        ps.close();
        con.close();

    }

    // Function to Find a Contact with Phone Number...
    public static void findContactByPhone(String phone) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ContactManagement.contact WHERE phone = ?");
        ps.setString(1, phone);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String Phone = rs.getString("phone");
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String City = rs.getString("city");

            System.out.println("Phone Number: " + Phone);
            System.out.println("Name: " + fName + " " + lName);
            System.out.println("City: " + City);
            System.out.println("----------------------------------------------------------");
        }

        rs.close();
        ps.close();
        con.close();
    }

    public static void findContactByName(String fname, String lname) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        PreparedStatement ps = con
                .prepareStatement("SELECT * FROM ContactManagement.contact WHERE fname = ? AND lname = ?");
        ps.setString(1, fname);
        ps.setString(2, lname);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String Phone = rs.getString("phone");
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String City = rs.getString("city");

            System.out.println("Phone Number: " + Phone);
            System.out.println("Name: " + fName + " " + lName);
            System.out.println("City: " + City);
            System.out.println("----------------------------------------------------------");

        }

        rs.close();
        ps.close();
        con.close();
    }

    public static void findContactByCity(String city) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactManagement", "root",
                "rudra@mysql");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ContactManagement.contact WHERE city = ?");
        ps.setString(1, city);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String Phone = rs.getString("phone");
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String City = rs.getString("city");

            System.out.println("Phone Number: " + Phone);
            System.out.println("Name: " + fName + " " + lName);
            System.out.println("City: " + City);
            System.out.println("----------------------------------------------------------");

        }

        rs.close();
        ps.close();
        con.close();
    }


    //------------------------------------------------Main Function-----------------------------------------------------
    public static void main(String[] args) throws Exception {
        // The functionality is going to be Insert, Update(Options), Delete, Fetch,
        // Search and Exit.

        System.out.println("-----------Welcome to Contact Management System------------");
        System.out.println("Select One Option: \n1. Insert\n2. Update\n3. Delete\n4. Fetch\n5. Find a contact?\n6. Exit");
        int choice = sc.nextInt();

        int flag = 1;
        while (flag == 1) {
            switch (choice) {

                // INSERT VALUES
                case 1:
                    System.out.println("----Insertion is in process----");
                    int again = 1;
                    while (again == 1) {
                        System.out.println("Enter The Phone Number: ");
                        String phone = sc.next();
                        System.out.println("Enter Your First Name: ");
                        String fName = sc.next();
                        System.out.println("Enter Last Name: ");
                        String lName = sc.next();
                        System.out.println("Enter City Name: ");
                        String city = sc.next();

                        try {
                            insertInto(phone, fName, lName, city);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println("Do you want to add more? \n1. Yes\n2. No");
                        again = Integer.parseInt(sc.next());
                        if (again == 1) {
                            again = 1;
                        } else {
                            again = 2;
                        }
                    }
                    break;

                // UPDATE VALUES:
                case 2:
                    System.out.println("What do you want to update? \n1. Phone Number\n2. Name\n3. City");
                    int choice1 = sc.nextInt();

                    int flag1 = 1;
                    while (flag1 == 1) {
                        switch (choice1) {

                            // Updating Phone Number:
                            case 1:
                                System.out.println("Enter The Phone Number: ");
                                String phone1 = sc.next();
                                System.out.println("Enter Your First Name: ");
                                String fName1 = sc.next();
                                System.out.println("Enter Last Name: ");
                                String lName1 = sc.next();
                                System.out.println("Enter City Name: ");
                                String city1 = sc.next();
                                try {
                                    updateInfo(1, phone1, fName1, lName1, city1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 2:
                                System.out.println("Enter The Phone Number: ");
                                String phone2 = sc.next();
                                System.out.println("Enter Your First Name: ");
                                String fName2 = sc.next();
                                System.out.println("Enter Last Name: ");
                                String lName2 = sc.next();
                                System.out.println("Enter City Name: ");
                                String city2 = sc.next();
                                try {
                                    updateInfo(2, phone2, fName2, lName2, city2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 3:
                                System.out.println("Enter The Phone Number: ");
                                String phone3 = sc.next();
                                System.out.println("Enter Your First Name: ");
                                String fName3 = sc.next();
                                System.out.println("Enter Last Name: ");
                                String lName3 = sc.next();
                                System.out.println("Enter City Name: ");
                                String city3 = sc.next();
                                try {
                                    updateInfo(3, phone3, fName3, lName3, city3);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                            default:
                                System.out.println("Entered a wrong choice! Please try again.");
                        }
                        System.out.println("Do you want to update again?\nNote: (Press only 1 or 2)\n1. Yes\n2. No");
                        flag = sc.nextInt();
                        break;
                    }

                    // DELETE A CONTACT:
                case 3:
                    System.out.println("Enter The Phone Number: ");
                    String phone = sc.next();
                    System.out.println("Enter Your First Name: ");
                    String fName = sc.next();
                    System.out.println("Enter Last Name: ");
                    String lName = sc.next();
                    System.out.println("Enter City Name: ");
                    String city = sc.next();

                    try {
                        deleteInfo(phone, fName, lName, city);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                // FETCH:
                case 4:
                    try {
                        displayContact();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                // Find A CONTACT:
                case 5:
                    System.out.println(
                            "Choose only one(Either press 1 or 2 or 3):\n1. Find by Name.\n2. Find by Phone Number\n3. Find by City.");
                    int chooseFind = sc.nextInt();
                    switch (chooseFind) {
                        case 1:
                            System.out.println("--Finding by Name--");
                            System.out.print("Enter First Name: ");
                            String fname = sc.next();
                            System.out.print("Enter Last Name: ");
                            String lname = sc.next();

                            try{
                                findContactByName(fname, lname);
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("--Finding by Phone Number--");
                            System.out.println("Enter The Phone Number: ");
                            String phoneNumber = sc.next();

                            try {
                                findContactByPhone(phoneNumber);
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("--Finding by City Name--");
                            System.out.print("Enter the City Name: ");
                            String cityName = sc.next();

                            findContactByCity(cityName);

                            break;
                        default:
                            System.out.println("Please Enter A Valid Number...");
                    }
                    break;

                // EXIT:
                case 6:
                    System.out.println("You have exited from the program!");
                    flag = -1;
                    break;

                default:
                    System.out.println("Entered a wrong choice! Please try again.");
            }

            System.out.println("----------------------------------------------------------");
            System.out.println("Select One Option: \n1. Insert\n2. Update\n3. Delete\n4. Fetch\n5. Find a contact?\n6. Exit");
            choice = sc.nextInt();
        }
    }
}
