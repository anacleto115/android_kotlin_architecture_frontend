
package com.example.lib_utilities.Utilities

import org.json.JSONArray
import org.json.JSONObject

public class JsonHelper
{
    companion object
    {
        private val parser = JSONObject();

        public fun ConvertToString(data: HashMap<String, Any>): String
        {
            var jsonObject = JSONObject(data.toMap());
            return jsonObject.toString().replace('"', '\"');
        }

        public fun ConvertToObject(data: String): HashMap<String, Any>
        {
            val jsonObject = JSONObject(data)
            return ToDictionary(jsonObject)
        }

        private fun ToDictionary(obj: JSONObject): HashMap<String, Any>
        {
            var map = HashMap<String, Any>();
            var keysItr = obj.keys();
            while (keysItr.hasNext())
            {
                var key = keysItr.next();
                var value = obj[key];
                if (value is JSONArray)
                    value = ToList(value);
                else if (value is JSONObject)
                    value = ToDictionary(value);
                map[key] = value;
            }
            return map;
        }

        private fun ToList(array: JSONArray): List<Any>
        {
            var list: MutableList<Any> = ArrayList();
            for (i in 0 until array.length())
            {
                var value = array[i];
                if (value is JSONArray)
                    value = ToList(value);
                else if (value is JSONObject)
                    value = ToDictionary(value);
                list.add(value);
            }
            return list;
        }
    }
}