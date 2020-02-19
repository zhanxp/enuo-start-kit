
using System.Collections.Generic;
using Com.EnuoCms.IService;
using Com.EnuoCms.Core.Common;
using System.Web.Http;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Core.Dispatcher
{
    public class WebAPICommandBus : ICommandBus
    {
        public ICommandResult Submit<TCommand>(TCommand command) where TCommand : ICommand
        {
            ICommandHandler<TCommand> handler = (ICommandHandler<TCommand>)GlobalConfiguration.Configuration.DependencyResolver.GetService(typeof(ICommandHandler<TCommand>));
            if (!((handler != null) && handler is ICommandHandler<TCommand>))
            {
                throw new CommandHandlerNotFoundException(typeof(TCommand));
            }
            return handler.Execute(command);

        }
        public IEnumerable<ValidationResult> Validate<TCommand>(TCommand command) where TCommand : ICommand
        {
            IValidationHandler<TCommand> handler = (IValidationHandler<TCommand>)GlobalConfiguration.Configuration.DependencyResolver.GetService(typeof(IValidationHandler<TCommand>));
            if (!((handler != null) && handler is IValidationHandler<TCommand>))
            {
                throw new ValidationHandlerNotFoundException(typeof(TCommand));
            }
            return handler.Validate(command);
        }
    }
}

