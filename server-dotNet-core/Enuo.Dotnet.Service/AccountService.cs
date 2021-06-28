using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Enuo.Dotnet.Model;

namespace Enuo.Dotnet.Service
{
  public class AccountService : BaseService<Admin>
  {
    public AccountService(MySqlDbContext dbContext) : base(dbContext)
    {

    }


    public bool ValidateLogin(string userName, string password)
    {
      return DBSet.Any(s => s.name == userName && s.password == password);
    }
  }
}
