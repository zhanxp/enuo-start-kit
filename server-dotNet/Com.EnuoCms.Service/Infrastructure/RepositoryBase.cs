//------------------------------------------------------------------------------
//     Repository Layer.
//
//     this class will be expanded in the future, for supporting more operations.
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Entity;
using System.Data;
using System.Linq.Expressions;
using System.Data.Entity.Infrastructure;
using System.Reflection;
using System.Collections;
using System.Data.SqlClient;
using Com.EnuoCms.Core.Common;
using Com.EnuoCms.Model;
using Com.EnuoCms.IService;
using Com.EnuoCms.Core;

namespace Com.EnuoCms.Service
{
    public abstract class RepositoryBase<T> where T : BaseEntity
    {
        private EnuoCmsDataContex dataContext;
        private IDbSet<T> dbset;
        private bool isLazy = true;

        protected RepositoryBase(IDatabaseFactory databaseFactory)
        {
            DatabaseFactory = databaseFactory;
        }

        protected IDatabaseFactory DatabaseFactory
        {
            get;
            private set;
        }

        protected EnuoCmsDataContex DataContext
        {
            get { return dataContext ?? (dataContext = DatabaseFactory.Get(isLazy)); }
        }

        protected IDbSet<T> DBSet
        {
            get { return dbset ?? (dbset = DataContext.Set<T>()); }
        }

        public void CloseLazyLoading()
        {
            isLazy = false;
        }

        public IEnumerable<RE> QuerySQL<RE>(string command, params object[] parameters)
        {
            return (DataContext as IObjectContextAdapter).ObjectContext.ExecuteStoreQuery<RE>(command, parameters).ToList();
        }

        public int ExecuteSQL(string command, params object[] parameters)
        {
            return (DataContext as IObjectContextAdapter).ObjectContext.ExecuteStoreCommand(command, parameters);
        }

        public virtual void Add(T entity)
        {
            DBSet.Add(entity);
        }

        public virtual void Update(T entity)
        {
			DBSet.Attach(entity);
            SetEntityState(entity, EntityState.Modified, null);
        }

        public virtual void Update(T entity, IEnumerable<string> updatedFields)
        {
            DBSet.Attach(entity);
            SetEntityState(entity, EntityState.Modified, updatedFields);
        }

        private void SetEntityState(object entity, EntityState state, IEnumerable<string> updatedFields)
        {
            if (entity == null)
            {
                return;
            }

            if (updatedFields == null)
            {
                dataContext.Entry(entity).State = EntityState.Modified;
            }
            else
            {
                var stateEntry = ((IObjectContextAdapter)dataContext)
                    .ObjectContext.ObjectStateManager.GetObjectStateEntry(entity);

                foreach (string field in updatedFields)
                {
                    if (entity.GetType().GetProperty(field) != null)
                    {
                        stateEntry.SetModifiedProperty(field);
                    }
                }
            }
        }

        public virtual void Delete(T entity)
        {
            DBSet.Remove(entity);
        }

        public virtual void Delete(Expression<Func<T, bool>> where)
        {
            IEnumerable<T> objects = DBSet.Where<T>(where).AsEnumerable();
            foreach (T obj in objects)
                DBSet.Remove(obj);
        }

        public virtual T GetById(long id)
        {
            return DBSet.Find(id);
        }

        public virtual T GetById(string id)
        {
            return DBSet.Find(id);
        }

        public T Get(Expression<Func<T, bool>> where)
        {
            return DBSet.Where(where).FirstOrDefault<T>();
        }

        public virtual IEnumerable<T> GetAll()
        {
            return DBSet.ToList();
        }

        public virtual int GetCount(Expression<Func<T, bool>> where)
        {
            return DBSet.Where(where).Count();
        }
        public virtual IEnumerable<T> GetMany(Expression<Func<T, bool>> where)
        {
            return DBSet.Where(where).ToList();
        }

        //public virtual IEnumerable<T> GetManyAsNoTracking(Expression<Func<T, bool>> where)
        //{
        //    return DBSet.Where(where).AsNoTracking().ToList();
        //}


        public virtual IEnumerable<T> GetMany(Expression<Func<T, bool>> where, int count,params IOrderByExpression<T>[] orderBy)
        {
			var query = DBSet.AsQueryable();
			if (where != null)
			{
				query = query.Where(where);
			}
			if (orderBy != null && orderBy.Count() != 0)
			{
				IOrderedQueryable<T> ordered = null;
				foreach (var proxy in orderBy)
				{
					if (ordered == null)
					{
						ordered = proxy.OrderBy(query);
					}
					else
					{
						ordered = proxy.ThenBy(ordered);
					}
				}

				query = ordered;
			}
            query = query.Take(count);
            return query.ToList();
        }

        public PageResult<T> QueryPagedList(
            Expression<Func<T, bool>> where,
            int pageIndex, int pageSize,
            params IOrderByExpression<T>[] orderBy)
        {
            if (pageIndex < 1 || pageSize < 1)
            {
                throw new Exception("page split param error.");
            }

            int allcount = 0;
            var query = QueryInternal(where, pageIndex, pageSize, out allcount, orderBy);

            return new PageResult<T>(pageIndex, pageSize,allcount,query.ToList());
        }

        //public IEnumerable<T> QueryEx(
        //    Expression<Func<T, bool>> where,
        //    int pageIndex, int pageSize,
        //     IEnumerable<IOrderByExpression<T>> orderBy)
        //{
        //    int allcount = 0;
        //    return QueryInternal(where, pageIndex, pageSize, out allcount, orderBy);
        //}

        private IEnumerable<T> QueryInternal(
            Expression<Func<T, bool>> where,
            int pageIndex, int pageSize, out int allcount,
            IEnumerable<IOrderByExpression<T>> orderBy)
        {
            var query = DBSet.AsQueryable();
            if (where != null)
            {
                query = query.Where(where);
            }
            allcount = query.Count();
            if (orderBy != null&&orderBy.Count()!=0)
            {
                IOrderedQueryable<T> ordered = null;
                foreach (var proxy in orderBy)
                {
                    if (ordered == null)
                    {
                        ordered = proxy.OrderBy(query);
                    }
                    else
                    {
                        ordered = proxy.ThenBy(ordered);
                    }
                }
                query = ordered;
            }

            if (pageIndex > 0 && pageSize > 0)
            {
                int skipped = (pageIndex - 1) * pageSize;
                int taken = pageSize;

                if (skipped > 0)
                {
                    query = query.Skip(skipped);
                }

                query = query.Take(taken);
            }

            return query.ToList();
        }
    }
}
