package com.services.rest;

import com.wpl.commons.ParameterConstants;

public class SecurityCheckUtil {

	public static boolean isRequestFromValidSource(String secretValue) {
		if (secretValue == null)
			return false;

		if (secretValue.equals(ParameterConstants.SECRET_KEY_VALUE))
			return true;

		return false;
	}

}