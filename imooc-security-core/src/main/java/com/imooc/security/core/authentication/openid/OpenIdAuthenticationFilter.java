package com.imooc.security.core.authentication.openid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.imooc.security.core.properties.contsant.SecurityConstants;

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
		// =====================================================================================
		public static final String OPENID_KEY = SecurityConstants.DEFAULT_REQUEST_PARAMETER_OPENID;
		public static final String PROVIDERID_KEY = SecurityConstants.DEFAULT_REQUEST_PARAMETER_PROVIDERID;

		private String openIdParameter = OPENID_KEY;
		private String providerIdParameter = PROVIDERID_KEY;
		private boolean postOnly = true;

		// ~ Constructors
		// ===================================================================================================

		public OpenIdAuthenticationFilter(String loginProcessUrlOpenId) {
			super(new AntPathRequestMatcher(loginProcessUrlOpenId, "POST"));
		}

		// ~ Methods
		// ========================================================================================================

		public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException(
						"Authentication method not supported: " + request.getMethod());
			}

			String openId = obtainOpenId(request);
			String providerId = obtainProviderId(request);

			if (openId == null) {
				openId = "";
			}

			openId = openId.trim();
			providerId = providerId.trim();

			OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);
			
			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}

		/**
		 * Enables subclasses to override the composition of the username, such as by
		 * including additional values and a separator.
		 *
		 * @param request so that request attributes can be retrieved
		 *
		 * @return the username that will be presented in the <code>Authentication</code>
		 * request token to the <code>AuthenticationManager</code>
		 */
		protected String obtainOpenId(HttpServletRequest request) {
			return request.getParameter(openIdParameter);
		}
		protected String obtainProviderId(HttpServletRequest request) {
			return request.getParameter(providerIdParameter);
		}

		/**
		 * Provided so that subclasses may configure what is put into the authentication
		 * request's details property.
		 *
		 * @param request that an authentication request is being created for
		 * @param authRequest the authentication request object that should have its details
		 * set
		 */
		protected void setDetails(HttpServletRequest request,
				OpenIdAuthenticationToken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}

		/**
		 * Sets the parameter name which will be used to obtain the username from the login
		 * request.
		 *
		 * @param usernameParameter the parameter name. Defaults to "username".
		 */
		public void setMobileParameter(String usernameParameter) {
			Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
			this.openIdParameter = usernameParameter;
		}

		/**
		 * Defines whether only HTTP POST requests will be allowed by this filter. If set to
		 * true, and an authentication request is received which is not a POST request, an
		 * exception will be raised immediately and authentication will not be attempted. The
		 * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
		 * authentication.
		 * <p>
		 * Defaults to <tt>true</tt> but may be overridden by subclasses.
		 */
		public void setPostOnly(boolean postOnly) {
			this.postOnly = postOnly;
		}

		public final String getOpenIdParameter() {
			return openIdParameter;
		}
		
		public final String getProviderIdParameter() {
			return providerIdParameter;
		}
}
