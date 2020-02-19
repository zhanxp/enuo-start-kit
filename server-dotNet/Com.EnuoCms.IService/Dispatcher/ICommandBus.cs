
using Com.EnuoCms.Core.Common;
using System.Collections.Generic;

namespace Com.EnuoCms.IService
{
    public interface ICommandBus
    {
        ICommandResult Submit<TCommand>(TCommand command) where TCommand: ICommand;
        IEnumerable<ValidationResult> Validate<TCommand>(TCommand command) where TCommand : ICommand;
    }
}

