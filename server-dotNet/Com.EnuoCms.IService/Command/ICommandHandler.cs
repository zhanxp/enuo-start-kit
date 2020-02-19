namespace Com.EnuoCms.IService
{
    public interface ICommandHandler<in TCommand> where TCommand: ICommand
    {
        ICommandResult Execute(TCommand command);
    }
}

