// IBookManager.aidl
package com.zzh.main.aidl;
import com.zzh.main.aidl.Book;
// Declare any non-default types here with import statements/Users/user/WorkPlaces/AS/AndroidArt/app/src/main/java/com/zzh/main/model/Book.java
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBookList();
    void addBook(in Book book);
}
