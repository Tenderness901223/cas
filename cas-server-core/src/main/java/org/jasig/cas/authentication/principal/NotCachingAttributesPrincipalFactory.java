/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.cas.authentication.principal;

import org.jasig.services.persondir.IPersonAttributeDao;

import java.util.Map;

/**
 * Factory to create {@link NotCachingAttributesPrincipal} objects.
 * @author Misagh Moayyed
 * @since 4.1
 */
public final class NotCachingAttributesPrincipalFactory implements PrincipalFactory {
    private final IPersonAttributeDao attributeRepository;

    /**
     * Instantiates a new Uncached attributes principal factory.
     *
     * @param attributeRepository the attribute repository
     */
    public NotCachingAttributesPrincipalFactory(final IPersonAttributeDao attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public Principal createPrincipal(final String id) {
        return createPrincipal(id, null);
    }

    /**
     * {@inheritDoc}
     * <p>Creates the principal with an null set of attributes first. Upon request for attributes
     * the set will be updated again. No need to call the attribute repository for attributes
     * when they are going to provided to the caller when accessed.</p>
     */
    @Override
    public Principal createPrincipal(final String id, final Map<String, Object> attributes) {
         return new NotCachingAttributesPrincipal(id, null, this.attributeRepository);
    }

    /**
     * Gets attribute repository.
     *
     * @return the attribute repository
     */
    public IPersonAttributeDao getAttributeRepository() {
        return attributeRepository;
    }
}
