import java.util.Stack;

public class ShiftReduceParser {
    
    // Define the grammar rules
    private static final String[][] RULES = {
        {"E", "E + T"},
        {"E", "T"},
        {"T", "T * F"},
        {"T", "F"},
        {"F", "( E )"},
        {"F", "i"}
    };
    
    public static boolean parse(String input) {
        Stack<String> stack = new Stack<>();
        stack.push("0");
        
        int i = 0;
        while (i < input.length()) {
            String state = stack.peek();
            char symbol = input.charAt(i);
            String action = getAction(state, symbol);
            
            if (action == null) {
                return false; // Invalid input
            }
            System.out.println(action);
            if (action.startsWith("S")) {
                // Shift the symbol onto the stack
                stack.push(Character.toString(symbol));
                stack.push(action.substring(1));
                i++;
            } else if (action.startsWith("R")) {
	                // Reduce the symbols on the stack according to the grammar rule
	                int ruleIndex = Integer.parseInt(action.substring(1)) - 1;
	                String[] rule = RULES[ruleIndex];
	
	                for (int j = 0; j < rule[1].length() * 2; j++) {
	                    stack.pop();
	
	                }
	                String newState = getGoto(stack.peek(), rule[0]);
	                if (newState == null) {
	                    return false; // Invalid state transition
	                }
	                stack.push(rule[0]);
	                stack.push(newState);
	                i++;
            } else if (action.equals("A")) {
                // Accept the input
                return true;
            }
        }
        
        return false; // Invalid input
    }

    
    // Get the action to take based on the current state and symbol
    private static String getAction(String state, char symbol) {
        // This is just an example - the actual table would be much larger
        if (state.equals("0") && symbol == 'i') {
            return "S5";
        } else if (state.equals("0") && symbol == '(') {
            return "S4";
       // } else if (state.equals("0") && symbol == 'E') {
         //   return "1";
        } else if (state.equals("0") && symbol == '2') {
            return "2";
     //   } else if (state.equals("0") && symbol == 'F') {
      //      return "3";
        } else if (state.equals("1") && symbol == '+') {
            return "S6";
        } else if (state.equals("1") && symbol == '$') {
            return "A";
        } else if (state.equals("2") && symbol == '+') {
            return "R2";
        } else if (state.equals("2") && symbol == '+') {
            return "R2";
        } else if (state.equals("2") && symbol == '*') {
            return "S7";
        } else if (state.equals("2") && symbol == ')') {
            return "R2";
        } else if (state.equals("2") && symbol == '$') {
            return "R2";
        } else if (state.equals("3") && symbol == '+') {
            return "R4";
        } else if (state.equals("3") && symbol == '*') {
            return "R4";
        } else if (state.equals("3") && symbol == ')') {
            return "R4";
        } else if (state.equals("3") && symbol == '$') {
            return "R4";
        } else if (state.equals("4") && symbol == 'i') {
            return "S5";
        } else if (state.equals("4") && symbol == '(') {
            return "S4";
  //      } else if (state.equals("4") && symbol == 'E') {
    //        return "8";
   //     } else if (state.equals("4") && symbol == 'T') {
     //       return "2";
  //      } else if (state.equals("4") && symbol == 'F') {
  //          return "3";
        } else if (state.equals("5") && symbol == '+') {
            return "R6";
        } else if (state.equals("5") && symbol == '*') {
            return "R6";
        } else if (state.equals("5") && symbol == ')') {
            return "R6";
        } else if (state.equals("5") && symbol == '$') {
            return "R6";
        } else if (state.equals("6") && symbol == 'i') {
            return "S5";
        } else if (state.equals("6") && symbol == '(') {
            return "S4";
//        } else if (state.equals("6") && symbol == 'T') {
  //          return "9";
///        } else if (state.equals("6") && symbol == 'F') {
   //         return "3";
        } else if (state.equals("7") && symbol == 'i') {
            return "S5";
        } else if (state.equals("7") && symbol == '(') {
            return "S4";
 //       } else if (state.equals("7") && symbol == 'F') {
 //           return "10";
        } else if (state.equals("8") && symbol == '+') {
            return "S6";
        } else if (state.equals("8") && symbol == ')') {
            return "S11";
        } else if (state.equals("9") && symbol == '+') {
            return "R1";
        } else if (state.equals("9") && symbol == '*') {
            return "S7";
        } else if (state.equals("9") && symbol == ')') {
            return "R1";
        } else if (state.equals("9") && symbol == '$') {
            return "R1";
        } else if (state.equals("10") && symbol == '+') {
            return "R3";
        } else if (state.equals("10") && symbol == '*') {
            return "R3";
        } else if (state.equals("10") && symbol == ')') {
            return "R3";
        } else if (state.equals("10") && symbol == '$') {
            return "R3";
        } else if (state.equals("11") && symbol == '+') {
            return "R5";
        } else if (state.equals("11") && symbol == '*') {
            return "R5";
        } else if (state.equals("11") && symbol == ')') {
            return "R5";
        } else if (state.equals("11") && symbol == '$') {
            return "R5";
        } else {
            throw new IllegalArgumentException("Invalid action for state " + state + " and symbol " + symbol);
        }
    }
 
        
    private static String getGoto(String state, String symbol) {
        // This is just an example - the actual table would be much larger
        switch (state) {
            case "0":
                switch (symbol) {
                    case "E":
                        return "1";
                    case "T":
                        return "2";
                    case "F":
                        return "3";
                    default:
                        return null;
                }
            case "4":
                switch (symbol) {
                    case "E":
                        return "8";
                    case "T":
                        return "2";
                    case "F":
                        return "3";
                    default:
                        return null;
                }
            case "6":
                switch (symbol) {
                    case "T":
                        return "9";
                    case "F":
                        return "3";
                    default:
                        return null;
                }
            case "7":
                switch (symbol) {
                    case "F":
                        return "10";
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

        
        // Test the parser
        public static void main(String[] args) {
            String input = "i+i*i";
            boolean result = parse(input);
            System.out.println("Parsing " + input + ": " + result);
            System.out.println("here");
        }
    }
