# Roozh for Java
> Tired of struggling with Persian dates in Java? Does your next project needs Persian dates instead of Gregorian ones? Are you an Iranian? You know that Java doesn't provide Persian dates natively.

If these questions seem familiar, the Roozh is the one here can help you achieve your goals.

**Never** waste your time again to search for a library or making new one.

## A Quick Overview What's In
* The easiest possible integration
* Integrate in less than 5 minutes
* Compatible down to Java 1.7
* Quick and simple API
* **Formatter**
* Tested and stable

## Include to Project
### Put `.jar` File into Libs Directory
Get the provided latest version of the library from root path. Simply put the downloaded file into the libs directory.

### Clone or Download `.zip` file
Clone this repository or download the compressed file, then extract to your computer. Simply import the library module to your project.

## Usage
```java
RoozhFormatter formatter = new RoozhFormatter()
    .appendDayOfMonth()
    .appendSpace()
    .appendMonth()
    .appendSpace()
    .appendYear(4);
// Format with current time
String formatted = formatter.build();
```

## Credits
- Kaveh Shahbazian - [Github](https://github.com/dc0d)
    - For the base of the Roozh for Java

## Developed By
- Alireza Eskandarpour Shoferi
    - [Twitter](https://twitter.com/enormoustheory) - [aesshoferi@gmail.com](mailto:aesshoferi@gmail.com)

## License
    Copyright 2016 Alireza Eskandarpour Shoferi
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
		http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.