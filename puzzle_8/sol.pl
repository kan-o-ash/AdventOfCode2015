#!/usr/bin/perl
use feature qw(say);

open(DATA, "<input.txt") or die "Couldn't open file file.txt, $!";

$str = "";
$line_count = 0;
$code_char_count = 0;

while(<DATA>){
    $line = substr $_, 1, -2;
    $str = $str . $line;
    $line_count++;
}

$code_char_count += $line_count*2;
$code_char_count += length $str;

# replace all \x codes like "\x00" with a tilde "~"
$str =~ s/((?<!\\)(\\\\)*\\(?!\\)x..)/(substr $1,0,-4)."~"/ge;
# replace escaped quotation mark with just quotation mark
$str =~ s/(\\\")/\"/g;
# replace escaped forward slash with just forward slash
$str =~ s/(\\\\)/\\/g;

# result
say $code_char_count - length $str;
