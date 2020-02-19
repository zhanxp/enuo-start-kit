using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Com.EnuoCms.IService
{
    public interface IUnitOfWork
    {
        void Commit();
    }
}
