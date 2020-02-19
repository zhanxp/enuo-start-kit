namespace Com.EnuoCms.IService
{
    public class CommandResult : ICommandResult
    {
        public CommandResult(bool success)
        {
            this.Success = success;
        }
        public CommandResult(bool success, string Message)
        {
            this.Success = success;
            this.Message = Message;
        }
        public bool Success { get; protected set; }
        public string Message { get; protected set; }
    }
}


