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
package org.apache.ibatis.session;

/**
 * 一级缓存最大范围是 SqlSession 内部，有多个 SqlSession 或者分布式的环境下，数据库写操作会引起脏数据，建议设定缓存级别为 Statement
 * 一级缓存内部设计简单 PerpetualCache，没有容量限定，在缓存的功能性上有所欠缺。
 * @author Eduardo Macarron
 */
public enum LocalCacheScope {
  SESSION, //默认。一个 MyBatis 会话中执行的所有语句，都会共享这一个缓存
  STATEMENT //缓存只对当前执行的这一个 Statement 有效
}
