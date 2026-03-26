# Vineflower Server

A server that can be run to repeatedly call Vineflower without continuously creating and destroying new jvm instances.

## About

Initially built to improve efficiency the [Pitest](https://github.com/hcoles/pitest) framework integration with
[Marv](https://github.com/SecretSheppy/marv), which makes thousands of decompilation calls to vineflower in order to
extract the generated mutants.

## Usage

Vineflower Server provides only one route `/vineflower` which is used as a rough replica of the 
[Vineflower cli](https://vineflower.org/usage/). There are a few differences:

* `.class` or `.jar` file paths must be specified with the `source` parameter.
* Library location paths (optional) must be specified with the `library` parameter.
* The destination path or file (optional) must be specified with the `destination` parameter. If no destination is provided then the decompiled files will be returned in the response JSON.

All other Vineflower [base decompiler options](https://vineflower.org/usage/#base-decompiler-options) can be added
with the name seen in the documentation without any `-` or `--` prefixes. For example:

### Example Usage in Go

```go
package main

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"
)

const BaseUrl = "http://localhost:8080/vineflower"

var Parameters = []string{
	"source=/path/to/source.class",
	"source=/path/to/other_source.class",
	"banner=hello%20world", // base decompiler option --banner
}

type Response struct {
	Destination string            `json:"destination"`
	Output      map[string]string `json:"output"`
}

func (r Response) String() string {
	return fmt.Sprintf("Destination:%s,Output:%v", r.Destination, r.Output)
}

func main() {
	params := strings.Join(Parameters, "&")
	url := fmt.Sprintf("%s?%s", BaseUrl, params)

	res, err := http.Get(url)
	if err != nil {
		panic(err)
	}

	body, err := io.ReadAll(res.Body)
	if err != nil {
		panic(err)
	}

	var response Response
	if err := json.Unmarshal(body, &response); err != nil {
		panic(err)
	}

	fmt.Println(response)
}
```