
package com.example.lib_utilities.Utilities

public interface ICacheHelper
{
    public fun Add(key: String, value: Any);
    public fun Instance();
    public fun Contains(key: String): Boolean;
    public fun Get(key: String): Any?;
    public fun Remove(key: String);
}