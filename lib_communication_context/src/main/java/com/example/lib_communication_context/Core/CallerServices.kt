
package com.example.lib_communication_context.Core

import com.example.lib_domain_context.ICaller
import com.example.lib_domain_context.ServiceData
import com.example.lib_utilities.Utilities.Convert
import com.example.lib_utilities.Utilities.JsonHelper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.Date

public class CallerServices : ICaller
{
    companion object
    {
        private var token: String? = null;
        private var expires: Date? = null;
    }

    public override fun Execute(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var response = HashMap<String, Any>();
        try
        {
            response = Authenticate(data);
            if (response == null || response.containsKey("Error"))
                return response;
            response.clear();

            if (data.containsKey("Architecture"))
                data.remove("Architecture");
            var _url = data["Url"] as String?;
            data.remove("Url");
            data.remove("UrlToken");
            data["Bearer"] = token!!;
            var stringData: String = JsonHelper.ConvertToString(data);

            var url = URL(_url);
            var conn = url.openConnection() as HttpURLConnection;
            conn.requestMethod = "POST";
            conn.setRequestProperty("Accept", "application/json");
            conn.doOutput = true;
            conn.outputStream.write(stringData.toByteArray(charset("utf-8")));

            if (conn.responseCode != 200)
            {
                conn.disconnect();
                response["Error"] = conn.responseMessage;
                return response;
            }

            var br = BufferedReader(InputStreamReader(conn.inputStream));
            var resp: String? = "";
            var output: String? = null;
            while (br.readLine().also { output = it } != null) {
                resp += output;
            }
            conn.disconnect();

            if (resp == null || resp == "")
            {
                response["Error"] = "lbErrorAuthentication";
                return response;
            }
            resp = resp.replace("\"", "").replace("\\", "'").replace("'", "\"");
            response = JsonHelper.ConvertToObject(resp);
            return response;
        }
        catch (ex: Exception)
        {
            response["Error"] = ex.toString();
            return response;
        }
    }

    public fun Authenticate(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var response = HashMap<String, Any>()
        try
        {
            if (expires != null &&
                Date().time - expires!!.time <= Date(0, 0, 0, 0, 1, 0).time)
            {
                response["Response"] = "Ok";
                return response;
            }

            var temp = HashMap<String, Any>();
            var _url = data["UrlToken"] as String?;
            temp["User"] = ServiceData.UserData;
            var stringData: String = JsonHelper.ConvertToString(temp);

            var url = URL(_url);
            var conn = url.openConnection() as HttpURLConnection;
            conn.requestMethod = "POST";
            conn.setRequestProperty("Accept", "application/json");
            conn.doOutput = true;
            conn.outputStream.write(stringData.toByteArray(charset("utf-8")));

            if (conn.responseCode != 200)
            {
                response["Error"] = conn.responseMessage;
                return response;
            }

            var br = BufferedReader(InputStreamReader(conn.inputStream));
            var resp: String? = "";
            var output: String? = null;
            while (br.readLine().also { output = it } != null) {
                resp += output;
            }
            conn.disconnect();

            if (resp == null || resp == "")
            {
                response["Error"] = "lbErrorAuthentication";
                return response;
            }
            resp = resp.replace("\"", "").replace("\\", "'").replace("'", "\"");
            response = JsonHelper.ConvertToObject(resp);
            token = response["Token"] as String?;
            expires = Convert.StringToDate(response["Expires"] as String?);
            return response;
        }
        catch (ex: Exception)
        {
            response["Error"] = ex.toString();
            return response;
        }
    }
}