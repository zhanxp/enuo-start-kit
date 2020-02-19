using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Com.EnuoCms.IService
{
    public interface IDatabaseFactory : IDisposable
    {
        EnuoCmsDataContex Get(bool isLazy);
    }
}
