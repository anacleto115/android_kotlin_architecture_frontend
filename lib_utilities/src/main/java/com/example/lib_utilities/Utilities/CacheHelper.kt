
package com.example.lib_utilities.Utilities

public class CacheHelper
{
    companion object
    {
        private var ICacheHelper: ICacheHelper? = null;

        public fun Add(key: String, value: Any)
        {
            CreateInstance()
            ICacheHelper!!.Add(key, value)
        }

        public fun CreateInstance(iCacheHelper: ICacheHelper? = null)
        {
            if (ICacheHelper != null)
                return;
            if (iCacheHelper != null)
                ICacheHelper = iCacheHelper;
            else if (ICacheHelper == null)
                ICacheHelper = CacheDictionary();
        }

        public fun Contains(key: String): Boolean
        {
            CreateInstance();
            return ICacheHelper!!.Contains(key);
        }

        public fun Get(key: String): Any?
        {
            CreateInstance();
            return ICacheHelper!!.Get(key);
        }

        public fun Remove(key: String)
        {
            CreateInstance();
            ICacheHelper!!.Remove(key);
        }
    }
}