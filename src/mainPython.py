import hashlib
import time

changeEverySeconds = 10

def generateCode(key):
    currentTime = int(time.time())
    timestampAtMinuteStart = currentTime - (currentTime % changeEverySeconds)
    hashInput = ((key + str(timestampAtMinuteStart)) + str(timestampAtMinuteStart)).encode('utf-8')
    hashResult = hashlib.sha256(hashInput).digest()
    reversed_int = int.from_bytes(hashResult[:4], byteorder='big', signed=False)
    code = abs(reversed_int) % 1000000
    return f"{code:06}"

def main():
    key = "Hi"
    print(generateCode(key))

if __name__ == "__main__":
    main()