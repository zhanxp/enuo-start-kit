using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Enuo.Dotnet.Model;
using Microsoft.EntityFrameworkCore;

namespace Enuo.Dotnet.Service
{
  public class MySqlDbContext : DbContext
  {
    public MySqlDbContext(DbContextOptions<MySqlDbContext> options) : base(options) { }

    public DbSet<Article> article { get; set; }

    public DbSet<Admin> admin { get; set; }

    public DbSet<Category> category { get; set; }
  }
}