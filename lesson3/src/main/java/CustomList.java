import java.util.AbstractList;
import java.util.Arrays;

/**
 * Created by Administrator on 02.12.2017.
 */
public class CustomList<E> extends AbstractList<E> {
    private int size;

    private Object[] arrObj;

    public CustomList(int size)
    {
        arrObj=new Object[size];
        this.size=size;
    }
    public CustomList()
    {
        arrObj=new Object[0];
        this.size=0;
    }

    @Override
    public boolean add(E e) {
        size++;
        Object[] newArr=Arrays.copyOf(arrObj,size);
        newArr[size-1]=e;
        arrObj=newArr;
        return true;
    }

    @Override
    public E get(int index)
    {
        try {
            return (E) arrObj[index];
        }catch (Exception e)
        {e.printStackTrace();
        return null;}

    }

    @Override
    public E set(int index, E element) {
        arrObj[index]=element;
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
