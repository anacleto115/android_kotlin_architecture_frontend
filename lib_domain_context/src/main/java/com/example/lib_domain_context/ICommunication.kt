
package com.example.lib_domain_context

public interface ICommunication
{
    public fun Select(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Insert(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Update(data: HashMap<String, Any>): HashMap<String, Any>;
    public fun Delete(data: HashMap<String, Any>): HashMap<String, Any>;
}