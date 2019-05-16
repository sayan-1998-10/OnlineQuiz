import java.util.Scanner;
public class Main {
	static int decide_to_set_quiz = 1;
	static int decide_to_take_quiz = 1;

//	array of subjects
	public static int numSubjects =2;
	
	public static String[] nameSubjects = new String[numSubjects];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Quiz[] q = new Quiz[numSubjects];
		
//		teacher login essentials
		long TID=1;
		while(TID >500 || TID < 100){
			System.out.println("Teacher:Enter your ID: ");
			TID = sc.nextLong();
			if(TID >500 || TID < 100) {
			System.out.println("<-This ID is not registered.Enter correct ID->");
			}
		}
		System.out.println("<-You are now logged in.->");
		
		while(decide_to_set_quiz != 0) {
			System.out.println("Set up a Quiz?(1/0)");			
			decide_to_set_quiz = sc.nextInt();
			
			if(decide_to_set_quiz == 1) {
				for(int i =0; i<q.length;i++) {
					System.out.println("Subject name:-");
					nameSubjects[i]=sc.next();
					System.out.println("  ");
					q[i] = new Quiz();
					q[i].set_quiz();
				}
			}
		}	
		
//		student login essentials

		long ID=1;
		while(ID >=1001500 || ID <= 1000100){
			System.out.println("Student:Enter your ID: ");
			ID = sc.nextLong();
			if(ID >=1001500 || ID <= 1000100) {
			System.out.println("<-This ID is not registered.Enter correct ID->");
			}
		}
		
		System.out.println("<-You are now logged in->");
		
		while(decide_to_take_quiz!=0) {
			System.out.println("You want to take a Quiz?(1/0)");
			decide_to_take_quiz = sc.nextInt();
			if(decide_to_take_quiz == 1) {
//				single student answering all the quizzes
				System.out.println("Want to take a quiz on which subject?");
				String temp = sc.next();
//				Skip the new Line
				sc.nextLine();
				
				for(int i =0; i<q.length;i++){
					
					if (temp.equals(nameSubjects[i]) ) {
					System.out.println("-----------"+nameSubjects[i]+"-----------");
					Student stud = new Student(q[i]);
					stud.Answer();
					stud.Check();
					}
				}//end of inner for
				
			}//end of outer if 
		}//end of while
		sc.close();
	}	
	
	
}

class Quiz{
	
	Scanner sc = new Scanner(System.in);
	protected static int numberQuestions = 3;
	protected static int numberOptions = 4;
	protected  String[] questions=new String[numberQuestions];
	protected  String[] descript =new String[numberQuestions];
	protected  String[] answers  = new String[numberQuestions];
	protected  String[] options  = new String[numberQuestions*numberOptions];
	
	void set_quiz() {
		int k=0;
		System.out.println("---------------Quiz---------------");
								
		for (int i=0; i<questions.length;i++) {
			
			System.out.println("---Enter Question---");
			questions[i] = sc.nextLine();
			System.out.println("---Enter Answer---");
			answers[i]  = sc.nextLine();
			System.out.println("---Enter Options---");
			for (int j=0; j<numberOptions;j++) {
				options[k] = sc.nextLine();
				k++;
			} 
			System.out.println("Enter description about the above Question:-");
			descript[i]  = sc.nextLine();
		}
	} 
}

class Student extends Quiz{
	
	protected int[] option_index = new int[questions.length];
	Student(Quiz object) {
		questions=object.questions;
		options = object.options;
		answers = object.answers;
		descript= object.descript;
	}
	//taking a Quiz
	void Answer(){
		//picking an option
		System.out.println("-----------------Start Answering!!-----------------");
		int m =0;
		for (int i=0;i<questions.length;i++) {
			System.out.println("Question :-");
			System.out.println(questions[i]);
			System.out.println(" ");

			for(int j =0;j<numberOptions;j++) {
				System.out.println("Option("+j+"): "+options[m]);
				m++;
			}
			System.out.print("Picked Option is :");
			option_index[i] = sc.nextInt();
			System.out.println(" ");
		}
		
	}
	void Check() {
		int marks = 0;	
		
		for (int i=0;i<questions.length;i++) {
			System.out.println(" ");
			
			if(options[option_index[i] +(i*4)].equals(answers[i]) ) {
				
				System.out.println("Question :-");
				System.out.println(questions[i]);
				System.out.println("Correct!!");
				System.out.println("Your Answer="+options[option_index[i] + (i*4)]);
				System.out.println("Correct Answer="+answers[i]);
				System.out.println("Description--> "+descript[i]);
				marks +=1;
				
			}
			else {
				System.out.println("Question :-");
				System.out.println(questions[i]);
				System.out.println("Wrong!!");
				System.out.println("Your Answer="+options[option_index[i]+ (i*4)]);
				System.out.println("Correct Answer="+answers[i]);
				System.out.println("Description--> "+descript[i]);
			}
		}
		System.out.println(" ");
		System.out.println("Marks Attained="+marks);
		System.out.println("Thank You!!");
		System.out.println("  ");
	}

}

