# Web Common Starter

A utility library for web development in Spring Boot.

## Requirements

- Java version 17 or higher

Built with Spring Boot 2.7.14.
As for SpringBoot 3.x, it has API updates that are not compatible with 2.x. 
Like the `javax.validation` to `jakarta.validation`, so it is not supported yet.

## Usage

Run `mvn install` to install the library to your local repository.

Add the dependency to your project:

```xml
<dependency>
    <groupId>tech.rollw.common</groupId>
    <artifactId>web-common-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>
```

## License

```text
Copyright (C) 2023 RollW

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```