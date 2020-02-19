namespace Com.EnuoCms.IService
{
    public interface ICommandResult
    {
        bool Success { get; }
        string Message { get; }
    }
}

