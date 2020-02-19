//------------------------------------------------------------------------------
//     Interface of Repository.
//
//     Check "RepositoryBase.cs" for details.
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Linq.Expressions;
using Com.EnuoCms.Core;
using Com.EnuoCms.Core.Common;

namespace Com.EnuoCms.IService
{
    public interface IRepository<T>
        where T : class
    {
        void CloseLazyLoading();

        IEnumerable<RE> QuerySQL<RE>(string command, params object[] parameters);
        int ExecuteSQL(string command, params object[] parameters);

        void Add(T entity);
        void Update(T entity);
        void Update(T entity, IEnumerable<string> updatedFields);
        void Delete(T entity);
        void Delete(Expression<Func<T, bool>> where);

        T GetById(long Id);
        T GetById(string Id);
		T Get(Expression<Func<T, bool>> where);

        int GetCount(Expression<Func<T, bool>> where);
       
        IEnumerable<T> GetAll();
        //IEnumerable<T> GetAll(int pageIndex, int pageSize, params IOrderByExpression<T>[] orderBy);
        IEnumerable<T> GetMany(Expression<Func<T, bool>> where);
        //IEnumerable<T> GetManyAsNoTracking(Expression<Func<T, bool>> where);
        //IEnumerable<T> GetMany(Expression<Func<T, bool>> where, int pageIndex, int pageSize,params IOrderByExpression<T>[] orderBy);
        IEnumerable<T> GetMany(Expression<Func<T, bool>> where, int count,params IOrderByExpression<T>[] orderBy);

        //IEnumerable<T> QueryEx(Expression<Func<T, bool>> where, int pageIndex, int pageSize, params IOrderByExpression<T>[] orderBy);

        PageResult<T> QueryPagedList(Expression<Func<T, bool>> where, int pageIndex, int pageSize, params IOrderByExpression<T>[] orderBy);
    }
}
