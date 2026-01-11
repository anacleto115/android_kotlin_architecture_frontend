
package com.example.lib_presentation_context.Core

import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.Enumerables.Loading
import com.example.lib_domain_context.IEntities
import com.example.lib_domain_context.IHelper
import com.example.lib_domain_context.IScreen
import com.example.lib_domain_context.IViewModel
import com.example.lib_domain_context.LogHelper
import com.example.lib_domain_context.MessagesHelper
import com.example.lib_language.Bundle
import com.example.lib_presentation_core.Core.FactoryHelper
import com.example.lib_utilities.Utilities.KLinq

public open abstract class VModel<T> : IViewModel<T>
{
    protected var current: T? = null;
    protected var currentCopy: T? = null;
    protected var list: List<T>? = null;
    protected var copy: List<T>? = null;
    protected var IScreen: IScreen? = null;
    protected var IHelper: IHelper? = null;
    protected var architecture: Enumerables.Architecture? = Enumerables.Architecture.Services;
    protected var isLoading = false;
    protected var fontSize = 16;
    protected var lbSelectItem: String? = null;
    protected var lbDeleteEntity: String? = null;

    public constructor(data: HashMap<String, Any>)
    {
        try
        {
            if (data.containsKey("Architecture"))
            {
                architecture = data["Architecture"] as Enumerables.Architecture?;
                data.remove("Architecture");
            }
            if (data.containsKey("View"))
            {
                IScreen = data["View"] as IScreen?;
                data.remove("View");
            }
            Resources();
            if (data.containsKey("IHelper"))
            {
                IHelper = data["IHelper"] as IHelper?;
                data.remove("IHelper");
            }
            list = ArrayList();
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
        }
    }

    public override fun SetCurrent(v: T?) { current = v; }
    public override fun GetCurrent(): T? { return current!!; }
    public override fun SetCurrentCopy(v: T?) { currentCopy = v; }
    public override fun GetCurrentCopy(): T? { return currentCopy; }
    public override fun SetList(v: List<T>?) { list = v; }
    public override fun GetList(): List<T>? { return list!!; }

    public fun Resources()
    {
        lbSelectItem = Bundle.Get("RsMessages", "lbSelectItem");
        lbDeleteEntity = Bundle.Get("RsMessages", "lbDeleteEntity");
    }

    public abstract fun CreateNew(): T;

    public fun GetItem(t: T?): T?
    {
        return KLinq.from(list!!)
            .firstOrDefault({ x -> (x as IEntities).Get_Id() == (t as IEntities?)!!.Get_Id(); });
    }

    public fun CreateCopy()
    {
        if (current == null)
            return;
        currentCopy = (current as IEntities).GetClone() as T?;
    }

    public fun Comparer(t1: T?, t2: T): Boolean { return false; }

    public open fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        FactoryHelper.IFactoryHelper =
            if (FactoryHelper.IFactoryHelper == null) FactoryHelperContext() else FactoryHelper.IFactoryHelper;
        data["Architecture"] = architecture!!;
        return data;
    }

    public override fun New(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data: HashMap<String, Any>? = data;
        var response = HashMap<String, Any>();
        try
        {
            currentCopy = CreateNew();
            data = data ?: HashMap();
            data["Action"] = Enumerables.Action.OPEN;
            IScreen!!.Change(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
    }

    public override fun Modify(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data: HashMap<String, Any> = data;
        var response = HashMap<String, Any>();
        try
        {
            if (current == null)
                return response;
            CreateCopy();
            data = data ?: HashMap();
            data["Action"] = Enumerables.Action.OPEN;
            IScreen!!.Change(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
    }

    public override fun Select(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        var response = HashMap<String, Any>();
        try
        {
            isLoading = true;
            IScreen!!.Loading(Loading.ADD);
            data = data ?: HashMap();
            data = Load(data);
            response = IHelper!!.Select(data);
            if (response == null || !response.containsKey("Entities"))
                return response;
            list = if (list == null) ArrayList() else list;
            (list!! as ArrayList).clear();
            val temp = response["Entities"] as List<T>?;
            for (item in temp!!)
                (list!! as ArrayList).add(item);
            //current = list.get(0);
            copy = ArrayList(list);
            Close(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen!!.Loading(Loading.REMOVE);
        }
    }

    public override fun Delete(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        var response = HashMap<String, Any>();
        try
        {
            isLoading = true;
            IScreen!!.Loading(Loading.ADD);
            data = data ?: HashMap();
            data = Load(data);
            if (data.containsKey("Entity"))
                current = data["Entity"] as T?;
            if (!data.containsKey("Entity"))
                data["Entity"] = current as Any;
            if (current == null)
                return response;
            if (!(MessagesHelper.Show(lbDeleteEntity!!, MessagesHelper.Message.QUESTION) as Boolean))
                return response;
            response = IHelper!!.Delete(data);
            if (response == null || response.containsKey("Error"))
                return response;
            current = response["Entity"] as T?;
            if (current != null)
            {
                (list!! as ArrayList).remove(GetItem(current));
                current = null;
                currentCopy = null;
            }
            Close(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen!!.Loading(Loading.REMOVE);
        }
    }

    public override fun Save(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        var response = HashMap<String, Any>();
        try
        {
            isLoading = true;
            IScreen!!.MoveFocus();
            IScreen!!.Loading(Loading.ADD);
            data = data ?: HashMap();
            data = Load(data);
            if (data.containsKey("Entity"))
                currentCopy = data["Entity"] as T?;
            if (!data.containsKey("Entity"))
                data["Entity"] = currentCopy as Any;
            if (currentCopy == null)
                return response;
            if ((currentCopy as IEntities).Get_Id() == 0)
            {
                response = IHelper!!.Insert(data);
                if (response == null || response.containsKey("Error"))
                    return response;
                currentCopy = response["Entity"] as T?;
                current = currentCopy;
                (list!! as MutableList<T>).add(current!!);
            } else if ((currentCopy as IEntities).Get_Id() != 0 &&
                !Comparer(current, currentCopy!!))
            {
                response = IHelper!!.Update(data)
                if (response == null || response.containsKey("Error"))
                    return response;
                currentCopy = response["Entity"] as T?;
                (list!! as MutableList<T>).remove(GetItem(current));
                (list!! as MutableList<T>).add(currentCopy!!);
                currentCopy = null;
            }
            if (response != null || !response.containsKey("Error"))
                Close(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen!!.Loading(Loading.REMOVE);
        }
    }

    public override fun Close(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data: HashMap<String, Any>? = data;
        var response = HashMap<String, Any>();
        try
        {
            data = data ?: HashMap();
            data["Action"] = Enumerables.Action.CLOSE;
            IScreen!!.Change(data);
            return response;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
            response["Error"] = ex.toString();
            return response;
        }
    }
}