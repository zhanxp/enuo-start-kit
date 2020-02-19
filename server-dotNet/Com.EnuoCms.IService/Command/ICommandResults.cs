namespace Com.EnuoCms.IService
{
    public interface ICommandResults
    {
        ICommandResult[] Results { get; }

        bool Success { get; }
        
    }
}

