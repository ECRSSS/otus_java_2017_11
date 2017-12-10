package CustomList;

import java.util.*;

/**
 * Created by Administrator on 06.12.2017.
 */
public class CustomList<E> implements List<E> {
    private int realSize;

    private Object[] arrObj;

    public CustomList()
    {
        arrObj=new Object[10];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for(Object o : arrObj)
        {
            if(o!=null) {
                stringBuilder.append(o + ",");
            }
        }
        stringBuilder.append("]");
        try {
            stringBuilder.replace(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf("]"), "");
        }catch (Exception e){}
        return stringBuilder.toString();
    }

    public CustomList(int size)
    {
        arrObj=new Object[size];
    }

    @Override
    public E remove(int index) {
    return null;
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
        if (index < 0 || index > realSize)
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
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return realSize==0;
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
        return Arrays.copyOfRange(arrObj,0,realSize);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < realSize)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(arrObj,realSize , a.getClass());
        System.arraycopy(arrObj, 0, a, 0, realSize);
        if (a.length > realSize)
            a[realSize] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        if(e==null){System.out.println("Невозможно добавить null");
            return false;}
        for (int i=0;i<arrObj.length;i++)
        {
            if(arrObj[i]==null)
            {
                arrObj[i]=e;
                realSize++;
                return true;
            }
        }
        int size=(arrObj.length*3)/2;
        Object[] newArr=new Object[size];
        for (int i=0;i<arrObj.length;i++)
        {
            newArr[i]=arrObj[i];
        }
        newArr[realSize] = e;
        realSize++;
        arrObj=newArr;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a=c.toArray();
        int numNew=a.length;
        System.arraycopy(a, 0, arrObj, realSize, numNew);
        realSize += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        Object[] a = c.toArray();
        int numNew = a.length;
        int numMoved = realSize - index;
        if (numMoved > 0)
            System.arraycopy(arrObj, index, arrObj, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, arrObj, index, numNew);
        realSize += numNew;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
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
        realSize++;
    }

    private class Iter implements Iterator<E>
    {
        int cursor;
        int last=-1;

        @Override
        public boolean hasNext() {
            return cursor!=realSize;
        }

        @Override
        public E next() {
            if (cursor >= realSize)
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
