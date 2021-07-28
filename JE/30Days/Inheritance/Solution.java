import java.util.*;

class Person {
	protected String firstName;
	protected String lastName;
	protected int idNumber;
	
	// Constructor
	Person(String firstName, String lastName, int identification){
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = identification;
	}
	
	// Print person data
	public void printPerson(){
		 System.out.println(
				"Name: " + lastName + ", " + firstName 
			+ 	"\nID: " + idNumber); 
	}
	 
}

class Student extends Person{
	private int[] testScores;

    /*	
    *   Class Constructor
    *   
    *   @param firstName - A string denoting the Person's first name.
    *   @param lastName - A string denoting the Person's last name.
    *   @param id - An integer denoting the Person's ID number.
    *   @param scores - An array of integers denoting the Person's test scores.
    */
    // Write your constructor here
    Student(String firstName, String lastName, int identification,int[] scores){
        super(firstName, lastName, identification);
        testScores=scores;
    }

    /*	
    *   Method Name: calculate
    *   @return A character denoting the grade.
    */
    // Write your method here
    private double average(){
        double ave = 0;
        for(int score: testScores){
            ave+=score;
        }
        return ave/testScores.length;
    }
    private boolean inRange(double average,int lower,int max){
        return average>= lower && average<max;
    }
    public char calculate(){
        char ret=' ';
        double ave = average();
        if(ave>=90&&ave<=100) ret = 'O';
        else if(inRange(ave, 80, 90)) ret = 'E';
        else if(inRange(ave, 70, 80)) ret = 'A';
        else if(inRange(ave, 55, 70)) ret = 'P';
        else if(inRange(ave, 40, 55)) ret = 'D';
        else if(ave<40) ret = 'T';
        return ret;
    }
}

class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String firstName = scan.next();
		String lastName = scan.next();
		int id = scan.nextInt();
		int numScores = scan.nextInt();
		int[] testScores = new int[numScores];
		for(int i = 0; i < numScores; i++){
			testScores[i] = scan.nextInt();
		}
		scan.close();
		
		Student s = new Student(firstName, lastName, id, testScores);
		s.printPerson();
		System.out.println("Grade: " + s.calculate() );
	}
}