
package com.example.lib_utilities.Utilities

public interface Callback<T>
{
    public fun Execute(data: T): T;
}