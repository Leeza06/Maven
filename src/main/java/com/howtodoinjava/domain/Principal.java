/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.howtodoinjava.domain;

import java.util.Set;

import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Relation;
import com.buschmais.xo.neo4j.api.annotation.Relation.Incoming;
import com.buschmais.xo.neo4j.api.annotation.Relation.Outgoing;

@Label
public interface Principal {

    String getName();

    void setName(String newValue);

    /**
     * @return a set of {@link Group} entities which this entity is a member of
     */
    @Relation("IS_MEMBER_OF")
    @Outgoing
    Set<Group> getGroups();

    /**
     * @return a set of {@link Principal} entities which are member of this entity
     */
    @Relation("IS_MEMBER_OF")
    @Incoming
    Set<Principal> getMembers();

}
