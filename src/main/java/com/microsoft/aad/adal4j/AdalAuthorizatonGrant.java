/*******************************************************************************
 * Copyright © Microsoft Open Technologies, Inc.
 * 
 * All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * THIS CODE IS PROVIDED *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * ANY IMPLIED WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A
 * PARTICULAR PURPOSE, MERCHANTABILITY OR NON-INFRINGEMENT.
 * 
 * See the Apache License, Version 2.0 for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/
package com.microsoft.aad.adal4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.nimbusds.oauth2.sdk.AuthorizationGrant;

/**
 * 
 */
class AdalAuthorizatonGrant {

    private final AuthorizationGrant grant;
    private final Map<String, String> params;

    /**
     * 
     * @param grant
     * @param scope
     */
    AdalAuthorizatonGrant(final AuthorizationGrant grant, final String[] scope) {
        this.grant = grant;
        params = new LinkedHashMap<String, String>();
        params.put("scope", StringHelper.convertArrayToString(scope));
    }

    /**
     * 
     * @param grant
     * @param params
     */
    AdalAuthorizatonGrant(final AuthorizationGrant grant,
            final Map<String, String> params) {
        this.grant = grant;
        this.params = params;
    }

    Map<String, String> toParameters() {

        final Map<String, String> outParams = new LinkedHashMap<String, String>();
        if (this.params != null) {
            outParams.putAll(this.params);
        }

        outParams.put("scope", "openid");
        outParams.putAll(grant.toParameters());
        return outParams;
    }

    AuthorizationGrant getAuthorizationGrant() {
        return this.grant;
    }

    Map<String, String> getCustomParameters() {
        return params;
    }
}
