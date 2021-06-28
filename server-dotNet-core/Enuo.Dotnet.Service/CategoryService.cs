using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Enuo.Dotnet.Model;

namespace Enuo.Dotnet.Service
{
  public class CategoryService : BaseService<Category>
  {
    public CategoryService(MySqlDbContext dbContext) : base(dbContext)
    {

    }
  }
}
