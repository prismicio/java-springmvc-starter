## Starter for Spring MVC projects

This is a blank [Spring MVC](http://projects.spring.io/spring-framework/) project that will connect to any [prismic.io](https://prismic.io) repository. It uses the prismic.io Java developement kit, and provide a few helpers to integrate with the Spring framework.

### How to start?

Edit the `web-context.xml` file to make the application point to the correct repository:

```
<!-- Prismic Config -->
<bean id="prismicConfig" class="io.prismic.starter.helper.PrismicConfig">
  <property name="apiEndpoint" value="https://lesbonneschoses.prismic.io/api" />
  <!-- <property name="accessToken" value="xxxxxx" /> -->
  <!-- <property name="clientId" value="xxxxxx" /> -->
  <!-- <property name="clientSecret" value="xxxxxx" /> -->
</bean>
```

Run your play application using either the `mvn jetty:run` and open your browser at http://localhost:8080/

### Licence

This software is licensed under the Apache 2 license, quoted below.

Copyright 2013 Zengularity (http://www.zengularity.com).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.