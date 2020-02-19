using System;
using System.Collections.Generic;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Core.Common
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
