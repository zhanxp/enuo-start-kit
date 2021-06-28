using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using Enuo.Dotnet.Core.Model;

namespace Enuo.Dotnet.Model
{
  [Table("category")]
  public class Category : BaseEntity
  {
    [Required]
    [MaxLength(200)]
    [Column("title")]
    [Display(Name = "名称")]
    public string title { get; set; }
  }
}
