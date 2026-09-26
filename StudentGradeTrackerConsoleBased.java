import java.util.Scanner;

public class StudentGradeTrackerConsoleBased {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//Student Information
        String name;
        do {
            System.out.print("Enter student name: ");
            name=sc.nextLine();
            if (name.isEmpty()){
                System.out.println("Student name cannot be empty!");
            }
        }while (name.isEmpty());

        int number;
        do {
            System.out.print("Enter number of subjects: ");
            number=sc.nextInt();
            if (number<1){
                System.out.println("Invalid number of subjects!");
            }
        }while (number<1);

//        Subject Marks
        int[] mark=new int[number];

        int total=0;

        for (int i=0;i<number;i++){
            do {

                System.out.print("Enter mark for Subject "+(i+1)+": ");
                mark[i]=sc.nextInt();
                if (mark[i]<0||mark[i]>100){
                    System.out.println("Invalid mark! Enter a mark between 0 and 100.");
                }

            }while (mark[i]<0||mark[i]>100);

            total+=mark[i];
        }

//Display Result
        System.out.println("\n--- Student Result ---");
        System.out.println("Student Name: "+name);

        System.out.println("Number of Subjects: "+number+"\n");

        for (int i=0;i<number;i++){
            System.out.println("Subject "+(i+1)+": "+mark[i]);
        }

        System.out.println("\nTotal Marks: "+total);

//        Calculate Average
        double average=total/(double)number;
        System.out.printf("Average Mark: %.2f%n",average);

//        Grade and Status
        if (average >= 90){
            System.out.println("Grade: A");
        } else if (average >= 80) {
            System.out.println("Grade: B");
        } else if (average >= 70) {
            System.out.println("Grade: C");
        } else if (average >= 60) {
            System.out.println("Grade: D");
        }else {
            System.out.println("Grade: F");
        }

        if(average>=60){
            System.out.println("Status: PASS");
        }else {
            System.out.println("Status: FAIL");
        }

//        Find Highest and Lowest Marks
        int highest = mark[0];
        for (int i = 1;i<number;i++){
            if(mark[i]>highest){
                highest=mark[i];
            }
        }
        int lowest = mark[0];
        for (int i=1;i<number;i++){
            if (mark[i]<lowest){
                lowest=mark[i];
            }
        }

        System.out.println("Highest Mark: "+highest);
        System.out.print("Highest Mark Subject: ");
        boolean firstHighest=true;
        for (int i = 0;i<number;i++){
            if(mark[i]==highest){
                if (!firstHighest){
                    System.out.print(", ");
                }
                System.out.print("Subject "+(i+1));
                firstHighest=false;
            }
        }
        System.out.println();
        System.out.println("Lowest Mark: "+lowest);
        System.out.print("Lowest Mark Subject: ");
        boolean firstLowest=true;
        for (int i = 0; i < number; i++) {
            if (mark[i]==lowest){
                if (!firstLowest){
                    System.out.print(", ");
                }
                System.out.print("Subject "+(i+1));
                firstLowest=false;
            }
        }
        System.out.println();

//        Count Passed and Failed Subjects
        int passed=0;
        int failed=0;

        for (int i=0;i<number;i++){
            if (mark[i]>=60){
                passed++;
            }else {
                failed++;
            }
        }
        System.out.println("Passed Subjects: "+passed);
        System.out.println("Failed Subjects: "+failed);

    }
}
