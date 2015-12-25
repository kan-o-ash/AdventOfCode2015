#!/usr/bin/perl
use feature qw(say);

sub part1 {
    open(DATA, "<input.txt") or die "Couldn't open file file.txt, $!";

    $str = "";
    $line_count = 0;
    $code_char_count = 0;

    while(<DATA>){
        $line = substr $_, 1, -2; # remove quotes and newline
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
}

sub part2 {
    open(DATA, "<input.txt") or die "Couldn't open file file.txt, $!";

    $str = "";
    $line_count = 0;
    $code_char_count = 0;

    while(<DATA>){
        $str = $str . $_;
        $line_count++;
    }

    # each \ or " needs an backslash before, each increase count by 1
    my $c = () = $str =~ /[\\\"]/g;

    # each line gains 2 extra characters due to quotations needed on both sides
    say $c + $line_count*2;
}

part1();
part2();