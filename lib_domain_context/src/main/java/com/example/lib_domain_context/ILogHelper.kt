
package com.example.lib_domain_context

public interface ILogHelper
{
    public fun Log(exception: Exception);
    public fun Log(exception: Exception, subError: Boolean);
}