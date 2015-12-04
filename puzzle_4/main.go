package main

import (
    "crypto/md5"
    "fmt"
    "io"
    "strconv"
)

func main() {
    input := "yzbqklnj"

    for i := 0; i < 1000000000; i++ {
        h := md5.New()
        io.WriteString(h, input)
        io.WriteString(h, strconv.Itoa(i))
                
        hash := fmt.Sprintf("%x", h.Sum(nil))
        if hash[0:6] == "000000" {
            fmt.Printf("%d\n", i)
            break
        }
    }
}
