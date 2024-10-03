# Web Common Starter

A utility library for web development in Spring Boot.

## Requirements

- Java 17 or higher

Built with Spring Boot 3.

## Usage

Run `mvn install` to install the library to your local repository.

Add the dependency to your project:

```xml
<dependency>
    <groupId>tech.rollw.common</groupId>
    <artifactId>web-common-spring-boot-starter</artifactId>
    <version>0.2.1</version>
</dependency>
```

## How to use

This library provides some common interfaces and implementations for web development:

### Base

- `ErrorCode`: An interface for error codes, you can implement it to define your own error codes.
- `BusinessException`: An exception that contains an error code.
- `HttpResponseBody`: A wrapper for HTTP response body, it contains the error code and the data.
- `HttpResponseEntity`: An extension of `ResponseEntity` that contains `HttpResponseBody` as the body.

### Web System

In the `tech.rollw.common.web.system` package, provides interfaces to help you manage 
the "system resources" in your project.

- `SystemResource`: A `SystemResource` represents a resource in your application, 
such as a user, a file, a blog post and so on.
  Typically associated with an entity in your database.
- `SystemResourceKind`: Represents which kind of the `SystemResource`, you can implement
it to define your own kinds.
- `SystemResourceFactory`: The `SystemResourceFactory` is used to create `SystemResource` instances
by the given ID and the type of the resource.
  To achieve this, you need to implement the `SystemResourceProvider` interface to handle the creation.


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