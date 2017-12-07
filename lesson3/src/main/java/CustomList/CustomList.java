package CustomList;

import java.util.*;

/**
 * Created by Administrator on 06.12.2017.
 */
public class CustomList<E> implements List<E> {
    private int size;
    private Object[] arrObj;

    public CustomList()
    {
        arrObj=new Object[0];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for(Object o : arrObj)
        {
                stringBuilder.append(o+",");
        }
        stringBuilder.append("]");
        stringBuilder.replace(stringBuilder.lastIndexOf(","),stringBuilder.lastIndexOf("]"),"");
        return stringBuilder.toString();
    }

    public CustomList(int size)
    {
        arrObj=new Object[size];
        this.size=size;
    }

    @Override
    public E remove(int index) {

        E result=(E)arrObj[index];
        arrObj[index]=null;
        Object[] newArr=new Object[arrObj.length-1];
        int i=0;
        for(Object obj : arrObj)
        {
            if(obj!=null) {
                newArr[i] = obj;
                i++;
            }
        }
        this.arrObj=newArr;
        this.size--;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListIter(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter(0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for(Object c:arrObj)
            if (c.equals(o))
                return true;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return arrObj;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(arrObj, size, a.getClass());
        System.arraycopy(arrObj, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        for (int i=0;i<size;i++)
        {
            if(arrObj[i]==null)
            {
                arrObj[i]=e;
                return true;
            }
        }
        Object[] newArr=new Object[arrObj.length+1];
        for (int i=0;i<arrObj.length;i++)
        {
            newArr[i]=arrObj[i];
        }
        newArr[size] = e;
        size++;
        arrObj=newArr;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a=c.toArray();
        int numNew=a.length;
        System.arraycopy(a, 0, arrObj, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        Object[] a = c.toArray();
        int numNew = a.length;
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(arrObj, index, arrObj, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, arrObj, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<?> e = iterator();
        while (e.hasNext()) {
            if (c.contains(e.next())) {
                e.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        try {
            return (E) arrObj[index];
        }catch (IndexOutOfBoundsException e)
        {e.printStackTrace();}
        return null;
    }

    public E set(int index, E element) {
        E oldValue = (E)arrObj[index];
        arrObj[index] = element;
        return oldValue;
    }

    public void add(int index, E element) {
        Object[] newArr=new Object[arrObj.length+1];
        for(int i=0;i<arrObj.length;i++)
        {
            newArr[i]=arrObj[i];
        }
        newArr[newArr.length-1]=element;
        size++;
    }

    private class Iter implements Iterator<E>
    {
        int cursor;
        int last=-1;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public E next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            last=cursor;
            E e= (E)CustomList.this.arrObj[cursor];
            cursor++;
            return e;
        }
        @Override
        public void remove()
        {
            if (last < 0)
                throw new IllegalStateException();
            CustomList.this.remove(last);
            cursor=last;
            last=-1;

        }
    }
    private class ListIter extends Iter implements ListIterator<E>
    {
        ListIter(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = CustomList.this.arrObj;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[last = i];
        }

        public void set(E e) {
            if (last < 0)
                throw new IllegalStateException();
            try {
                CustomList.this.set(last, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
                int i = cursor;
                CustomList.this.add(i, e);
                cursor = i + 1;
                last = -1;
        }
    }
}
