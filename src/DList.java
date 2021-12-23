import java.io.FileNotFoundException;
import java.util.Formatter;

public class DList<T extends Comparable<T>> {

    private BNode<T> first;   // first node in the list
    private BNode<T> mid;     // the middle node in the list
    private BNode<T> last;    // the last node in the list
    private int count;        // the number of list nodes
    private boolean verbose;  // true = verbose on
    private Formatter out;    // output if verbose = true

    //Non-parameterized constructor
    public DList ()
    {
        //Set the first 3 values to null...
        this.first = null;
        this.mid = null;
        this.last = null;
        //Set verbose to false...
        this.verbose = false;
        //Set the count to 1...
        this.count = 1;
        //Set the formatter out ot null...
        this.out = null;
    }

    //Setters and Getters...
    public BNode<T> getFirst()
    {
        return first;
    }
    public void setFirst(BNode<T> first)
    {
        this.first = first;
    }
    public BNode<T> getMid()
    {
        return mid;
    }
    public void setMid(BNode<T> mid)
    {
        this.mid = mid;
    }
    public BNode<T> getLast()
    {
        return last;
    }
    public void setLast(BNode<T> last)
    {
        this.last = last;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int count)
    {
        this.count = count;
    }
    public void setVerbose(boolean verbose)
    {
        try
        {
            out = new Formatter("output.txt");
            System.out.println("File to print verbose output: 'output.txt'");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: FileNotFoundException.");;
        }
        this.verbose = verbose;
    }
    public boolean getVerbose()
    {
        return this.verbose;
    }

    // Sets the new first node of the list...
    public void newFirst(BNode<T> node)
    {
        // If the first node is null
        if (this.first == null)
        {
            // Set the first and last to the passed node
            this.first = node;
            this.last = node;

            // Set the node's next and prev to null
            node.setNextNode(null);
            node.setPrevNode(null);
        }
        // Otherwise
        else
        {
            // Set the given nodes next to the current first node
            node.setNextNode(this.first);

            // Set the first node's previous to the given node
            this.first.setPrevNode(node);

            // First node becomes the given node
            this.first = node;
        }

        // Increment the list count by 1
        this.count++;
        out.format("New first: %s%ncount = %d%n", node.toString(), count);//FIXME

        // Call the set new mid method
        setNewMid();
    }

    //Sets the new last node of the list...
    public void newLast(BNode<T> node)
    {
        // If the first node is null
        if(this.last == null)
        {
            // Set the first and last to the passed node
            this.first = node;
            this.last = node;

            // Set the node's next and prev to null
            node.setNextNode(null);
            node.setPrevNode(null);
        }

        // Otherwise
        else
        {
            // Set the given nodes prev to the current last node
            node.setPrevNode(this.last);

            // Set the last node's next to the given node
            this.last.setNextNode(node);

            // Last node becomes the given node
            this.last = node;
        }

        // Increment the list count by 1
        this.count++;
        out.format("New first: %s%ncount = %d%n", node.toString(), count);//FIXME

        // Call the set new mid method
        setNewMid();
    }

    // Inserts a new node at the end of the list...
    public void newInside(BNode<T> node)
    {
        // Create an auxillary node pass it the middle
        BNode<T> aux = this.mid;

        //Increment the counter...
        this.count++;

        out.format("New in middle: new %s compare to mid %s%n", node.toString(), aux.toString());
        out.format("count = %d", count);

        // If the compare two is positive...
        if (node.compareTo(aux) > 0)
        {
            //Look to the right...
            while (node.compareTo(aux) > 0)
            {
                aux = aux.getNextNode();
            }
            // Create a temp node given the aux's previous...
            BNode<T> temp = aux.getPrevNode();
            // Insert the given node between the temp and the aux...
            insertBetw(node, temp, aux);
        }
        //Otherwise, is less than or equal to...
        else {
            //Look left of the mid...
            while (node.compareTo(aux) < 0)
            {
                aux = aux.getPrevNode();
            }

            //Create a temporary node given the aux's next...
            BNode<T> temp = aux.getNextNode();
            //Insert given between aux and temp...
            insertBetw(node, aux, temp);
        }
        out.format("");
        setNewMid();//Set the new middle node.
    }
    // Method that sets the new middle node...
    public void setNewMid()
    {
        // Create an aux of the first node...
        BNode<T> aux = this.first;
        int newMid = 0; //A new mid to store the calculation.
        //If the count is even...
        if (this.count % 2 == 0)
        {
            newMid = (this.count / 2);
        }
        //Otherwise count is odd...
        else
        {
            newMid = (this.count / 2) + 1;
        }
        //Find the appropriate location in the list...
        for(int i = 1; i < newMid; i++)
        {
            aux = aux.getNextNode();
        }

        //Set the middle to aux...
        this.mid = aux;
        out.format("New mid (@%d) = %d", newMid, this.mid.getData());//FIXME: Debug statement...
    }

    //Method that inserts a given node between a left and right node...
    public void insertBetw(BNode<T> node, BNode<T> leftNode, BNode<T> rightNode)
    {
        // Insert the new node to the right of the left node
        leftNode.setNextNode(node);
        node.setPrevNode(leftNode);

        // Insert the new node to the left of the right node
        rightNode.setPrevNode(node);
        node.setNextNode(rightNode);
        out.format("\nInsert %d between %d and %d.\n", node.getData(), leftNode.getData(), rightNode.getData());
    }

    //Prints the doubly linked list...
    public void prDList()
    {
        //Create an aux BNode...
        BNode<T> aux = this.getFirst();
        int i = 0;//Declare and initialize a counter.

        //For the verbose option...
        if (verbose)
        {
            out.format("\n%-4s ", aux.getData().toString());
            i++;
            while (aux.getNextNode() != null)
            {
                if (i == 10)
                {
                    out.format("\n");
                    i = 0;
                }
                aux = aux.getNextNode();
                out.format("%-4s ", aux.getData().toString());
                i++;
            }
            out.format("\n\n");
        }
        //Otherwise...
        else
        {
            System.out.printf("\n%-4s ", aux.getData().toString());
            i++;
            while (aux.getNextNode() != null)
            {
                if (i == 10)
                {
                    System.out.printf("\n");
                    i = 0;
                }
                aux = aux.getNextNode();
                System.out.printf("%-4s ", aux.getData().toString());
                i++;
            }
            System.out.printf("\n\n");
        }

    }
    //This method is used to print the final sorted list and close the formatter...
    public void closeList()
    {
        //Print the final list...
        out.format("FINAL: ");
        //Print the sorted list...
        this.prDList();
        //Close the formatter...
        out.close();
    }
    //This method prints the integer currently being read from the file...
    public void printNext(int next){
        //Print next read...
        out.format("Next read: %d", next);
        out.format("\n");
    }
    //This method prints the first integer passed into the file...
    public void printFirst(int num) {
        //Print the first read...
        out.format("First read: %d\nNew first: %d\ncount = %d\nNew mid (@1) = %d", num,
                num, this.getCount(), num);
        prDList(); //Print the current list.
    }
}