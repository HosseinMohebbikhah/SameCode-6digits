using System;
using System.Security.Cryptography;
using System.Text;

class HelloWorld
{
    const int changeEverySeconds = 10;

    static void Main()
    {
        string key = "Hi";
        Console.WriteLine("Code : " + generateCode(key));
    }

    static int generateCode(string key)
    {
        long currentTime = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
        long timestampAtMinuteStart = currentTime - (currentTime % changeEverySeconds);
        byte[] hashBytes = SHA256
            .Create()
            .ComputeHash(
                Encoding.UTF8.GetBytes((key + timestampAtMinuteStart) + timestampAtMinuteStart)
            );
        Array.Reverse(hashBytes, 0, 4);
        return Math.Abs(BitConverter.ToInt32(hashBytes, 0)) % 1000000;
    }
}
