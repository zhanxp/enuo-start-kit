using Enuo.Dotnet.Model;
using System;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Enuo.Dotnet.Service
{
  public class ArticleService : BaseService<Article>
  {
    public ArticleService(MySqlDbContext dbContext) : base(dbContext)
    {

    }

  }
}
