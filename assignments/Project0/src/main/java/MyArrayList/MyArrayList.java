package MyArrayList;

public class MyArrayList<T> implements MyList <T>{
    private int startSize=5;
    private int sizeMax;//size of the array
    private Object[] arrayList, temp;
    private int size=0;//size of elements in array

    //make the array of length 5 and max size of 5
    public MyArrayList(){
        arrayList=new Object[startSize];
        sizeMax=startSize;
    }

    //make the initial array of a certain size
    public MyArrayList(int size){
        arrayList=new Object[size];
        sizeMax=size;
    }

    //return the size of the array
    @Override
    public int size()
    {
        return size;
    }

    //add an object to the arraylist, if it's full, make the array bigger first
    @Override
    public void add(T t)
    {
        if(size==sizeMax)
            growArray();
        arrayList[size] = t;

        size++;
    }

    //same as add but in a specific index
    @Override
    public void add(T t, int i)
    {
        if(size==sizeMax)
            growArray();
        temp = new Object[sizeMax];
        int tI=0;
        size++;
        for(int aI=0; aI<sizeMax; aI++)
        {
            if(aI==i)
            {
                temp[tI]=t;
                tI++;
            }
            temp[tI] = arrayList[aI];
            tI++;
        }
        arrayList=temp.clone();

    }

    @Override
    public T get(int i)
    {
        return (T) arrayList[i];
    }

    @Override
    public void remove(int i)
    {
        temp = new Object[sizeMax];
        int tI=0;
        for(int aI=0; aI<sizeMax; aI++)
        {
            if(aI!=i)
            {
                temp[tI] = arrayList[aI];
                tI++;
            }
        }
        size--;
        arrayList=temp.clone();
    }

    @Override
    public void clear()
    {
        temp = new Object[sizeMax];
        arrayList = temp.clone();
    }

    @Override
    public boolean contains(T t)
    {
        for(int i=0; i<size; i++)
        {
            if(arrayList[i].equals(t))
                return true;
        }
        return false;
    }

    //make an array clone the current array, double the current, fill the current with the temp
    private void growArray()
    {
        temp = arrayList.clone();
        sizeMax*=2;
        arrayList = new Object[sizeMax];

        for(int i=0; i<temp.length; i++){
            arrayList[i]=temp[i];
        }
    }
}
