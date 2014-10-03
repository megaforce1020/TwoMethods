/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package readwriteio_twomethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author VR
 * @work UofUtah
 */
public class ReadWriteIO_twomethods {

    /**
     * @param args the command line arguments
     */
    
    /* In the previous program ReadWriteIO_onemethod, we wrote
            all the code in the main() method.
                        
            this is NOT good programming style. Good style
            includes modular deisgn, which means you should 
            try to break up your code into modules
            modules are methods which contain a block of code 
            that does one (or few) very specific tasks
    
            in this example, I will show you how to create a new method
            to help write a string out to a file. instead of writing this code 
            in the main() method, we place it in its own method called
            writeToFile(). this method is written after the ending } of 
            the main() method     
    */
    public static void main(String[] args)
            throws IOException, InputMismatchException {
        // TODO code application logic here

        Scanner console = new Scanner(System.in);
        int number, error=0, result, stop;
        
        String read ="", strToWrite="";

        String name = "", reply = "";
        String titles = null;

        StringBuilder stringToSave = new StringBuilder("");

        do {
            System.out.println("Please enter the score "
                    + "that you'd like to store (-1 to stop)");

            try {
                error=0;
                number = console.nextInt();
                if (number != -1) {
                    System.out.println("You entered: " + number);
                    System.out.println("Please enter name: ");
                    console.nextLine();
                    name = console.nextLine();
                    System.out.println("Your name is:" + name);
                    
                    stringToSave.append(name).append("\t").append(number).append("\n");
                    strToWrite = stringToSave.toString();
                    
                    /*************WRITING TO FILE USING A NEW METHOD ***********************/
                    /*
                                        everything else in the code is the same as the other file
                                        the only difference is we are creating a new method to do the 
                                        task of writing a line of text to file.
                                        
                                        //METHOD CALL
                                        we execute the method, by calling it by its name
                                        this method is named writeToFile
                                        we have set it up to accept one String input - which is the line of text to write
                    
                                        we therefore send strToWrite to the writeToFile() method
                                        we do so by passing only the name of the String variable 
                                        in the parentheses of the method call
                                        strToWrite is called an "argument" - a variable that is an input to a method
                                        */
                    boolean answer = writeToFile(strToWrite);
                    
                    System.out.println("The error is " +answer);
                    
                    BufferedReader inputBuff = new BufferedReader
                                        (new FileReader("outfile.txt"));
        
                    
                    while ((read = inputBuff.readLine())!=null) {
                        System.out.println("Entry from file:" + read);
                    }
                    
                } else {
                    System.out.println("Goodbye");
                }
            } 
            catch (InputMismatchException ime) {
                
                error = -1;
                System.err.println("You did not enter a number.");
                console.nextLine();
            }
            catch (FileNotFoundException fnf) {
                System.err.println("sorry there was an error in finding the file");
            }
            catch (IOException ioe) {
                System.err.println("sorry there was an error in finding the file");
            }
            

        }while(error==-1);
                
    }

    /*
        this is the new method
        notice carefully how we create this method
        always write public static - we will talk more about this later
        void means this method does not return an answer
        if you want to send back an answer such as an int, you can define the method like this instead
        notice that if return type if not void
        you always have to to return an appropriate answer by using the return command followed
        by the a variable or data of the same datatype as the return type
    
    
        public static int writeToFile (String str2)  throws IOException {
    
        int someInt=0;
        return someInt;
        }
        
        we define this method to throw IOException.
        this means this method will throw the exception to the method that called it to execute
        so writeToFile throws the exception to the main() method in this class
        and the main() method handles it because it has a catch{} block for some IOException
        such as FileNotFoundException
    
        Recall that methods have to have their own set of variables
        any variable or object defined in main() is not accessible or usable in this new method
    
        so when the main() method passes it or sends it a string to write out to file
        this new method should store that string in its own String variable
        you can call this any name you want - I called it str2
        this is called PASSING PARAMETERS
        str2 is a parameter of the writeToFile() method
        "parameters" are like inputs that are accepted to the method
        whereas "arguments" are inputs sent to a method
        
        */
    public static boolean writeToFile (String strToWrite)  throws IOException{
        boolean writeStatus = false;
        try {
            BufferedWriter outputBuff = new BufferedWriter(
                                        new FileWriter("outfile.txt", true));
            outputBuff.write(strToWrite);
            outputBuff.flush();
        }
        catch(IOException ioe2){
            writeStatus = false;
            System.err.println("IO exception occurred while trying to write to file ");
        }
        
        return writeStatus;
    }
}
