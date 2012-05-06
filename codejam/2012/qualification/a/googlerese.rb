#!/usr/bin/env ruby


translation = {
  "a" => "y",
  "b" => "h",
  "c" => "e",
  "d" => "s",
  "e" => "o",
  "f" => "c",
  "g" => "v",
  "h" => "x",
  "i" => "d",
  "j" => "u",
  "k" => "i",
  "l" => "g",
  "m" => "l",
  "n" => "b",
  "o" => "k",
  "p" => "r",
  "q" => "z",
  "r" => "t",
  "s" => "n",
  "t" => "w",
  "u" => "j",
  "v" => "p",
  "w" => "f",
  "x" => "m",
  "y" => "a",
  "z" => "q",
  "\n" => "\n",
  " " => " "
}

n = STDIN.gets.to_i
for i in 1..n
  print "Case ##{i}: "
  STDIN.gets.chars.to_a.each {
    |c|
    print translation[c]
  }
end
