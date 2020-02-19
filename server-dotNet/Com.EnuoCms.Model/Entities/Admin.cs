using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Model.Entities
{
    [Table("admin")]
    public class Admin : BaseEntity
    {
		//[Key]
        //[Column("id")]
        //public override int ID { get; set; }

        [Required]
        [MaxLength(50)]
        [Column("name")]
        [Display(Name = "用户名")]
        public string Name { get; set; }

        [Required]
        [MaxLength(50)]
        [Column("password")]
        [Display(Name = "密码")]
        public string Password { get; set; }
    }
}
