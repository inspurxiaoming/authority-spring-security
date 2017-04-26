/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package cn.jw.authority.rest.security;

public class Constant {
	
    public final static String ROLE_AUTHORITY_SYSTEM_ADMIN = "ROLE_AUTHORITY_SYSTEM_ADMIN";  //权限申请系统管理员角色
    public final static String ROLE_AUTHORITY_SYSTEM_USER = "ROLE_AUTHORITY_SYSTEM_USER";   //权限申请系统普通角色
    
    public final static String ACCESS_HAS_ROLE_AUTHORITY_SYSTEM_ADMIN = "hasRole('ROLE_AUTHORITY_SYSTEM_ADMIN')";
    public final static String ACCESS_HAS_ROLE_AUTHORITY_SYSTEM_USER = "hasRole('ROLE_AUTHORITY_SYSTEM_USER')";

    public final static String FakeSchemaName = "defaultSchema";
    public final static String FakeCatalogName = "defaultCatalog";

    public final static String IDENTITY_USER = "user";
    public final static String IDENTITY_ROLE = "role";
    
    public final static String ACCESS_POST_FILTER_READ = "hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'MANAGEMENT') " + "or hasPermission(filterObject, 'OPERATION') or hasPermission(filterObject, 'ADMINISTRATION')";

    public final static String SERVER_MODE_QUERY = "query";
    public final static String SERVER_MODE_JOB = "job";
    public final static String SERVER_MODE_ALL = "all";

}
