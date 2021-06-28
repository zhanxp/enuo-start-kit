using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Enuo.Dotnet.Core.Model
{
  public class PageResult<T>
  {
    public int pageIndex { get; set; }
    public int pageSize { get; set; }
    public int total { get; set; }
    public List<T> items { get; set; }

    public PageResult()
    {
      this.items = new List<T>();
    }

    public PageResult(int pageIndex, int pageSize, int total, List<T> items)
    {
      this.pageIndex = pageIndex;
      this.pageSize = pageSize;
      this.total = total;
      this.items = items;
    }
  }
}
