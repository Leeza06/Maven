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
package com.howtodoinjava;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;
import com.howtodoinjava.domain.Group;
import com.howtodoinjava.domain.User;

public class XOTest {

    private XOManagerFactory xoManagerFactory;
    private XOManager xoManager;

    @Before
    public void setUp() {

        /**
         * Initialize the XOManagerFactory by the named XOUnit.
         *
         * See META-INF/xo.xml for more details.
         */
        xoManagerFactory = XO.createXOManagerFactory("DemoJavaProject");

        /**
         * Create the XOManager.
         */
        xoManager = xoManagerFactory.createXOManager();
    }

    @After
    public void tearDown() {

        /**
         * Safely close the XOManagerFactory / XOManager after completion.
         */
        if (xoManagerFactory != null) {
            if (xoManager != null) {
                xoManager.close();
            }
            xoManagerFactory.close();
        }
    }

    @Test
    public void test() {

        /**
         * Start a new transaction.
         */
        xoManager.currentTransaction().begin();

        try {

            /**
             * Create a entity of type {@link User} and set some properties.
             */
            final User user = xoManager.create(User.class);
            user.setName("mike");
            user.setFirstname("Mike");
            user.setLastname("Smith");

            /**
             * Create an entity of type {@link Group} and the user to this group.
             */
            final Group employees = xoManager.create(Group.class);
            employees.setName("Employees");
            employees.getMembers().add(user);

            assertThat(user.getGroups(), hasSize(1));
            assertThat(user.getGroups(), containsInAnyOrder(employees));

            /**
             * Commit the running transaction.
             */
            xoManager.currentTransaction().commit();
        } catch (final XOException e) {

            /**
             * Roll the transaction back in case of any database exception.
             */
            xoManager.currentTransaction().rollback();
        }
    }

}