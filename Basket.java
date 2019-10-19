

import java.util.Objects;
import java.util.function.Predicate;

public class Basket<E>  {


    private static class Node<E>{
        private E value;
        private Node<E> next;
    }

    private int size = 0;

    private Node<E> first = null;
    private Node<E> last = null;

    public E getFirst() {
        return first.value;
    }

    public E getLast() {
        return last.value;
    }


    public E pick(E element){
        if(element == null){
            return null;
        }
        Node<E> tmp = first;
        Node<E> prev = null;
        while(tmp != null){
            if(element.equals(tmp.value)){
                if(prev == null){
                    first = tmp.next;
                }else {
                    prev.next = tmp.next;
                }
                size--;
                return tmp.value;
            }
            prev = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    public E pick(Predicate<? super E> predicate){
        Objects.requireNonNull(predicate);
        Node<E> tmp = first;
        Node<E> prev = null;
        while(tmp != null){
            predicate.test(tmp.value);
            if(predicate.test(tmp.value)){
                if(prev == null){
                    first = tmp.next;
                }else {
                    prev.next = tmp.next;
                }
                size--;
                return tmp.value;
            }
            prev = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    public void add(E e){
        if(e == null){
            return;
        }
        Node<E> node = new Node<>();
        node.value = e;

        if(first == null){
            first = node;
            last = node;
        }else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void addAll(E... all){
        Objects.requireNonNull(all);

        for (E e : all) {
            this.add(e);
        }
    }


    public static <E> Basket<E> of(E... all){
        Basket<E> basket = new Basket<>();
        basket.addAll(all);
        return basket;
    }
}
