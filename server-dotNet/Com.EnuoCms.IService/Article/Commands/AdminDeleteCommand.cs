
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by zhanxp@me.com
//
//     If any question, please let's know.
//     It's a great help for improve our templates
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Com.EnuoCms.Core.Common;

namespace Com.EnuoCms.IService
{
    public class AdminDeleteCommand : ICommand
    {
        public List<long> itemId;
        public AdminDeleteCommand(List<long> itemId)
        {
            this.itemId = itemId;
        }
    }
}
