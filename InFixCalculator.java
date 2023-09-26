//Time and Space complexity at the bottom
//Test cases are at the bottom
import java.util.Stack;
public class InFixCalculator{
    public static int solve(String str){
       
    	char[] chr = str.toCharArray(); //Converts the string to a sequence of characters
 
        Stack<Integer> operands = new Stack<Integer>();//stack for operands eg) 1,2,3 and etc
        Stack<Character> operators = new Stack<Character>();//stack for operators eg) +,-,*,/
 
        for (int i = 0; i < chr.length; i++){ //Using a for loop to go through each of the characters 
        	
        	if (chr[i] >= '0' && chr[i] <= '9') {  //using an if statement to check if its operand and push it to the stack
                StringBuffer sbuf = new StringBuffer();
               
                while (i < chr.length && chr[i] >= '0' && chr[i] <= '9') //if there is more than one digit in the number
                    sbuf.append(chr[i++]);
                
                operands.push(Integer.parseInt(sbuf.toString()));
                
                i--; //corrects the position since its in a for loop, it will skip a character
        	
        	}else if (chr[i] == '(') // if chr is (
                
        		operators.push(chr[i]); //push it to the operators stack
        	
        	else if (chr[i] == ')') { //if chr is )
               
        		while (operators.peek() != '(') //while loop if the first element in the stack is (
                  
                	operands.push(calc(operators.pop(),operands.pop(),operands.pop())); //calling the calc method to calculate and push into the stack
                
                operators.pop(); //popping out operators
        	}else if (chr[i] == '+' ||chr[i] == '-' || chr[i] == '*' ||chr[i] == '/') { // if its an operator
        	
        	while (!operators.empty() && Precedence(chr[i],operators.peek())) //if the stack is not empty and if the first element on the top of the operator stack has a great precedence or not then
                  
        		operands.push(calc(operators.pop(),operands.pop(),operands.pop()));//then we pop the operator annd operands. Call the calc method and calculate then push the result in the operands stack
        	operators.push(chr[i]);
        	}
        }
        // if there is anything left in the operators stack
         while (!operators.empty()) //the stack in not empty
        	 operands.push(calc(operators.pop(),operands.pop(),operands.pop())); // we perform the calc again
         return operands.pop(); // then pop it out
    }
    public static boolean Precedence(char op1, char op2) { //method to check precedence
        if (op2 == '(' || op2 == ')')
            return false; 
        if ((op1 == '*' || op1 == '/') &&
            (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static int calc(char operator,int num1,int num2) { //method to calc 
    
    	if(operator=='+') { //for + operator
			return num1+num2;
		}else if(operator=='-') {//for - operator
			return num2-num1;
		}else if(operator=='*'){//for * operator
			return num1*num2;
		}else {
			return num2/num1; //for / operator
		}
    }
    public static void main(String[] args){
    	String eq=("10 * 2 - 15");// initializing the equation
    	eq = eq.replaceAll("\\s", ""); //using the replaceall method to get rid of the space between the operators/operands
    	
        System.out.println(solve(eq)); // calling the solve method to solve the postfix equation	
        /*Time Complexity: O(n)
		 * Space Complexity:O(2n) (there are two data structures, 2 stacks used )
		 */
        
        /*Test Cases 
         * In Calc method, if num1 is 0 for division:
         * if(num1==0){
         * System.out.println("Enter valid num);
         * }
         * else{
         * return num2/num1
         * }
         * 
         * Could implement scanner for user input in main method:
         * import java.util.*;
         * 
         * Scanner Sc = new Scanner(System.in);
		 * System.out.println("Enter in a InFix expression: ");
		 * eq = sc.nextLine();
         * 
         * */
    }
}
