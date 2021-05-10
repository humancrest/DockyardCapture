# Dockyard - Capture

Dockyard - Capture is screenshot utility on different platforms (Windows, Mac).  
It reads the URL from the CSV file and automatically takes a full page screenshot in all major web
browsers.

## Requirements

The latest version of the [Java 11 OpenJDK](https://openjdk.java.net/)

## Build

### Run

`./gradlew run --args="-fn [filePath]/[fileName].csv -osn mac -brn edge" `

#### Argument

|  configuration key  |  Description  |  Example  |
| ---- | ---- | ---- |
|  --fileName<BR>-fn  |  Path to file contain CSV file.  |  --fileName input.csv  |
|  --browserName<BR>-brn  |  This script runs this browser.  |  --browserName chrome<BR> (chrome,firefox,edge, safari) |
|  --osName<BR>-osn  |  This script runs this os.  |  --osName mac<BR>(mac, win)

### Prerequisites

#### CSV file

* Character code must be UTF-8
* One line must meet the following format  
  (NO.), (Name), (URL)  
  SampleFile: DockyardCapture/src/test/resources/sample.csv
* Only single-byte alphanumeric characters can be used for (Name)
* (NO.) Is a half-width number from 0 to 99999
* If the page length is too long, such as the timeline, it may not be possible to capture.

## License

umbt's source code is made available under the [Apache 2.0 license](https://github.com/humancrest/DockyardCapture/blob/main/LICENSE).
