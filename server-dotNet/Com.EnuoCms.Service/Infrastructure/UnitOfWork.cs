using Com.EnuoCms.IService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Com.EnuoCms.Service
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly IDatabaseFactory databaseFactory;
        private EnuoCmsDataContex dataContext;

        public UnitOfWork(IDatabaseFactory databaseFactory)
        {
            this.databaseFactory = databaseFactory;
        }

        protected EnuoCmsDataContex DataContext
        {
            get { return dataContext ?? (dataContext = databaseFactory.Get(true)); }
        }

        public void Commit()
        {
            DataContext.Commit();
        }
    }
}
