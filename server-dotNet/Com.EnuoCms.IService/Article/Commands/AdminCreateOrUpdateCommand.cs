
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by zhanxp@me.com
//
//     If any question, please let's know.
//     It's a great help for improve our templates
// </auto-generated>
//------------------------------------------------------------------------------

using Com.EnuoCms.Model.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Com.EnuoCms.IService
{
    public class AdminCreateOrUpdateCommand :ICommand
    {
        public Admin ent;

        public AdminCreateOrUpdateCommand(Admin ent)
        {
            this.ent = ent;
        }
    }
}
