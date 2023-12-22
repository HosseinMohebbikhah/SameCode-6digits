import 'dart:convert';
import 'dart:math';
import 'package:crypto/crypto.dart';

const int changeEverySeconds = 10;

int generateCode(String key) {
  DateTime now = DateTime.now();
  int timestampAtMinuteStart = now.millisecondsSinceEpoch ~/ 1000 -
      (now.second %
          changeEverySeconds); // Set timestamp to the beginning of the current minute
  String sharedKey = key +
      timestampAtMinuteStart
          .toString(); // Replace this with your actual shared key

  String code = sha256
      .convert(utf8.encode(sharedKey + timestampAtMinuteStart.toString()))
      .toString();
  return int.parse(code.substring(0, 8), radix: 16) % 1000000;
}

void main() {
  String key = "Hi";
  print(generateCode(key));
}
