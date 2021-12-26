/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clinton Begin
 */
public class InterceptorChain {

  private final List<Interceptor> interceptors = new ArrayList<Interceptor>();

  /**
   * 按照实现拦截器的顺序进行插件封装。
   * 根据 Interceptor 的实现，可能会有不同的效果。
   * 1. Interceptor 实现丢弃了之前的 target，重新生成一个 target.getClass() 类型的对象
   * 2. Interceptor 在原先 target 的基础之上，使用 Proxy.newProxyInstance 形成递增式代理
   * 一般情况下，建议 Interceptor 实现者（加上Intercepts注解，用于确定拦截类型及方法）的 plugin 方法调用 Plugin.wrap(...) 进行包装，逐层进行代理。$Proxy($Proxy($Proxy()))
   *
   * @param target
   * @return
   */
  public Object pluginAll(Object target) {
    for (Interceptor interceptor : interceptors) {
      target = interceptor.plugin(target);
    }
    return target;
  }

  public void addInterceptor(Interceptor interceptor) {
    interceptors.add(interceptor);
  }
  
  public List<Interceptor> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }

}
