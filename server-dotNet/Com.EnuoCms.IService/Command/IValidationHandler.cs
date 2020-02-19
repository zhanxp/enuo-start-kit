using Com.EnuoCms.Core.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Com.EnuoCms.IService
{
    public interface IValidationHandler<in TCommand> where TCommand : ICommand
    {
        IEnumerable<ValidationResult>  Validate(TCommand command);
    }
}
