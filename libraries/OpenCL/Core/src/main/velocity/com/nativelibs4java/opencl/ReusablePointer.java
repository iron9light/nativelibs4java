#parse("main/Header.vm")
package com.nativelibs4java.opencl;
import org.bridj.*;

/**
 *
 * @author ochafik
 */
final class ReusablePointer {
    private final Pointer<?> pointer;
    private final long bytesCapacity;

    public ReusablePointer(long bytesCapacity) {
        this.bytesCapacity = bytesCapacity;
        this.pointer = Pointer.allocateBytes(bytesCapacity).withoutValidityInformation();
    }
    public Pointer<Integer> pointerToInts(int... values) {
        if (values == null)
            return null;
        long needed = 4 * values.length;
        if (needed > bytesCapacity) {
            return Pointer.pointerToInts(values);
        } else {
            return (Pointer)pointer.setInts(values);
        }
    }
    public Pointer<SizeT> pointerToSizeTs(long... values) {
        if (values == null)
            return null;
        long needed = SizeT.SIZE * values.length;
        if (needed > bytesCapacity) {
            return Pointer.pointerToSizeTs(values);
        } else {
            return (Pointer)pointer.setSizeTs(values);
        }
    }
    public Pointer<SizeT> pointerToSizeTs(int... values) {
        if (values == null)
            return null;
        long needed = SizeT.SIZE * values.length;
        if (needed > bytesCapacity) {
            return Pointer.pointerToSizeTs(values);
        } else {
            return (Pointer)pointer.setSizeTs(values);
        }
    }
    public <T> Pointer<T> allocatedBytes(int needed) {
        if (needed == 0)
            return null;
        if (needed > bytesCapacity) {
            return (Pointer)Pointer.allocateBytes(needed);
        } else {
            return (Pointer)pointer;
        }
    }
    public <T> Pointer<T> allocatedSizeTs(int needed) {
    	return allocatedBytes(needed * SizeT.SIZE);
    }
    public <T> Pointer<T> allocatedInts(int needed) {
    	return allocatedBytes(needed * 4);
    }
}