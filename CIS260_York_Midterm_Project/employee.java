import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class employee {
    public static void menu() throws Exception {

        // USER PROMPT
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of employees to entered: ");
        int numOfEmployees = input.nextInt();

        // Employee Details
        ArrayList<String> employeeName = new ArrayList<String>(numOfEmployees);
        ArrayList<Integer> employeeID = new ArrayList<Integer>(numOfEmployees);
        ArrayList<Double> employeeSalary = new ArrayList<Double>(numOfEmployees);

        int i = 0;
        while (i < numOfEmployees) {
            // Employee Name Prompt
            System.out.println("employee " + (i + 1) + ": Enter the employee's name: ");
                employeeName.add(input.next());

            // Employee ID Prompt
            System.out.println("employee " + (i + 1) + ": Enter the employee's ID number (enter twice to verify): ");
            if ((int) (Math.log10(input.nextInt()) + 1) == 5) {
            employeeID.add(input.nextInt());
            }
            else {
                throw new Exception("Please enter a valid ID number.");
            }
            

            // Employee Salary Prompt
            System.out.println("employee " + (i + 1) + ": Enter the employee's salary (per year): ");
                employeeSalary.add(input.nextDouble());
                i++;
        }

        boolean continueRunning = true;
        do {
            System.out.println("\nPlease select one of the following: " +
                        "\nPress 1: Add employee" + 
                        "\nPress 2: Remove employee" + 
                        "\nPress 3: Display all employees" + 
                        "\nPress 4: Retrieve employee data from ID" +
                        "\nPress 5: Retrieve within a salary range" + 
                        "\nPress 6: Sort employees by salary (highest to lowest)" + 
                        "\nPress 7: Sort employees by name (alphebetical order)" + 
                        "\nPress 8: Sort employees by ID number (ascending order)" +
                        "\nPress 9: Clear employee data" +
                        "\nPress 0: Exit program" + 
                        "\n");

            System.out.print("input: ");
            int userSelection = input.nextInt();
            switch (userSelection) {
                    
            // ----------------------------------------- ADD EMPLOYEE ----------------------------------------- //
            case 1:
                System.out.println("How many employees would you like to add?");
                int newEmployeeCount = input.nextInt();
                numOfEmployees += newEmployeeCount;
                i = 0; 
                while (i < newEmployeeCount) {
                    System.out.println("employee " + i + 1 + ": Enter the employee's name: ");
                        employeeName.add(input.next());
        
                    System.out.println("employee " + i + 1 + ": Enter the employee's ID: "); // Must be 5 digits
                        employeeID.add(input.nextInt());
        
                    System.out.println("employee " + i + 1 + ": Enter the employee's salary (per year): ");
                        employeeSalary.add(input.nextDouble());
                        i++;
                    }
                break;

            // ---------------------------------------- REMOVE EMPLOYEE --------------------------------------- //
            case 2:
                System.out.println("Who would you like to remove?\n");
                for (int j = 0; j < numOfEmployees; j++) {
                    System.out.println(j + ": " + employeeName.get(j));
                }
                System.out.print("\nEnter the coresponding number: ");
                int number = input.nextInt();
                employeeName.remove(number);
                employeeID.remove(number);
                employeeSalary.remove(number);
                System.out.print(Color.RED);
                System.out.print("Employee Removed");
                System.out.print(Color.RESET);
                numOfEmployees--;
                break;

            // --------------------------------------- DISPLAY EMPLOYEES -------------------------------------- //
            case 3:
            try {
                System.out.println("Total number of employees: " + numOfEmployees);
                for (int j = 0; j < numOfEmployees; j++) {
                    System.out.println("Name: " + employeeName.get(j));
                    System.out.println("ID: " + employeeID.get(j));
                    System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                        "en", "US")).format(employeeSalary.get(j)) + "\n");
                }
                } catch (Exception e) {
                    // do nothing
                }
                break;

            // ------------------------------------- RETRIEVE DATA FROM ID ------------------------------------ //
            case 4:
            int counter = 0;
                System.out.println("Enter an employee's ID number: ");
                int ID = input.nextInt();
                for (int j = 0; j < numOfEmployees; j++) {
                    if (employeeID.get(j) == ID) {
                        System.out.println("Name: " + employeeName.get(j));
                        System.out.println("ID: " + employeeID.get(j));
                        System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                        "en", "US")).format(employeeSalary.get(j)) + "\n");
                        break;
                    }
                    else {
                        counter++;
                        continue;
                    }
                }
                if (counter >= numOfEmployees) {
                    System.out.println("No match.");
                }
                break;

            // ----------------------------------- RETRIEVE FROM SALARY RANGE --------------------------------- //
            case 5:
                System.out.println("Enter a salary range...");
                System.out.print("Minimum salary: ");
                double minSalary = input.nextDouble();
                System.out.print("\nMaximum salary: ");
                double maxSalary = input.nextDouble();

                counter = 0;
                for (int j = 0; j < numOfEmployees; j++) {
                    if (employeeSalary.get(j) > minSalary && employeeSalary.get(j) < maxSalary) {
                        System.out.println("Name: " + employeeName.get(j));
                        System.out.println("ID: " + employeeID.get(j));
                        System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                        "en", "US")).format(employeeSalary.get(j)) + "\n");
                    }
                    else {
                        counter++;
                        continue;
                    }
                }
                if (counter >= numOfEmployees) {
                    System.out.println("No employee within the specifed range.");
                }
                break;

            // ----------------------------------------- SORT BY SALARY --------------------------------------- //
            case 6:
                ArrayList<String> oldNameList = new ArrayList<>(List.copyOf(employeeName));
                ArrayList<Integer> oldIDList = new ArrayList<>(List.copyOf(employeeID));
                ArrayList<Double> oldSalaryList = new ArrayList<>(List.copyOf(employeeSalary));

                Collections.sort(employeeSalary);

                for (i = 0; i < numOfEmployees; i++) {
                    if (oldSalaryList.get(i) == employeeSalary.get(i)) {
                        continue;
                    } 

                    else {
                        for (int j = 0; j < numOfEmployees; j++) {
                            for (int k = 0; k < numOfEmployees; k++) {
                                if (oldSalaryList.get(j) == employeeSalary.get(k)) {
                                    // turn index of j into index of k if a match is found
                                    employeeID.add(j, oldIDList.get(k));
                                    employeeName.add(j, oldNameList.get(k));
                                }
                            }
                        }
                    }
                }
                for (i = 0; i < numOfEmployees; i++) {
                    System.out.println("Name: " + employeeName.get(i));
                    System.out.println("ID: " + employeeID.get(i));
                    System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                    "en", "US")).format(employeeSalary.get(i)) + "\n");
                }
                break;

            // ------------------------------------------ SORT BY NAME ---------------------------------------- //
            case 7:
                oldNameList = new ArrayList<>(List.copyOf(employeeName));
                oldSalaryList = new ArrayList<>(List.copyOf(employeeSalary));
                oldIDList = new ArrayList<>(List.copyOf(employeeID));

                Collections.sort(employeeName);

                for (i = 0; i < numOfEmployees; i++) {
                    if (oldNameList.get(i) == employeeName.get(i)) {
                        continue;
                    } 

                    else {
                        for (int j = 0; j < numOfEmployees; j++) {
                            for (int k = 0; k < numOfEmployees; k++) {
                                if (oldNameList.get(j) == employeeName.get(k)) {
                                    // turn index of j into index of k if a match is found
                                    employeeID.add(j, oldIDList.get(k));
                                    employeeSalary.add(j, oldSalaryList.get(k));
                                }
                            }
                        }
                    }
                }
                for (i = 0; i < numOfEmployees; i++) {
                    System.out.println("Name: " + employeeName.get(i));
                    System.out.println("ID: " + employeeID.get(i));
                    System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                    "en", "US")).format(employeeSalary.get(i)) + "\n");
                }
                break;

            // ---------------------------------------- SORT BY ID NUMBER ------------------------------------- //
            case 8:
                oldNameList = new ArrayList<>(List.copyOf(employeeName));
                oldSalaryList = new ArrayList<>(List.copyOf(employeeSalary));
                oldIDList = new ArrayList<>(List.copyOf(employeeID));

                Collections.sort(employeeID);

                for (i = 0; i < numOfEmployees; i++) {
                    if (oldIDList.get(i) == employeeID.get(i)) {
                        continue;
                    } 

                    else {
                        for (int j = 0; j < numOfEmployees; j++) {
                            for (int k = 0; k < numOfEmployees; k++) {
                                if (oldIDList.get(j) == employeeID.get(k)) {
                                    // turn index of j into index of k if a match is found
                                    employeeName.add(j, oldNameList.get(k));
                                    employeeSalary.add(j, oldSalaryList.get(k));
                                }
                            }
                        }
                    }
                }
                for (i = 0; i < numOfEmployees; i++) {
                    System.out.println("Name: " + employeeName.get(i));
                    System.out.println("ID: " + employeeID.get(i));
                    System.out.println("Salary: " + NumberFormat.getCurrencyInstance(new Locale(
                    "en", "US")).format(employeeSalary.get(i)) + "\n");
                }
                break;

            // --------------------------------------- CLEAR EMPLOYEE DATA ------------------------------------ //
            case 9:
                System.out.println("Clearing all employee data...");
                employeeName.clear();
                employeeID.clear();
                employeeSalary.clear();
                System.out.print(Color.RED);
                System.out.println("All employee data cleared.");
                System.out.print(Color.RESET);
                break;

            // ------------------------------------------ EXIT PROGRAM ---------------------------------------- //
            case 0:
                System.out.print(Color.GREEN_BRIGHT);
                System.out.println("\nFINISHED\n");
                System.out.print(Color.RESET);
                continueRunning = false;
                break;

            default:
                System.out.print(Color.RED_BOLD_BRIGHT);
                System.out.println("Invalid Entry");
                System.out.print(Color.RESET);
                break;
            }
        } while (continueRunning == true);
    }
}
