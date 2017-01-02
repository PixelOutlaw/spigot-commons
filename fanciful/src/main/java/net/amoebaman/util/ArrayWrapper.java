/**
 * The MIT License
 * Copyright (c) 2015 Teal Cube Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.amoebaman.util;

import org.apache.commons.lang3.Validate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * Represents a wrapper around an array class of an arbitrary reference type,
 * which properly implements "value" hash code and equality functions.
 * <p>
 * This class is intended for use as a key to a map.
 * </p>
 * @author Glen Husman
 * @param <E> The type of elements in the array.
 * @see Arrays
 */
public final class ArrayWrapper<E> {

	/**
	 * Creates an array wrapper with some elements.
	 * @param elements The elements of the array.
	 */
	public ArrayWrapper(E... elements){
		setArray(elements);
	}
	
	private E[] _array;
	
	/**
	 * Retrieves a reference to the wrapped array instance.
	 * @return The array wrapped by this instance.
	 */
	public E[] getArray(){
		return _array;	
	}
	
	/**
	 * Set this wrapper to wrap a new array instance.
	 * @param array The new wrapped array.
	 */
	public void setArray(E[] array){
		Validate.notNull(array, "The array must not be null.");
		_array = array;
	}
	
	/**
	 * Determines if this object has a value equivalent to another object.
	 * @see Arrays#equals(Object[], Object[])
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object other)
    {
        if (!(other instanceof ArrayWrapper))
        {
            return false;
        }
        return Arrays.equals(_array, ((ArrayWrapper)other)._array);
    }

	/**
	 * Gets the hash code represented by this objects value.
	 * @see Arrays#hashCode(Object[])
	 * @return This object's hash code.
	 */
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(_array);
    }
    
    /**
     * Converts an iterable element collection to an array of elements.
     * The iteration order of the specified object will be used as the array element order.
     * @param list The iterable of objects which will be converted to an array.
     * @param c The type of the elements of the array.
     * @return An array of elements in the specified iterable.
     */
     @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterable<? extends T> list, Class<T> c) {
        int size = -1;
        if(list instanceof Collection<?>){
        	@SuppressWarnings("rawtypes")
			Collection coll = (Collection)list;
        	size = coll.size();
        }
        
        
        if(size < 0){
        	size = 0;
        	// Ugly hack: Count it ourselves
        	for(@SuppressWarnings("unused") T element : list){
        		size++;
        	}
        }
    	
        T[] result = (T[]) Array.newInstance(c, size);
        int i = 0;
        for(T element : list){ // Assumes iteration order is consistent
    		result[i++] = element; // Assign array element at index THEN increment counter
    	}
        return result;
    }
}
