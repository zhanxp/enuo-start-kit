using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Model
{
    public class BaseEntity
    {
		[Key]
		[Column("id")]
        public virtual int ID { get; set; }

		[Column("ent_types")]
		[Display(Name = "类型")]
		public int EntTypes { get; set; }

		[Column("ent_status")]
		[Display(Name = "状态")]
		public int EntStatus { get; set; }

		[MaxLength(50)]
		[Column("creater")]
		[Display(Name = "创建人")]
		public string Creater { get; set; }

		[MaxLength(50)]
		[Column("updater")]
		[Display(Name = "更新人")]
		public string Updater { get; set; }

		[Required]
		[Column("create_date")]
		[Display(Name = "创建时间")]
		[DisplayFormat(DataFormatString = "{0:yyyy年MM月dd日}")]
		public DateTime CreateDate { get; set; } = System.DateTime.Now;

		[Required]
		[Column("update_date")]
		[Display(Name = "更新时间")]
		[DisplayFormat(DataFormatString = "{0:yyyy年MM月dd日}")]
		public DateTime UpdateDate { get; set; } = System.DateTime.Now;
    }
}
