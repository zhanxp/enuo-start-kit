using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Enuo.Dotnet.Core.Model;
using Enuo.Dotnet.Core.Utils;
using MySqlX.XDevAPI.Common;
using System.Linq.Expressions;

namespace Enuo.Dotnet.Service
{
  public abstract class BaseService<T> where T : BaseEntity
  {
    private DbSet<T> _dbset;
    public BaseService(MySqlDbContext dbContext)
    {
      this.DbContext = dbContext;
    }

    protected MySqlDbContext DbContext
    {
      get;
      private set;
    }

    protected DbSet<T> DBSet
    {
      get { return _dbset ?? (_dbset = DbContext.Set<T>()); }
    }

    public async Task<List<T>> List()
    {
      return await DBSet.ToListAsync();
    }

    public async Task<List<T>> List(Expression<Func<T, bool>> where)
    {
      return await DBSet.Where<T>(where).ToListAsync();
    }

    public async Task<T> FindById(int id)
    {
      return await DBSet.FirstOrDefaultAsync(m => m.id == id);
    }

    public async Task<int> Insert(T ent)
    {
      DbContext.Add(ent);
      return await DbContext.SaveChangesAsync();
    }

    public async Task<int> Update(T ent)
    {
      //var old = dBSet.FirstOrDefault(m => m.id == ent.id);
      //if (old == null)
      //{
      //  return 0;
      //}
      //ObjectHelper.CopyProperties(ent, old);
      //dbContext.Update(old);
      DbContext.Update(ent);
      return await DbContext.SaveChangesAsync();
    }

    public async Task<int> Delete(int id)
    {
      var old = await DBSet.FirstOrDefaultAsync(m => m.id == id);
      if (old == null)
      {
        return 0;
      }
      DBSet.Remove(old);
      return await DbContext.SaveChangesAsync();
    }

    public bool Exists(int id)
    {
      return DBSet.Any(e => e.id == id);
    }

    public async Task<PageResult<T>> PageList(int pageIndex, int pageSize)
    {
      int total = await DBSet.CountAsync();
      var items = await DBSet.ToListAsync();
      return new PageResult<T>(pageIndex, pageSize, total, items);
    }

    public async Task<PageResult<T>> PageList(int pageIndex, int pageSize, Expression<Func<T, bool>> where)
    {
      int total = await DBSet.Where<T>(where).CountAsync();
      var items = await DBSet.Where<T>(where).ToListAsync();
      return new PageResult<T>(pageIndex, pageSize, total, items);
    }

  }
}
