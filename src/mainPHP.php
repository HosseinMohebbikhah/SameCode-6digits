<?php

const changeEverySeconds = 10;

function generateCode($key)
{
    $currentTime = time();
    $timestampAtMinuteStart = $currentTime - ($currentTime % changeEverySeconds);
    return hexdec(substr(hash('sha256', ($key . $timestampAtMinuteStart) . $timestampAtMinuteStart), 0, 8)) % 1000000;
}

$key = "Hi";
return generateCode($key);
