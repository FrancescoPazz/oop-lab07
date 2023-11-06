package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements){
        this.elements = elements;
        filter = new Predicate<T>() {
            public boolean test(T elem){
                return true;
            }
        };
    }
    
    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter){
        this.elements = elements;
        this.filter = filter;
    }
    
    public void setIterationPolicy(Predicate<T> filter){
        this.filter = filter;
    }
    
    @Override
    public java.util.Iterator<T> iterator() {
        return new PolicyIterator();
    }

    private class PolicyIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public T next(){
            if(hasNext()){
                return elements[current++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext(){
            while(this.current < elements.length){
                if(filter.test(elements[this.current])){
                    return true;
                }
                this.current++;
            }
            return false;
        }
    }
}
