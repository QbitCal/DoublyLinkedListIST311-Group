public class BNode<T extends Comparable<T>> {

    private T data; // node data MUST be an object reference
    private BNode<T> nextNode; // next node or null
    private BNode<T> prevNode; // previous node or null

    //Three argument constructor...
    public BNode (T data, BNode<T> nextNode, BNode<T> prevNode)
    {
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    //Getters and Setters...
    public T getData()
    {
        return data;
    }
    public BNode<T> getPrevNode()
    {
        return prevNode;
    }
    public void setPrevNode(BNode<T> prevNode)
    {
        this.prevNode = prevNode;
    }
    public BNode<T> getNextNode()
    {
        return nextNode;
    }
    public void setNextNode(BNode<T> nextNode)
    {
        this.nextNode = nextNode;
    }

    //The compareTo method compares BNode<T> t's compareTo value to the current BNode object
    //and returns a -1 if the current object is less than t's value, 0 if it is the same, and positive if
    //it is greater in value...
    public int compareTo(BNode<T> t)
    {
        //Create a node to compare...
        T data1 = this.getData();
        //Create a second node to compare...
        T data2 = t.getData();
        return data1.compareTo(data2);
    } // end compareTo

    //This method is used to print all of the parameters of the BNode Object it is instantiated on.
    //The values are stored in a single string in the order "currentNode nextNode prevNode"...
     public String toString(){
        //Declare a string to hold the data...
        String output;
        output = this.data.toString();
        return output;
     }

}
