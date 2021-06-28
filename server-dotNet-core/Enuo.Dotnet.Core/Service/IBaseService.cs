using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using Enuo.Dotnet.Core.Model;

namespace Enuo.Dotnet.Core
{
  public interface IBaseService<T> where T : class
  {
    Task<List<T>> List();

    Task<List<T>> List(Expression<Func<T, bool>> where);

    Task<PageResult<T>> PageList(int pageIndex, int pageSize);

    Task<PageResult<T>> PageList(int pageIndex, int pageSize, Expression<Func<T, bool>> where);

    Task<T> FindById(int id);

    Task<int> Insert(T ent);

    Task<int> Update(T ent);

    Task<int> Delete(int id);

    bool Exists(int id);
  }
}
