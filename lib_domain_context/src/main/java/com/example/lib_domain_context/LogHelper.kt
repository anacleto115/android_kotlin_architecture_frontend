
package com.example.lib_domain_context

public class LogHelper
{
    companion object
    {
        public var ILogHelper: ILogHelper? = null;

        public fun Log(exception: Exception)
        {
            if (ILogHelper == null)
                return;

            ILogHelper!!.Log(exception, false);
        }

        public fun Log(exception: Exception, subError: Boolean)
        {
            if (ILogHelper == null)
                return;

            ILogHelper!!.Log(exception, subError);
        }
    }
}