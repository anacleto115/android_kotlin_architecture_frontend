
package com.example.lib_utilities.Utilities

public class KLinq
{
    companion object
    {
        public fun <T> from(list: Collection<T>): KLinqList<T> { return KLinqList(list); }
    }
}