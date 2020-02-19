using Com.EnuoCms.IService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace Com.EnuoCms.Service
{
public class DatabaseFactory : Disposable, IDatabaseFactory
{
    private EnuoCmsDataContex dataContext;
    public EnuoCmsDataContex Get(bool isLazy)
    {
        return dataContext ?? (dataContext = new EnuoCmsDataContex(isLazy));
    }
    protected override void DisposeCore()
    {
        if (dataContext != null)
            dataContext.Dispose();
    }
}
}
