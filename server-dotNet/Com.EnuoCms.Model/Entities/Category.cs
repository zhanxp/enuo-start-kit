using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Model.Entities
{
  [Table("category")]
  public class Category : BaseEntity
  {
    //[Key]
    //[Column("id")]
    //public int ID { get; set; }

    [Required]
    [MaxLength(200)]
    [Column("title")]
    [Display(Name = "名称")]
    public string Title { get; set; }
  }
}
