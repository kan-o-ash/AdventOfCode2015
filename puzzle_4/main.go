package main

import (
    "crypto/md5"
    "fmt"
    "strconv"
)

func main() {
    input := "yzbqklnj"

    for i := 0; i < 1000000000; i++ {
        h := md5.Sum([]byte(input + strconv.Itoa(i)))
        hash := fmt.Sprintf("%x", h)
        if hash[0:6] == "000000" {
            fmt.Printf("%d\n", i)
            break
        }
    }
}
