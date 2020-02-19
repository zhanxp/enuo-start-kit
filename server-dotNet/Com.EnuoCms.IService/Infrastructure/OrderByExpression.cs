using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Linq.Expressions;

namespace Com.EnuoCms.IService
{
    public interface IOrderByExpression<T>
        where T : class
    {
        IOrderedQueryable<T> OrderBy(IQueryable<T> query);
        IOrderedQueryable<T> ThenBy(IOrderedQueryable<T> query);
    }

    public class OrderByExpression<T, Object> : IOrderByExpression<T>
        where T : class
    {
        private Expression<Func<T, Object>> _expression;
        private bool _descending;

        public OrderByExpression(Expression<Func<T, Object>> expression, bool descending = false)
        {
            _expression = expression;
            _descending = descending;
        }

        public IOrderedQueryable<T> OrderBy(IQueryable<T> query)
        {
            if (_descending)
            {
                return query.OrderByDescending(_expression);
            }
            else
            {
                return query.OrderBy(_expression);
            }
        }

        public IOrderedQueryable<T> ThenBy(IOrderedQueryable<T> query)
        {
            if (_descending)
            {
                return query.ThenByDescending(_expression);
            }
            else
            {
                return query.ThenBy(_expression);
            }
        }
    }
}
