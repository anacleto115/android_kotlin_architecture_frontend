
package com.example.lib_language

import com.example.lib_language.Applications.*
import com.example.lib_language.Maintenances.*

public class Bundle
{
    companion object
    {
        private var data: HashMap<String, Any>? = null;

        public fun Load(language: String)
        {
            data = if (data == null) HashMap() else data;
            data!!["RsMenu"] = RsMenu(language).Get();
            data!!["RsMessages"] = RsMessages(language).Get();
            data!!["RsHome"] = RsHome(language).Get();
            data!!["RsPersonTypes"] = RsPersonTypes(language).Get();
            data!!["RsLogin"] = RsLogin(language).Get();
            data!!["RsPersons"] = RsPersons(language).Get();
        }

        public fun Get(resource: String, key: String): String?
        {
            var response = "";
            if (!data!!.containsKey(resource))
                return response;
            var resources = data!![resource] as HashMap<String, String>?;
            if (!resources!!.containsKey(key))
                return response;
            else
                return resources[key];
        }
    }
}