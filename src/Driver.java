import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/** This program creates a Doubly Linked List of Integers from the generic DList
 * and BNode classes. It reads these Integers from a file and enters them in
 * ascending order to the list giving the option to print the results to the screen
 * or to an output file.
 *
 * @author Quenten Calvano, Zachary Morehouse, Arol Andino
 * @version 12/9/2020
 */

public class Driver {

    private static Scanner input = new Scanner(System.in); //The scanner used to accept user input.
    private static Scanner inputFromFile; //The scanner used to read input from the file.
    private static String fileName; //The String which accepts a user specified file name.
    private static File file; //The file created from the user's filename.
    private static FileInputStream fileIn; //The fileInputStream used to read from the file.
    private static String verbose; //The string value of the verbose option chosen by the user.

    public static void main(String[] args)
    {

        //This is where we take the file...
        System.out.println("Enter the input file name: ");
        fileName = input.nextLine();

        //Once the dList is created, Prompt the user for the
        //verbose option and set the result accordingly...
        System.out.println("Do you want the verbose option (y or n)?");
        verbose = String.valueOf(input.next().toLowerCase().charAt(0));

        //Insert the set instantiation here...
        DList<Integer> dList = new DList<>();

        //Attempt to read the file...
        try
        {
            //Sets the file to the filename...
            file = new File(fileName);
            //Sets the FileInputStream to read the file...
            fileIn = new FileInputStream(file);
            //Uses the Scanner to read the FileInputStream...
            inputFromFile = new Scanner(fileIn);

            //Set verbose...
            if (verbose.equals("y"))
            {
                dList.setVerbose(true);
            }
            else if (verbose.equals("n"))
            {
                dList.setVerbose(false);
            }

            //Create new aux for the first node...
            BNode<Integer> aux = new BNode<>(Integer.parseInt(inputFromFile.next()),
                    null, null);
            dList.setFirst(aux);//Make aux the first in the list.
            dList.setMid(aux);//Make aux the mid in the list.
            dList.setLast(aux);//Make aux the last in the list.

            if (verbose.equals("y")) {
                //Create a variable to hold the int value read first...
                int firstRead = aux.getData();
                //Print the first number read to the file...
                dList.printFirst(firstRead);
            }

            //Read the rest from the file and compare for where to place each...
            while (inputFromFile.hasNext())
            {
                //Set the aux to the nextBNode object...
                aux = new BNode<>(Integer.parseInt(inputFromFile.next()), null,
                        null);

                //Save the next number to print to the output file...
                int nextInt = aux.getData();
                //Print the number currently being read...
                if (dList.getVerbose()) {
                    //Print next integer...
                    dList.printNext(nextInt);
                }
                //Format print the value of the next number...
                //System.out.printf("Next read: %d\n", aux.getData());//FIXME

                //If the new node comes before...
                if (aux.compareTo(dList.getFirst()) < 0)
                {
                    dList.newFirst(aux);//If less than first, set new first.
                }
                else if (aux.compareTo(dList.getLast()) > 0)
                {
                    dList.newLast(aux);//If greater than last, set new last.
                }
                else
                {
                    dList.newInside(aux);//Else, set new middle.
                }
                if (dList.getVerbose()) {
                    //Print the list...
                    dList.prDList();
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERROR: NullPointerException.");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFoundException");
        } finally
        {
            try
            {
                fileIn.close();//Close the FileInputStream.
                if (dList.getVerbose()) {
                    dList.closeList(); //Close the formatter...
                }
                else {
                    //Print the final list...
                    System.out.println("\nFINAL: ");
                    dList.prDList();
                }
            }
            catch (IOException e)
            {
                System.out.println("ERROR: IOException.");
            }
            catch (NullPointerException e) {
                System.out.println("ERROR: NullPointerException.");
            }
        }
    }
}
