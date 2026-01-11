
package com.example.lib_domain_context

public interface IViewModel<T>
{
    public fun SetCurrent(v: T?);
    public fun GetCurrent(): T?;
    public fun SetCurrentCopy(v: T?);
    public fun GetCurrentCopy(): T?;
    public fun SetList(v: List<T>?);
    public fun GetList(): List<T>?;

    public fun Select(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun New(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Modify(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Save(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Delete(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Close(data: HashMap<String, Any>): HashMap<String, Any>;
}